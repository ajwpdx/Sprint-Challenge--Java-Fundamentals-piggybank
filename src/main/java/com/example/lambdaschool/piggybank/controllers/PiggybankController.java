package com.example.lambdaschool.piggybank.controllers;

import com.example.lambdaschool.piggybank.models.Coin;
import com.example.lambdaschool.piggybank.repositories.PiggybankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PiggybankController {

    @Autowired
    private PiggybankRepository coinrepos;

    private List<Coin> screenCoin(List<Coin> myList, double amount)
    {
        List<Coin> tempList = new ArrayList<>();

        for (Coin c : myList)
        {
            if(c.getValue() > amount)
            {

                tempList.add(c);
            }

            else
            {
                if (c.getValue()*c.getQuantity() < amount)
                {
                    amount = amount - c.getValue()*c.getQuantity();
                }
                else {
                    tempList.add(c);
                }
            }
        }
        return tempList;
    }

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
    //http://localhost:2019/money/{amount}
    @GetMapping(value = "money/{amount}", produces = {"application/json"})
    public ResponseEntity<?> emptyPiggybank(@PathVariable double amount)
    {
        List<Coin> myPiggybank = new ArrayList<>();
        coinrepos.findAll().iterator().forEachRemaining(myPiggybank::add);

        List<Coin> rtnList = screenCoin(myPiggybank, amount);

        double total = 0.0;
        for (Coin c : rtnList)
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
