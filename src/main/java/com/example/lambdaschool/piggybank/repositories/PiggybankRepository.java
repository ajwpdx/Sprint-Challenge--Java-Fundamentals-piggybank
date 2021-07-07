package com.example.lambdaschool.piggybank.repositories;

import com.example.lambdaschool.piggybank.models.Coin;
import org.springframework.data.repository.CrudRepository;

public interface PiggybankRepository extends CrudRepository<Coin, Long>
{

}
