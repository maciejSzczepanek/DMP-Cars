package com.sda.dmpcars.controller;

import com.sda.dmpcars.dto.RentDto;
import com.sda.dmpcars.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/rents")
public class RentController {

    @Autowired
    RentService rentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RentDto getRent(@PathVariable Integer id) {
        return rentService.getRentById(id);
    }

    @RequestMapping(value = "/carRents", method = RequestMethod.GET)
    public Set<RentDto> getAllRentsByCarId(@PathVariable Integer carId) {
        return rentService.getRentsByCarId(carId);
    }

    @RequestMapping(value = "/accountRents", method = RequestMethod.GET)
    public Set<RentDto> getAllCarsByAccountId(@PathVariable Integer accountId) {
        return rentService.getRentsByAccountId(accountId);
    }

    @RequestMapping(value = "/rent", method = RequestMethod.POST)
    public ResponseEntity<String> addRent(@RequestBody RentDto rentDto) {
        RentDto result = rentService.addRent(rentDto);
        return result.getId() != 0 ?
                new ResponseEntity<>("OK", HttpStatus.OK) :
                new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRent(@RequestBody RentDto rentDto) {
        return rentService.deleteRentById(rentDto) ?
                new ResponseEntity<>("OK", HttpStatus.OK) :
                new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/rent", method = RequestMethod.PUT)
    public ResponseEntity<String> updateRent(@RequestBody RentDto rentDto) {
        return rentDto.getId().equals(rentService.updateRent(rentDto).getId()) ?
                new ResponseEntity<>("OK", HttpStatus.OK) :
                new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }

}
