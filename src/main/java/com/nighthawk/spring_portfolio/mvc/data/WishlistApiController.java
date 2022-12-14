package com.nighthawk.spring_portfolio.mvc.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/wishlist")  // all requests in file begin with this URI
public class WishlistApiController {
 
    // Autowired enables Control to connect URI request and POJO Object to easily for Database CRUD operations
    @Autowired
    private WishlistJpaRepository wishlistrepository;

    /* GET List of Jokes
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Wishlist>> getWishlist() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>( wishlistrepository.findAll(), HttpStatus.OK);
    }

    /* Update Like
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PutMapping("/like/{id}")
    public ResponseEntity<Wishlist> setLike(@PathVariable long id) {
        /* 
        * Optional (below) is a container object which helps determine if a result is present. 
        * If a value is present, isPresent() will return true
        * get() will return the value.
        */
        Optional<Wishlist> optional = wishlistrepository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Wishlist item = optional.get();  // value from findByID
            item.setLike(item.getLike()+1); // increment value
            wishlistrepository.save(item);  // save entity
            return new ResponseEntity<>(item, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed HTTP response: status code, headers, and body
    }

    /* Update Jeer
     */
    @PutMapping("/dislike/{id}")
    public ResponseEntity<Wishlist> setDislike(@PathVariable long id) {
        Optional<Wishlist> optional = wishlistrepository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Wishlist item = optional.get();
            item.setDislike(item.getDislike()+1);
            wishlistrepository.save(item);
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
