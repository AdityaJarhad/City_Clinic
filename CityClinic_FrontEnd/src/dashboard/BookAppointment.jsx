import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import axios from 'axios';

const BookAppointment = () => {
  const location = useLocation();
  const { doctor } = location.state;

  const [appointmentDate, setAppointmentDate] = useState('');
  const [status, setStatus] = useState('Pending');
  const [message, setMessage] = useState(''); // State for messages
  const [errorMessage, setErrorMessage] = useState(''); // State for error messages

  const today = new Date().toISOString().split('T')[0];

  const handleSubmit = async (e) => {
    e.preventDefault();

    const user = JSON.parse(localStorage.getItem("user")); 
    const patientId = user && user.user ? user.user.id : 0; 

    const appointmentData = {
      patientId,
      doctorId: doctor.id,
      appointmentDate,
      status,
    };

    console.log('Appointment Data:', appointmentData);

    try {
      const response = await axios.post('/api/appointment/post', appointmentData);
      console.log('Appointment booked:', response.data);
      setMessage("Appointment booked successfully!");
      setErrorMessage(''); // Clear any previous error messages
    } catch (error) {
      console.error('Error booking appointment:', error.response ? error.response.data : error.message);
      setErrorMessage("Error booking appointment. Please try again.");
      setMessage(''); // Clear any previous success messages
    }
  };

  return (
    <div className="p-6 mt-20 bg-white shadow-lg rounded-lg">
      <h1 className="text-2xl font-bold mb-4">Book Appointment</h1>
      
      <div className="mb-4">
        <p><strong>Doctor Name:</strong> {doctor.clinic.name}</p>
        <p><strong>Specialization:</strong> {doctor.specialization.name}</p>
      </div>

      <form onSubmit={handleSubmit}>
        <div className="mb-4">
          <label htmlFor="appointmentDate" className="block text-gray-700">Appointment Date</label>
          <input 
            type="date" 
            id="appointmentDate" 
            value={appointmentDate} 
            onChange={(e) => setAppointmentDate(e.target.value)} 
            required 
            min={today} 
            className="mt-1 block w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Status</label>
          <input 
            type="text" 
            value={status} 
            readOnly 
            className="mt-1 block w-full p-2 border border-gray-300 rounded bg-gray-100"
          />
        </div>
        <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">
          Confirm Appointment
        </button>
      </form>

      {/* Display success or error messages */}
      {message && <div className="mt-4 text-green-600">{message}</div>}
      {errorMessage && <div className="mt-4 text-red-600">{errorMessage}</div>}
    </div>
  );
};

export default BookAppointment;
