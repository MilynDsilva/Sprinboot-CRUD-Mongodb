package com.example.SpringCRUDOperationsMongodb.controller;

import com.example.SpringCRUDOperationsMongodb.model.CarPojo;
import com.example.SpringCRUDOperationsMongodb.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private MyRepository myRepository;

    public CarPojo carPojo;

    //localhost:8080/api/addcar
    @PostMapping(path = "/addcar")
    public String addCar(@RequestBody CarPojo carPojo){
        myRepository.save(carPojo);
        return "Successfully saved car details of ID:"+carPojo.getId();
    }

    //localhost:8080/api/getcars
    @GetMapping(path = "/getcars")
    public List<CarPojo> getALlCar(){
        return myRepository.findAll();
    }

    //localhost:8080/api/getcar/1
    @GetMapping(path = "/getcar/{id}")
    public Optional getOneCar(@PathVariable int id){
        return myRepository.findById(id);
    }

    //localhost:8080/api/delete/2
    @DeleteMapping(path = "/delete/{id}")
    public String deleteCar(@PathVariable int id){
        myRepository.deleteById(id);
        return "Deleted car with ID "+id;
    }

    //localhost:8080/api/put/3    
    @PutMapping(path = "/put/{id}")
    public CarPojo updateCar(@RequestBody CarPojo carPojoOne , @PathVariable int id){

        return myRepository.findById(id).map(carPojo -> {
                carPojo.setName(carPojoOne.getName());
                carPojo.setColor(carPojoOne.getColor());
                return myRepository.save(carPojo);
        })
                .orElseGet(() -> {
            return myRepository.save(carPojo);
        });

    }
}
