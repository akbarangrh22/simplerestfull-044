/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplerestfull.demo;

import java.util.HashMap;
import java.util.Map;
import model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */

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
        //GET DELETE
        @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
        public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        productRepo.remove(id);
        return new ResponseEntity<>("Product is delete successfully", HttpStatus.OK);
        
        }
        //GET PUT
        @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
        public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        //jika id tidak ketemu, tidak bisa update data
        if(!productRepo.containsKey(id)){ 
           return new ResponseEntity<>("There is no product key yet", HttpStatus.NOT_FOUND);
        }
        //id sama, data akan update
        else{
           productRepo.remove(id);
           product.setId(id);
           productRepo.put(id, product);
           return new ResponseEntity<>("Product is updated Successfully", HttpStatus.OK);
        }
        }
        //GET POST
        @RequestMapping(value = "/products", method = RequestMethod.POST)
        public ResponseEntity<Object> createProduct(@RequestBody Product product){
        //jika ada id sama akan memunculkan "Product key cannot duplicated"
        if (productRepo.containsKey(product.getId())){
           return new ResponseEntity<>("Product key cannot duplicated", HttpStatus.OK);
        }
        //id berbeda / belum pernah dibuat data berhasil dibuat dan akan memunculkan"Product is created successfully"
        else{
           productRepo.put(product.getId(), product);
           return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
            }
        }
        //GET API
        @RequestMapping(value = "/products")
        public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
        }
}
    

