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

        double total = 0.0;
        for (Coin c : myPiggybank)
        {
            if (c.getQuantity() > 1)
            {
                System.out.println(c.getQuantity() + " " + c.getNameplural());
            } else {
                System.out.println(c.getQuantity() + " " + c.getName());
            }

            total = total + (c.getQuantity() * c.getValue());
        }
        System.out.println("The piggy bank holds " + total);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
