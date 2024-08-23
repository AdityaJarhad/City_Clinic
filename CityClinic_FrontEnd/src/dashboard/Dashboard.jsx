
import React, { useEffect } from 'react';

import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    if (!user) {
      navigate('/login'); // Redirect to login if user is not logged in
    }
  }, [user, navigate]);

  const role = user?.user?.role; // Optional chaining to avoid errors

  if (role === "DOCTOR") {
    return (
      <div className="p-6 mt-20 bg-gradient-to-r from-teal-200 to-blue-300">
        <h1 className="text-4xl font-bold mb-6 text-center text-gray-800">Welcome, Doctor</h1>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {/* New Clinic Card */}
          <div className="bg-white shadow-lg rounded-lg p-6 transition-transform transform hover:scale-105 cursor-pointer" onClick={() => navigate('/dashboard/clinic')}>
            <h2 className="text-xl font-semibold text-blue-600">Clinic</h2>
            <p className="text-gray-700">Manage your clinic details and information.</p>
          </div>
          <div className="bg-white shadow-lg rounded-lg p-6 transition-transform transform hover:scale-105 cursor-pointer" onClick={() => navigate('/dashboard/doctor-patients')}>
            <h2 className="text-xl font-semibold text-blue-600">Patients</h2>
            <p className="text-gray-700">Search and manage your patients' information.</p>
          </div>
          <div className="bg-white shadow-lg rounded-lg p-6 transition-transform transform hover:scale-105 cursor-pointer" onClick={() => navigate('/dashboard/appointments')}>
            <h2 className="text-xl font-semibold text-blue-600">Appointments</h2>
            <p className="text-gray-700">View and manage appointments.</p>
          </div>
        </div>
      </div>
    );
  }

  if (role === "PATIENT") {
    return (
      <div className="p-6 mt-20 bg-gradient-to-r from-teal-200 to-blue-300">
        <h1 className="text-4xl font-bold mb-6 text-center text-gray-800">Welcome</h1>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          <div className="bg-white shadow-lg rounded-lg p-6 transition-transform transform hover:scale-105 cursor-pointer" onClick={() => navigate('/dashboard/patient-medical-history')}>
            <h2 className="text-xl font-semibold text-blue-600">Medical History</h2>
            <p className="text-gray-700">View your medical history.</p>
          </div>
          <div className="bg-white shadow-lg rounded-lg p-6 transition-transform transform hover:scale-105 cursor-pointer" onClick={() => navigate('/dashboard/doctors')}>
            <h2 className="text-xl font-semibold text-blue-600">Book Appointment</h2>
            <p className="text-gray-700">View your upcoming appointments.</p>
          </div>
          <div className="bg-white shadow-lg rounded-lg p-6 transition-transform transform hover:scale-105 cursor-pointer" onClick={() => navigate('/dashboard/booked-appointments')}>
            <h2 className="text-xl font-semibold text-blue-600">Booked Appointments</h2>
            <p className="text-gray-700">See your booked appointments.</p>
          </div>
        </div>
      </div>
    );
  }

  return <p className="text-center text-red-500">Unauthorized</p>;
};

export default Dashboard;
