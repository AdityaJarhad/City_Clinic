# City_Clinic

--Spring_Boot_Server
A . Entities
1. patient -> med_history  oneToMany    (Change)
2. Rating (Long rating); 
3. can we change manyToOne relation for Location And clinic

B. dtos
1. patientDTO -> med_history  oneToMany    (Change)
2. RatingDTO (Long rating); 
	and rating is 1 - 10
3. AppointmentDTO -> @FutureOrPresent 

C. Repository
1. List<MedicalHistory> findByDoctor(Doctor doctor);  ///check for optional
2. No need of IPatientRepository
3. chk IReviewRepository
4. chk IUserRepository to improve more


D. UserServiceImpl -> 
 1.   @Autowired
    private ModelMapper mapper;