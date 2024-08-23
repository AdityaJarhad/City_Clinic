import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const DoctorListForAppointments = () => {
  const [doctors, setDoctors] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetch('/api/doctors')
      .then((response) => response.json())
      .then((data) => {
        console.log(data); // Log the data to inspect the structure
        setDoctors(data);
      })
      .catch((error) => console.error('Error fetching doctor data:', error));
  }, []);

  return (
    <div className="p-6 mt-20 bg-gradient-to-r from-teal-200 to-blue-300">
      <h1 className="text-4xl font-bold mb-6 text-center text-gray-800">Available Doctors</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {doctors.map((doctor) => (
          <div key={doctor.id} className="bg-white shadow-lg rounded-lg p-6">
            <h2 className="text-xl font-semibold text-blue-600">{doctor.clinic.name}</h2>
            <p className="text-gray-700">Qualifications: {doctor.qualifications}</p>
            <p className="text-gray-700">Experience: {doctor.experience} years</p>
            <p className="text-gray-700">Specialization: {doctor.specialization.name}</p>
            <p className="text-gray-700">
              Location: {doctor.clinic.location.address}, {doctor.clinic.location.city}, {doctor.clinic.location.state}, {doctor.clinic.location.zip_code}
            </p>
            <p className="text-gray-700">Country: {doctor.clinic.location.country}</p>
            <p className="text-gray-700">Contact: {doctor.clinic.moNo}</p>
            <p className="text-gray-700">Email: {doctor.clinic.email}</p>
            <p className="text-gray-700">Schedule: {doctor.availabilitySchedule}</p>
            <button 
              className="mt-4 bg-blue-500 text-white px-4 py-2 rounded"
              onClick={() => handleBookAppointment(doctor)} // Pass the entire doctor object
            >
              Book Appointment
            </button>
          </div>
        ))}
      </div>
    </div>
  );

  function handleBookAppointment(doctor) {
    // Navigate to the appointment booking page with the doctor object only
    navigate(`/book-appointment`, { state: { doctor } });
  }
};

export default DoctorListForAppointments;
