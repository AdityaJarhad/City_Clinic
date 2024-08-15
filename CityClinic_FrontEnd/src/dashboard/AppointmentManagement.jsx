// src/dashboard/AppointmentManagement.jsx
import React, { useEffect, useState } from 'react';

const AppointmentManagement = () => {
    const [appointments, setAppointments] = useState([]);

    useEffect(() => {
        const fetchAppointments = async () => {
            const response = await fetch('http://localhost:8080/appointments');
            const data = await response.json();
            setAppointments(data);
        };
        fetchAppointments();
    }, []);

    return (
        <div>
            <h2>Appointment Management</h2>
            <ul>
                {appointments.map(appointment => (
                    <li key={appointment.id}>
                        {appointment.date} - {appointment.patientName}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AppointmentManagement;
