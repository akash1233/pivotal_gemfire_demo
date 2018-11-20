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
	
	PersonEntity abk = new PersonEntity("Abhishek Kothari", 1001);
    PersonEntity sumit = new PersonEntity("Sumit Punjabi", 1002);
    PersonEntity john = new PersonEntity("John Doe", 999);
     
      	
    @Autowired
    PersonRepo personRepository;
    
    //@RequestMapping("/")
    @GetMapping("/test")
    public String getAllHealthRecord() {
    	
    	System.out.println("Lookup each person by name...");

        asList(abk.getName(), sumit.getName(), john.getName())
        .forEach(name -> System.out.println("\t" + personRepository.findByName(name)));

    	 System.out.println("Query adults (over 18):");

         stream(personRepository.findByHealthrecordGreaterThan(1000).spliterator(), false)
           .forEach(person -> System.out.println("\t" + person));

         System.out.println("Query teens (less than 999):");

         stream(personRepository.findByHealthrecordLessThan(999).spliterator(), false)
           .forEach(person -> System.out.println("\t" + person));

         System.out.println("Query teens (between 100 and 1000):");

       
        return "SUCCESS";
         
    }
    
    
    @GetMapping("/byname/{name}")
    public PersonEntity getHealthRecordByName(@RequestParam(name="name", required=false, defaultValue="John Doe") String name) {
    	
    	PersonEntity values= personRepository.findByName(name);
    	return values;
    }
    
    
    /*@GetMapping("/healthrecord/{number}")
    public PersonEntity findByHealthrecordGreaterThan(@RequestParam(name="number", required=false, defaultValue="1000") String number) {
    
    	PersonEntity values= personRepository.findByHealthrecordGreaterThan(number);
    	return values;
         
   } */
    
   
    
    }



	
	
