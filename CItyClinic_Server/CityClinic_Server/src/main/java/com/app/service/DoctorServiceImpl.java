package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.DoctorRegistrationRequest;
import com.app.entity.Clinic;
import com.app.entity.Doctor;
import com.app.entity.Location;
import com.app.entity.Specialization;
import com.app.entity.User;
import com.app.repository.IClinicRepository;
import com.app.repository.IDoctorRepository;
import com.app.repository.ILocationRepository;
import com.app.repository.ISpecializationRepository;
import com.app.repository.IUserRepository;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private IDoctorRepository doctorRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISpecializationRepository specializationRepository;

    @Autowired
    private IClinicRepository clinicRepository;

    @Autowired
    private ILocationRepository locationRepository;
    
    @Autowired
    private IUserRepository UserRepository;

    @Override
    @Transactional
    public void registerDoctor(DoctorRegistrationRequest request) {
        // Step 1: Create and save Location
        Location location = new Location();
        location.setAddress(request.getLocationAddress());
        location.setCity(request.getLocationCity());
        location.setState(request.getLocationState());
        location.setZip_code(request.getLocationZipCode());
        location.setCountry(request.getLocationCountry());
        locationRepository.save(location);

        // Step 2: Create and save Clinic
        Clinic clinic = new Clinic();
        clinic.setName(request.getClinicName());
        clinic.setMoNo(request.getClinicMoNo());
        clinic.setEmail(request.getClinicEmail());
        clinic.setDescription(request.getClinicDescription());
        clinic.setLocation(location); // Associate the clinic with the location
        clinicRepository.save(clinic);

        // Step 3: Find User and Specialization
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")); // Handle user not found case

        Specialization specialization = specializationRepository.findById(request.getSpecializationId())
                .orElseThrow(() -> new RuntimeException("Specialization not found")); // Handle specialization not found case

        // Step 4: Create and save Doctor
        Doctor doctor = new Doctor();
        doctor.setUser(user); // Set the user as a primary key
        doctor.setSpecialization(specialization); // Set the specialization
        doctor.setClinic(clinic); // Set the clinic
        doctor.setQualifications(request.getQualifications());
        doctor.setExperience(request.getExperience());
        doctor.setAvailabilitySchedule(request.getAvailabilitySchedule());
        doctor.setProfilePicture(request.getProfilePicture());
        
        doctorRepository.save(doctor); // Save the doctor entity
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
    
    

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public void updateDoctor(Long id, DoctorRegistrationRequest request) {
    	
    	
//        Doctor doctor = doctorRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Doctor not found"));

    	User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for user ID: " + id));
        
        Doctor doctor = doctorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Doctor not found for user ID: " + id));
        // Update Doctor details
        doctor.setQualifications(request.getQualifications());
        doctor.setExperience(request.getExperience());
        doctor.setAvailabilitySchedule(request.getAvailabilitySchedule());
        doctor.setProfilePicture(request.getProfilePicture());

        // If clinic or location needs to be updated
        // Example: Update clinic details if provided in the request
        if (request.getClinicName() != null) {
            Clinic clinic = doctor.getClinic();
            clinic.setName(request.getClinicName());
            clinic.setMoNo(request.getClinicMoNo());
            clinic.setEmail(request.getClinicEmail());
            clinic.setDescription(request.getClinicDescription());

            // Assuming location details are part of the request as well
            Location location = clinic.getLocation();
            location.setAddress(request.getLocationAddress());
            location.setCity(request.getLocationCity());
            location.setState(request.getLocationState());
            location.setZip_code(request.getLocationZipCode());
            location.setCountry(request.getLocationCountry());

            locationRepository.save(location);
            clinicRepository.save(clinic);
        }

        doctorRepository.save(doctor); // Save updated doctor entity
    }

    @Override
    @Transactional
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found");
        }
        doctorRepository.deleteById(id);
    }


    @Override
    public DoctorRegistrationRequest findByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for user ID: " + userId));
        
        Doctor doctor = doctorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Doctor not found for user ID: " + userId));
        
        // Populate DoctorRegistrationRequest from Doctor entity
        DoctorRegistrationRequest request = new DoctorRegistrationRequest();
        request.setUserId(doctor.getUser().getId());
        request.setQualifications(doctor.getQualifications());
        request.setExperience(doctor.getExperience());
        request.setAvailabilitySchedule(doctor.getAvailabilitySchedule());
        request.setProfilePicture(doctor.getProfilePicture());
        request.setSpecializationId(doctor.getSpecialization().getId());
        // Get clinic and its location details
        Clinic clinic = doctor.getClinic();
        if (clinic != null) {
            request.setClinicName(clinic.getName());
            request.setClinicMoNo(clinic.getMoNo());
            request.setClinicEmail(clinic.getEmail());
            request.setClinicDescription(clinic.getDescription());

            Location location = clinic.getLocation();
            if (location != null) {
                request.setLocationAddress(location.getAddress());
                request.setLocationCity(location.getCity());
                request.setLocationState(location.getState());
                request.setLocationZipCode(location.getZip_code());
                request.setLocationCountry(location.getCountry());
            }
        }

        return request;
    }

    
    
    
    
    
    
    
    
    @Override
    public List<DoctorRegistrationRequest> getAllDoctorRegistrations() {
        return doctorRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    
	
    // Method to convert Doctor entity to DoctorRegistrationRequest DTO
    private DoctorRegistrationRequest convertToDto(Doctor doctor) {
        DoctorRegistrationRequest dto = new DoctorRegistrationRequest();
        dto.setUserId(doctor.getUser().getId());
        dto.setSpecializationId(doctor.getSpecialization().getId());
        dto.setQualifications(doctor.getQualifications());
        dto.setExperience(doctor.getExperience());
        dto.setAvailabilitySchedule(doctor.getAvailabilitySchedule());
        dto.setProfilePicture(doctor.getProfilePicture());

        // Assuming that the Doctor entity has a Clinic entity associated with it
        dto.setClinicName(doctor.getClinic().getName());
        dto.setClinicMoNo(doctor.getClinic().getMoNo());
        dto.setClinicEmail(doctor.getClinic().getEmail());
        dto.setClinicDescription(doctor.getClinic().getDescription());

        // Assuming that the Doctor entity has a Location entity associated with it
        dto.setLocationAddress(doctor.getClinic().getLocation().getAddress());
        dto.setLocationCity(doctor.getClinic().getLocation().getCity());
        dto.setLocationState(doctor.getClinic().getLocation().getState());
        dto.setLocationZipCode(doctor.getClinic().getLocation().getZip_code());
        dto.setLocationCountry(doctor.getClinic().getLocation().getCountry());

        return dto;
    }

}



