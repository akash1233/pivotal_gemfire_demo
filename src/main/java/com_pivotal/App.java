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

    PersonEntity obj1 = new PersonEntity("John1", 1001);
    PersonEntity obj2 = new PersonEntity("John2", 1002);
    PersonEntity obj3 = new PersonEntity("John3", 1003);

    @Bean
    ApplicationRunner run(PersonRepo personRepository) {
        return args -> {
            personRepository.save(obj1);
            personRepository.save(obj2);
            personRepository.save(obj3);

        };
    }

}
