package org.restaurante.msplazoleta.infrastructure.configuration.util;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static org.restaurante.msplazoleta.infrastructure.configuration.Constants.*;

@RequiredArgsConstructor
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final Map<String, List<String>> rolesEndpointsMap = new HashMap<>();

    static {
        // Inicializar el mapa de roles y endpoints
        rolesEndpointsMap.put("ROLE_" + ADMIN_ROLE_ID, Arrays.asList("/restaurant/create", "/restaurant/{id}"));
        rolesEndpointsMap.put("ROLE_" + OWNER_ROLE_ID, Arrays.asList("/restaurant/{id}", "/dish", "/dish/{id}", "/dish/state/{id}", "/restaurant/create/employee", "/order/traceability/startEnd/{restaurantId}", "/order/traceability/average/{restaurantId}"));
        rolesEndpointsMap.put("ROLE_" + CLIENT_ROLE_ID, Arrays.asList("/restaurant", "/dish/restaurant/{restaurantId}", "/order", "/order/cancel/{orderId}", "/order/traceability/{orderId}"));
        rolesEndpointsMap.put("ROLE_" + EMPLOYEE_ROLE_ID, Arrays.asList("/order/state/{state}", "/order/{orderId}", "/order/assign/{orderId}", "/order/ready/{orderId}", "/order/delivered/{orderId}"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String endpoint = request.getRequestURI();

        try {
            String token = getToken(request);
            if (token == null || !JwtUtil.validateJwtToken(token)) {
                sendUnauthorizedError(response);
                return;
            }

            List<String> roles = JwtUtil.getRoles(token);
            if (!isRoleAuthorizedForEndpoint(roles, endpoint)) {
                sendUnauthorizedError(response);
                return;
            }

            Long userId = JwtUtil.getUserId(token);
            request.setAttribute("userId", userId);

            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            sendUnauthorizedError(response, e.getMessage());
        }
    }

    private boolean isRoleAuthorizedForEndpoint(List<String> roles, String endpoint) {
        for (String role : roles) {
            List<String> roleEndpoints = rolesEndpointsMap.get(role);
            if (roleEndpoints != null) {
                for (String allowedEndpoint : roleEndpoints) {
                    if (isEndpointMatch(allowedEndpoint, endpoint)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isEndpointMatch(String allowedEndpoint, String actualEndpoint) {
        Pattern pattern = Pattern.compile(allowedEndpoint
                .replaceAll("\\{id\\}", "[^/]+")
                .replaceAll("\\{restaurantId\\}", "[^/]+")
                .replaceAll("\\{state\\}", "[^/]+")
                .replaceAll("\\{orderId\\}", "[^/]+")
        );

        return pattern.matcher(actualEndpoint).matches();
    }

    private String getToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

    private void sendUnauthorizedError(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }

    private void sendUnauthorizedError(HttpServletResponse response, String message) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), message);
    }
}
