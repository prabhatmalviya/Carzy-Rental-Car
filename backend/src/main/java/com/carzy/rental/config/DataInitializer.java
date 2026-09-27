package com.carzy.rental.config;

import com.carzy.rental.entity.Car;
import com.carzy.rental.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedCars(CarRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                    new Car("Maruti", "Swift", 2024, new BigDecimal("1800"), true, "Petrol", "Manual"),
                    new Car("Hyundai", "Creta", 2024, new BigDecimal("2800"), true, "Petrol", "Automatic"),
                    new Car("Tata", "Nexon", 2023, new BigDecimal("2200"), true, "Diesel", "Manual"),
                    new Car("Toyota", "Innova", 2024, new BigDecimal("3500"), true, "Diesel", "Automatic")
                ));
            }
        };
    }
}
