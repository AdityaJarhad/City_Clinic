package com.app.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.app.entity.Specialization;
import com.app.repository.ISpecializationRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final ISpecializationRepository specializationRepository;

    public DataLoader(ISpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // List of specializations to add
        String[] specializations = {
            "Cardiology",
            "Pediatrics",
            "Dermatology",
            "Orthopedics",
            "Neurology",
            "General Medicine",
            "Gynecology",
            "Ophthalmology",
            "Psychiatry",
            "Gastroenterology"
        };

        for (String name : specializations) {
            if (specializationRepository.findByName(name) == null) {
                specializationRepository.save(new Specialization(name));
            }
        }
    }
}
