import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const DoctorPatients = () => {
  const [patients, setPatients] = useState([]);
  const [searchId, setSearchId] = useState("");
  const [appointmentDetails, setAppointmentDetails] = useState(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    if (!user) {
      navigate('/login');
      return;
    }

    const fetchPatients = async () => {
      try {
        const response = await axios.get(`/api/appointment/doctor/${user.user.id}/patients`);
        setPatients(response.data);
      } catch (error) {
        console.error("Error fetching patients", error);
      } finally {
        setLoading(false);
      }
    };

    if (loading) {
      fetchPatients();
    }
  }, [user, navigate, loading]);

  const handleSearch = async () => {
    if (!searchId) return;
    try {
      const response = await axios.get(`/api/appointment/search?appointmentId=${searchId}&doctorId=${user.user.id}`);
      if (response.data) {
        setAppointmentDetails(response.data);
        console.log("Appointment details:", response.data);
      } else {
        console.warn("No data returned for the given search ID.");
      }
    } catch (error) {
      console.error("Error fetching appointment details", error);
    }
  };

  return (
    <div className="p-6 mt-20 bg-gradient-to-r from-teal-200 to-blue-300">
      <h1 className="text-4xl font-bold mb-6 text-center text-gray-800">Manage Patients</h1>
      
      {/* Search input and button */}
      <div className="mb-6">
        <input
          type="text"
          placeholder="Appointment ID"
          value={searchId}
          onChange={(e) => setSearchId(e.target.value)}
          className="border p-2 rounded"
        />
        <button onClick={handleSearch} className="ml-4 p-2 bg-blue-500 text-white rounded">Search</button>
      </div>

      {/* Display appointment details if available */}
      {appointmentDetails && (
        <div className="bg-white shadow-lg rounded-lg p-6 mb-6">
          <h2 className="text-xl font-semibold text-blue-600">Appointment Details</h2>
          <p>Patient: {appointmentDetails.name}</p>
          <p>Contact: {appointmentDetails.contactNumber}</p>
          <button onClick={() => navigate(`/dashboard/medical-history/${appointmentDetails.userId}`)} className="mt-4 p-2 bg-green-500 text-white rounded">View Medical History</button>
        </div>
      )}

      {/* Display loading message or list of patients */}
      {loading ? (
        <p>Loading patients...</p>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {patients.map((patient) => (
            <div key={patient.userId} className="bg-white shadow-lg rounded-lg p-6 transition-transform transform hover:scale-105 cursor-pointer">
              <h2 className="text-xl font-semibold text-blue-600">{patient.name}</h2>
              <p className="text-gray-700">Contact: {patient.contactNumber}</p>
              <button onClick={() => navigate(`/dashboard/medical-history/${patient.userId}`)} className="mt-4 p-2 bg-green-500 text-white rounded">View Medical History</button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};



export default DoctorPatients;
