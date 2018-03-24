package com.sda.dmpcars.controller;

import com.sda.dmpcars.model.Rent;
import com.sda.dmpcars.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {

    private RentService rentService;

    @Autowired
    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @RequestMapping(value = "/{carId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Rent> getAllRentsByCarId(@PathVariable int carId) {
        return rentService.getAllRentsByCarId(carId);
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Rent> getAllRentsByAccountId(@PathVariable int accountId) {
        return rentService.getAllRentsByAccountId(accountId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String addRent(@RequestBody Rent rent) {
        rentService.addRent(rent);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String updateRent(@RequestBody Rent rent) {
        rentService.updateRent(rent);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteRent(@RequestBody Rent rent) {
        rentService.deleteRent(rent);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteAllRents() {
        rentService.deleteAllRents();
        return "OK";
    }

}
