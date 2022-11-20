package com.antra.controller;

import com.antra.exception.ProductItemException;
import com.antra.exception.ProductItemNotFoundException;
import com.antra.service.ProductItemService;
import com.antra.vo.ErrorResponse;
import com.antra.vo.ProductItem;
import com.antra.vo.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Product", description = "REST API for Products", tags = {"Product"})
public class ProductItemRestController {

    final ProductItemService productItemService;

    @Autowired
    public ProductItemRestController(ProductItemService productItemService){
        this.productItemService = productItemService;
    }

    // hello msg

    @ApiOperation(value = "Display a hello message")
    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<String>("Hello!", HttpStatus.OK);
    }


    // Create
    @ApiOperation(value = "Create a new product record")
    @PostMapping(value = "/product")
    public ResponseEntity<ResponseMessage> createProduct(@RequestParam(required = false, defaultValue = "0") Long id,
                                                         @RequestParam(required = false, defaultValue = "name") String name,
                                                         @RequestParam(required = false, defaultValue = "0.0") Double price,
                                                         UriComponentsBuilder ucBuilder){
        ProductItem product = productItemService.saveProduct(new ProductItem(id, name, price));
        HttpHeaders header = new HttpHeaders();
        header.setLocation(ucBuilder.path("/api/product?id={id}&name={name}&price={price}").buildAndExpand(product.getId(), product.getName(), product.getPrice()).toUri());
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("PRODUCT_CREATED", product), header, HttpStatus.CREATED);
    }

    // Read
    @ApiOperation(value = "Read a single product record")
    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductItem> getProduct(@PathVariable("id") long id) throws ProductItemException{
        ProductItem product = productItemService.findById(id);
        if(product == null){
            throw new ProductItemNotFoundException("PRODUCT_NOT_FOUND");
        }
        return new ResponseEntity<ProductItem>(product, HttpStatus.OK);
    }

    @ApiOperation(value = "Read all products")
    @GetMapping(value = "/product")
    public ResponseEntity<List<ProductItem>> getAllProduct(){
        List<ProductItem> products = productItemService.findAllProduct();
        if(products.isEmpty()) throw new ProductItemNotFoundException("EMPTY_TABLE");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Update
    @ApiOperation(value = "Update a single product")
    @PutMapping(value = "/product/{id}")
    public ResponseEntity<ProductItem> updateProduct(@PathVariable("id") long id, @RequestBody ProductItem product){
        ProductItem current = productItemService.findById(id);
        if(current == null) throw new ProductItemNotFoundException("PRODUCT_NOT_FOUND");
        current.setName(product.getName());
        current.setPrice(product.getPrice());
        productItemService.updateProduct(current);
        return new ResponseEntity<ProductItem>(current, HttpStatus.OK);
    }

    // Delete
    @ApiOperation(value = "Delete a single product")
    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable("id") long id){
        ProductItem product = productItemService.findById(id);
        if(product == null) throw new ProductItemNotFoundException("PRODUCT_NOT_FOUND");
        productItemService.deleteProductById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("PRODUCT_DELETED", product), HttpStatus.OK);
    }

    // local ExceptionHandlers
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception e){
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMsg(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
