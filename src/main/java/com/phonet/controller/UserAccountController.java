package com.phonet.controller;

import com.phonet.model.UserAccount;
import com.phonet.service.UserAccountService;
import com.phonet.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @RequestMapping(value = "/userAccount/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserAccount userAccount, UriComponentsBuilder ucBuilder) {
        if (userAccountService.isExist(userAccount)) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with number " +
                    userAccount.getNumber() + " already exist."), HttpStatus.CONFLICT);
        }
        userAccountService.save(userAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/userAccount/{id}").buildAndExpand(userAccount.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/userAccount/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        UserAccount userAccount = userAccountService.findById(id);
        if (userAccount == null) {
            return new ResponseEntity(new CustomErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserAccount>(userAccount, HttpStatus.OK);
    }

    @RequestMapping(value = "/userAccount/{number}", method = RequestMethod.GET)
    public ResponseEntity<?> operationUserCash(@PathVariable("number") String number, @PathVariable("money") String money) {
        UserAccount userAccount = userAccountService.findByNumber(number);
        Double withDraw = Double.valueOf(money);
        if (userAccount == null) {
            return new ResponseEntity(new CustomErrorType("User with number " + number
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        userAccount.setWithDraw(withDraw);
        userAccountService.cashOperation(userAccount);

        return new ResponseEntity<UserAccount>(userAccount, HttpStatus.OK);
    }
}
