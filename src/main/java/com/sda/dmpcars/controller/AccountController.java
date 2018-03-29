package com.sda.dmpcars.controller;

import com.sda.dmpcars.dto.AccountDto;
import com.sda.dmpcars.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AccountDto getAccount(@PathVariable Integer id) {
        return accountService.getAccountById(id);
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public Set<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity<String> addAccount(@RequestBody AccountDto accountDto) {
        AccountDto result = accountService.addAccount(accountDto);
        return result.getId() != 0 ?
                new ResponseEntity<>("OK", HttpStatus.OK) :
                new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccount(@RequestBody AccountDto accountDto) {
        return accountService.deleteAccount(accountDto) ?
                new ResponseEntity<>("OK", HttpStatus.OK) :
                new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAccount(@RequestBody AccountDto accountDto) {
        return accountDto.getId().equals(accountService.updateAccount(accountDto).getId()) ?
                new ResponseEntity<>("OK", HttpStatus.OK) :
                new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }

}
