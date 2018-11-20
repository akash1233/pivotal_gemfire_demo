package com_pivotal;
import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;

import java.io.IOException;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ClientCacheApplication(name = "AccessingDataGemFireApplication", logLevel = "error")
@EnableEntityDefinedRegions(basePackageClasses = PersonEntity.class,
  clientRegionShortcut = ClientRegionShortcut.LOCAL)

@EnableGemfireRepositories

public class App {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(App.class, args);
    }

    PersonEntity abk = new PersonEntity("Abhishek Kothari", 1001);
    PersonEntity sumit = new PersonEntity("Sumit Punjabi", 1002);
    PersonEntity john = new PersonEntity("John Doe", 999);
    
    @Bean
    ApplicationRunner run(PersonRepo personRepository) {

        return args -> {
            System.out.println("Entering into accessing data from Pivotal GemFire framework");
            asList(abk, sumit, john).forEach(person -> System.out.println("\t" + person));
            System.out.println("Saving Alice, Bob and Carol to Pivotal GemFire...");

            personRepository.save(abk);
            personRepository.save(sumit);
            personRepository.save(john);
           
            getDetails(personRepository);
    };       
    }
    
    
    public void getDetails(PersonRepo personRepository) {
    	
    	System.out.println("Lookup each person by name...");

        asList(abk.getName(), sumit.getName(), john.getName())
        .forEach(name -> System.out.println("\t" + personRepository.findByName(name)));

      System.out.println("Query for records (over 1000):");

      stream(personRepository.findByHealthrecordGreaterThan(1000).spliterator(), false)
        .forEach(person -> System.out.println("\t" + person));

      System.out.println("Query for records (less than 999):");

      stream(personRepository.findByHealthrecordLessThan(999).spliterator(), false)
        .forEach(person -> System.out.println("\t" + person));

      System.out.println("Query teens (between 100 and 1000):");

//      stream(personRepository.findByHealthrecordonRangeBetween(100, 1000).spliterator(), false)
//        .forEach(person -> System.out.println("\t" + person));
//    
    }
   
}