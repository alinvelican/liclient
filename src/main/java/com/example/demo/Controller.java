package com.example.demo;

import com.example.demo.model.ApiResponse;
import com.example.demo.model.Cumparare;
import com.example.demo.model.Rezervare;
import com.example.demo.model.Zbor;
import com.example.demo.repository.CumparateRepository;
import com.example.demo.repository.RezervareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class Controller {

    @Autowired
    private RezervareRepository repository;
    @Autowired
    private CumparateRepository cumparateRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${server.url}")
    public String URL;
    @GetMapping("/getAllZboruri")
    public ApiResponse<List<Zbor>> getAll() {
        ResponseEntity<List<Zbor>> restExchange =
                restTemplate.exchange(
                        URL + "/list",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Zbor>>(){});
        return new ApiResponse<>(HttpStatus.OK.value(), "",restExchange.getBody() );

    }
    @GetMapping("/addRezervare/{id}")
    public Iterable<Rezervare> rezerva(@PathVariable("id") Integer id) {
        Zbor z = restTemplate.exchange(
                URL +"/list",
                HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Zbor>>(){}).getBody().stream().filter(x -> x.getId() == id).findFirst().get();
        String random = UUID.randomUUID().toString();
        Rezervare rezervare = new Rezervare();
        rezervare.setId(random);
        rezervare.setZbor(z);
         repository.save(rezervare);
       return repository.findAll();

    }
    @GetMapping("/addCumparare/{id}")
    public Iterable<Cumparare> cumpara(@PathVariable("id") Integer id) {
        Zbor z = restTemplate.exchange(
                URL + "/list",
                HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Zbor>>(){}).getBody().stream().filter(x -> x.getId() == id).findFirst().get();
        String random = UUID.randomUUID().toString();
        Cumparare rezervare = new Cumparare();
        rezervare.setId(random);
        rezervare.setZbor(z);
        restTemplate.exchange(
                URL + "/cumpara/" + id,
                HttpMethod.GET,
                null, ApiResponse.class);
        cumparateRepository.save(rezervare);
        return cumparateRepository.findAll();

    }
        @GetMapping("/findAllRez")
    public ApiResponse<Iterable<Rezervare>> findById() {
       return new ApiResponse<>(HttpStatus.OK.value(), "", repository.findAll());

    }


   @GetMapping("/cumparate")
       public ApiResponse<List<Cumparare>> getCumparate() {
       return new ApiResponse<>(HttpStatus.OK.value(), "",cumparateRepository.findAll());
       }
    @DeleteMapping("/anuleaza/{id}")
    public ApiResponse<Iterable<Rezervare>> deleteById(@PathVariable("id") String id) {
        repository.delete(repository.findById(id).get());

        return new ApiResponse<>(HttpStatus.OK.value(), "", repository.findAll());
    }

    @GetMapping("/cumparaDinRezervate/{id}")
    public ApiResponse<Iterable<Rezervare>> cumparaDinRezervate(@PathVariable("id") String id) {
        Rezervare rezervare = repository.findById(id).get();
        cumpara(rezervare.getZbor().getId());
        repository.delete(rezervare);
        return new ApiResponse<>(HttpStatus.OK.value(), "", repository.findAll());
    }
}