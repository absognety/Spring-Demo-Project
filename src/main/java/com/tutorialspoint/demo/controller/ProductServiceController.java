package com.tutorialspoint.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialspoint.demo.model.Product;

@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id,@PathVariable("name") String name) {
        productRepo.remove(id);
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}/{name}", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@PathVariable("id") String id,@PathVariable("name") String name) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
}
