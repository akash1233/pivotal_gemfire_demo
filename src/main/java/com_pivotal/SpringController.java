package com_pivotal;

import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringController {


    @Autowired
    PersonRepo personRepository;

    @GetMapping("/healthrecord")
    public Iterable<PersonEntity> findByHealthrecordGreaterThan(@RequestParam(required = false, defaultValue = "1000") int number) {
        Iterable<PersonEntity> values = personRepository.findAll();
        return values;
    }
}



	
	
