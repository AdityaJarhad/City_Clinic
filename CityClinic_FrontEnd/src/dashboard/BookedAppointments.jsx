import React, { useEffect, useState } from 'react';
import axios from 'axios';

const BookedAppointments = () => {
    const [appointments, setAppointments] = useState([]);
    const user = JSON.parse(localStorage.getItem("user"));
    useEffect(() => {
        // Assuming the patient's ID is stored in localStorage after login
        const patientId = user.user.id;
        axios.get(`https://localhost:8080/api/appointment/booked/${patientId}`)
            .then(response => {
                setAppointments(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the appointments!', error);
            });
    }, []);

    return (
        <div className="p-6 mt-20 bg-gradient-to-r from-teal-200 to-blue-300">
        <h2 className="text-4xl font-bold mb-6 text-center text-gray-800">My Booked Appointments</h2>
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            {appointments.length > 0 ? (
                appointments.map(appointment => (
                    <div key={appointment.id} className="bg-white shadow-sm rounded-lg p-4">
                        <h3 className="text-xl font-semibold text-gray-800">Doctor: {appointment.doctorName}</h3>
                        <p className="text-gray-700 mt-2">Clinic: {appointment.clinicName}</p>
                        <p className="text-gray-700 mt-2">Date: {new Date(appointment.appointmentDate).toLocaleDateString()}</p>
                        <p className="text-gray-700 mt-2">Status: 
                        <span className={`ml-2 font-medium p-1 rounded ${appointment.status.toLowerCase() === 'confirmed' ? 'bg-green-100 text-green-800' : 
                                    appointment.status.toLowerCase() === 'cancelled' ? 'bg-red-100 text-red-800' : 
                                    'bg-yellow-100 text-yellow-800'}`}>
                                    {appointment.status}
                                </span>
                        </p>
                    </div>
                ))
            ) : (
                <p className="text-center text-gray-500">No booked appointments found.</p>
            )}
        </div>
    </div>
);
};

export default BookedAppointments;
