package com.testproject.coffeehouseapi.controller;

import com.testproject.coffeehouseapi.dto.request.AvailabilityOfProductsRequest;
import com.testproject.coffeehouseapi.exception.RequestException;
import com.testproject.coffeehouseapi.model.CoffeeHouse;
import com.testproject.coffeehouseapi.model.Product;
import com.testproject.coffeehouseapi.service.CoffeeHouseService;
import com.testproject.coffeehouseapi.util.ResponseHelper;
import com.testproject.coffeehouseapi.validator.ValidationErrMsgBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Manager", description = "Manager API")
@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    private final CoffeeHouseService coffeeHouseService;
    private final ResponseHelper responseHelper;

    @Autowired
    public ManagerController(CoffeeHouseService coffeeHouseService, ResponseHelper responseHelper) {
        this.coffeeHouseService = coffeeHouseService;
        this.responseHelper = responseHelper;
    }

//    @GetMapping("/{coffeeHouseId}")
//    public ResponseEntity<?> getCoffeeHouseProducts(@PathVariable ("coffeeHouseId") Long coffeeHouseId) {
//        List<Product> allProducts = coffeeHouseService.findCoffeeHouseProducts(coffeeHouseService.findById(coffeeHouseId));
//        return new ResponseEntity<>(responseHelper.getCoffeeHouseProductsResponse(allProducts), HttpStatus.OK);
//    }

//    @PostMapping("/{coffeeHouseId}")
//    public ResponseEntity<?> changeProductToNotAvailable(@PathVariable ("coffeeHouseId") Long coffeeHouseId,
//                                                         @RequestBody @Valid AvailabilityOfProductsRequest availabilityOfProductsRequest, BindingResult bindingResult) {
//        CoffeeHouse coffeeHouse = coffeeHouseService.findById(coffeeHouseId);
//        if (bindingResult.hasErrors()) {
//            String errMsg = ValidationErrMsgBuilder.buildFieldErrMsg(bindingResult);
//            throw new RequestException(errMsg, HttpStatus.BAD_REQUEST);
//        }
//        if (!availabilityOfProductsRequest.getNotAvailableProducts().isEmpty()) {
//            coffeeHouseService.setProductAvailability(coffeeHouse, availabilityOfProductsRequest.getNotAvailableProducts(), false);
//        }
//        if (!availabilityOfProductsRequest.getAvailableProducts().isEmpty()) {
//            coffeeHouseService.setProductAvailability(coffeeHouse, availabilityOfProductsRequest.getAvailableProducts(), true);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
