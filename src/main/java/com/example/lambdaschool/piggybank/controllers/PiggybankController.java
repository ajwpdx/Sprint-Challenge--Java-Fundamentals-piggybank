package com.example.lambdaschool.piggybank.controllers;

import com.example.lambdaschool.piggybank.models.Coin;
import com.example.lambdaschool.piggybank.repositories.PiggybankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PiggybankController {

    @Autowired
    private PiggybankRepository coinrepos;

    //http://localhost:2019/total
    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> displayAllWithTotal()
    {
        List<Coin> myPiggybank = new ArrayList<>();
        coinrepos.findAll().iterator().forEachRemaining(myPiggybank::add);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
