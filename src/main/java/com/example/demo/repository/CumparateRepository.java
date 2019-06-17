package com.example.demo.repository;


import com.example.demo.model.Cumparare;
import com.example.demo.model.Rezervare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CumparateRepository extends CrudRepository<Cumparare, String> {}