package com.example.demo.repository;

import com.example.demo.model.Rezervare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RezervareRepository extends CrudRepository<Rezervare, String> {}