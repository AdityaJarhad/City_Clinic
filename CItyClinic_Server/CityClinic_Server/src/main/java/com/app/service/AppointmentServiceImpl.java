package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.AppointmentDTO;
import com.app.dto.BookedAppointmentDTO;
import com.app.dto.DetailedAppointmentDTO;
import com.app.dto.PatientInfo;
import com.app.entity.Appointment;
import com.app.entity.Doctor;
import com.app.entity.Patient;
import com.app.entity.User;
import com.app.repository.IAppointmentRepository;
import com.app.repository.IDoctorRepository;
import com.app.repository.IPatientRepository;
import com.app.repository.IUserRepository;


@SuppressWarnings("deprecation")
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private IAppointmentRepository appointmentRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private IPatientRepository patientRepo;

	@Autowired
	private IUserRepository userrepo;

	@Autowired
	private IDoctorRepository doctorRepo;
	
	@Autowired
    private EmailService emailService;

	@Override
	public AppointmentDTO createAppointment(AppointmentDTO appointmentDto) {

	
		User user = userrepo.getById(appointmentDto.getPatientId());
		Patient patient = patientRepo.findByUser(user);
		appointmentDto.setPatientId(patient.getId());

		Doctor doctor = doctorRepo.findById(appointmentDto.getDoctorId())
				.orElseThrow(() -> new RuntimeException("Doctor not found"));

		System.out.println("________" + appointmentDto);

		Appointment appointment = mapper.map(appointmentDto, Appointment.class);
		appointment.setDoctor(doctor); // Set the doctor
		appointment.setPatient(patient); // Set the patient
		appointment = appointmentRepo.save(appointment);

		return mapper.map(appointment, AppointmentDTO.class);
	}

	@Override
	public AppointmentDTO updateAppointment(Long appointmentId, AppointmentDTO appointmentDto) {
	    Appointment appointment = appointmentRepo.findById(appointmentId)
	            .orElseThrow(() -> new RuntimeException("Appointment not found with id : " + appointmentId));

	    
	    appointment.setStatus(appointmentDto.getStatus());

	    appointment = appointmentRepo.save(appointment); 
	    
	 // Send email notification to the patient
        Patient patient = appointment.getPatient();
        String patientEmail = patient.getUser().getEmail();
        String subject = "Appointment Status Update";
        String body = "Dear " + patient.getUser().getName() + ",\n\n" +
                      "Your appointment status has been updated to: " + appointment.getStatus() + ".\n\n" +
                      "Thank you,\nCity Clinic System";

        emailService.sendSimpleEmail(patientEmail, subject, body);


	    return mapper.map(appointment, AppointmentDTO.class);
	}

	@Override
	public AppointmentDTO getAppointmentById(Long appointmentId) {
		Appointment appointment = appointmentRepo.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found with id : " + appointmentId));
		
		
		return mapper.map(appointment, AppointmentDTO.class);
	}

	@Override
	public List<AppointmentDTO> getAllAppointments() {
		List<Appointment> appointments = appointmentRepo.findAll();

		return appointments.stream().map(appointment -> mapper.map(appointment, AppointmentDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepo.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found with id : " + appointmentId));
		appointmentRepo.delete(appointment);

	}
	
	@Override
	public List<DetailedAppointmentDTO> getAppointmentsByDoctorId(Long doctorId) {
		User user = userrepo.getById(doctorId);
		Optional<Doctor> doctor = doctorRepo.findByUser(user);
	    List<Appointment> appointments = appointmentRepo.findAppointmentsByDoctorId(doctor.get().getId());

	    return appointments.stream()
	            .map(appointment -> {
	                // Fetch patient details
	                Patient patient = appointment.getPatient();
	                User patientUser = patient.getUser(); // Access the User details from Patient

	                // Map appointment and patient details to DetailedAppointmentDTO
	                return new DetailedAppointmentDTO(
	                        appointment.getId(),
	                        appointment.getDoctor().getId(),
	                        appointment.getAppointmentDate(),
	                        appointment.getStatus(),
	                        patient.getId(), // Patient ID
	                        patientUser.getName(), // Patient name
	                        patientUser.getEmail(), // Patient email
	                        patientUser.getContactNumber(), // Patient contact number
	                        patientUser.getAddress() // Patient address
	                );
	            })
	            .collect(Collectors.toList());
	}

	@Override
    public List<BookedAppointmentDTO> getBookedAppointmentsByPatientId(Long patientId) {
		
	
		
        Patient patient = patientRepo.findByUserId(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id : " + patientId));

        List<Appointment> appointments = appointmentRepo.findByPatient(patient);

        return appointments.stream().map(appointment -> {
            BookedAppointmentDTO dto = new BookedAppointmentDTO();
            dto.setId(appointment.getId());
            dto.setDoctorName(appointment.getDoctor().getUser().getName());
            dto.setClinicName(appointment.getDoctor().getClinic().getName());
            dto.setAppointmentDate(appointment.getAppointmentDate());
            dto.setStatus(appointment.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }
	
	
	public List<DetailedAppointmentDTO> getAppointmentsByDoctorIdAndDate(Long doctorId, LocalDate date) {
		
		User user = userrepo.getById(doctorId);
		Optional<Doctor> doctor = doctorRepo.findByUser(user);
        List<Appointment> appointments = appointmentRepo.findAppointmentsByDoctorIdAndDate(doctor.get().getId(), date);
        System.out.println("Fetching appointments for doctorId: " + doctorId + " on date: " + date);
        appointments.forEach(a->System.out.println(a.getStatus()));
        return appointments.stream()
	            .map(appointment -> {
	                // Fetch patient details
	                Patient patient = appointment.getPatient();
	                User patientUser = patient.getUser(); // Access the User details from Patient

	                // Map appointment and patient details to DetailedAppointmentDTO
	                return new DetailedAppointmentDTO(
	                        appointment.getId(),
	                        appointment.getDoctor().getId(),
	                        appointment.getAppointmentDate(),
	                        appointment.getStatus(),
	                        patient.getId(), // Patient ID
	                        patientUser.getName(), // Patient name
	                        patientUser.getEmail(), // Patient email
	                        patientUser.getContactNumber(), // Patient contact number
	                        patientUser.getAddress() // Patient address
	                );
	            })
	            .collect(Collectors.toList());
    }

    public List<DetailedAppointmentDTO> getAppointmentsByDoctorIdAndDateRange(Long doctorId, LocalDate startDate, LocalDate endDate) {
    	User user = userrepo.getById(doctorId);
		Optional<Doctor> doctor = doctorRepo.findByUser(user);
        List<Appointment> appointments = appointmentRepo.findAppointmentsByDoctorIdAndDateRange(doctor.get().getId(), startDate, endDate);
        return appointments.stream()
	            .map(appointment -> {
	                // Fetch patient details
	                Patient patient = appointment.getPatient();
	                User patientUser = patient.getUser(); // Access the User details from Patient

	                // Map appointment and patient details to DetailedAppointmentDTO
	                return new DetailedAppointmentDTO(
	                        appointment.getId(),
	                        appointment.getDoctor().getId(),
	                        appointment.getAppointmentDate(),
	                        appointment.getStatus(),
	                        patient.getId(), // Patient ID
	                        patientUser.getName(), 
	                        patientUser.getEmail(), 
	                        patientUser.getContactNumber(), 
	                        patientUser.getAddress() 
	                );
	            })
	            .collect(Collectors.toList());
    }
    

    
    public List<PatientInfo> getPatientsByDoctorId(Long doctorId) {
    	User user = userrepo.getById(doctorId);
		Optional<Doctor> doctor = doctorRepo.findByUser(user);
        List<Appointment> appointments = appointmentRepo.findAppointmentsByDoctorId(doctor.get().getId());
        Set<Patient> patients = appointments.stream()
                .map(Appointment::getPatient)
                .collect(Collectors.toSet());
        return patients.stream()
                .map(patient -> new PatientInfo(patient.getId(), patient.getUser().getName(), patient.getUser().getContactNumber()))
                .collect(Collectors.toList());
    }
    
    
    public PatientInfo getPatientInfoByAppointmentId(Long appointmentId, Long doctorId) {
    	User user = userrepo.getById(doctorId);
		Optional<Doctor> doctor = doctorRepo.findByUser(user);
        Appointment appointment = appointmentRepo.findByIdAndDoctorId(appointmentId,doctor.get().getId());
        if (appointment == null) {
            throw new ResourceNotFoundException("Appointment with ID " + appointmentId + " and Doctor ID " + doctorId + " not found.");
        }
        Long patientId = appointment.getPatient().getId();
        Patient patient = patientRepo.findById(patientId)
                                           .orElseThrow(() -> new ResourceNotFoundException("Patient with ID " + patientId + " not found."));
        
        return new PatientInfo(patient.getId(), patient.getUser().getName(), patient.getUser().getContactNumber());
    }
    
}
