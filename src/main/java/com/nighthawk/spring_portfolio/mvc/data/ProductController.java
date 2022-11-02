package com.nighthawk.spring_portfolio.mvc.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/products")  // all requests in file begin with this URI
public class ProductController {

    // Autowired enables Control to connect URI request and POJO Object to easily for Database CRUD operations
    @Autowired
    private ProductJpaRepository productrepository;

    /* GET List of Jokes
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>(productrepository.findAll(), HttpStatus.OK);
    }

    /* Update Like
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PutMapping("/buy/{id}")
    public ResponseEntity<Product> setLike(@PathVariable long id) {
        /* 
        * Optional (below) is a container object which helps determine if a result is present. 
        * If a value is present, isPresent() will return true
        * get() will return the value.
        */
        Optional<Product> optional = productrepository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Product product = optional.get();  // value from findByID
            product.setYa(product.getYa()+1); // increment value
            productrepository.save(product);  // save entity
            return new ResponseEntity<>(product, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed HTTP response: status code, headers, and body
    }

    /* Update Jeer
     */
    @PutMapping("/skip/{id}")
    public ResponseEntity<Product> setNa(@PathVariable long id) {
        Optional<Product> optional = productrepository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Product product = optional.get();
            product.setNa(product.getNa()+1);
            productrepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
