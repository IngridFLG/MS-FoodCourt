package org.restaurante.msplazoleta.infrastructure.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.restaurante.msplazoleta.application.dto.request.DishRequestDto;
import org.restaurante.msplazoleta.application.dto.request.DishUpdateRequestDto;
import org.restaurante.msplazoleta.application.dto.response.DishResponseDto;
import org.restaurante.msplazoleta.application.dto.response.ListDishByRestaurantResponseDto;
import org.restaurante.msplazoleta.application.handler.interfaces.IDishHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @PostMapping
    public ResponseEntity<Void> saveDish(@Valid @RequestBody DishRequestDto dishRequestDto,  HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        dishHandler.saveDish(dishRequestDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDto> getDish(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(dishHandler.getDish(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateDish(@PathVariable("id") Long id, @Valid @RequestBody DishUpdateRequestDto dishUpdateRequestDto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        dishHandler.updateDish(dishUpdateRequestDto, userId, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/state/{id}")
    public ResponseEntity<Void> updateDish(@PathVariable("id") Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        dishHandler.updateStateDish(userId, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ListDishByRestaurantResponseDto>> getAllDishesByRestaurant(@PathVariable("restaurantId") Long restaurantId ,@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam String category) {

        return ResponseEntity.status(HttpStatus.OK).body(dishHandler.getListDishByRestaurant(restaurantId, page, pageSize, category));
    }
}
