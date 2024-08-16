import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Appointments = () => {
    const navigate = useNavigate();
    const [appointments, setAppointments] = useState([]);
    const [loading, setLoading] = useState(true);
    const [fetched, setFetched] = useState(false);
    const user = JSON.parse(localStorage.getItem("user")); // Get the logged-in user details

    useEffect(() => {
        if (user && !fetched) {
            console.log("Logged-in user:", user);
            fetchAppointments(user.user.id); // Use the logged-in user's ID as doctorId
            setFetched(true); // Set fetched to true after fetching data
        }
    }, [user, fetched]);

    const fetchAppointments = async (doctorId) => {
        try {
            console.log("Fetching appointments for doctorId:", doctorId);
            const response = await axios.get(`https://localhost:8080/api/appointment/doctor/${doctorId}`); // Replace with your backend endpoint
            setAppointments(response.data);
            setLoading(false);
        } catch (error) {
            console.error("Error fetching appointments:", error);
            setLoading(false);
        }
    };

    const handleStatusChange = (appointmentId, status) => {
        setAppointments(prevAppointments => 
            prevAppointments.map(appointment => 
                appointment.appointmentId === appointmentId 
                    ? { ...appointment, newStatus: status } 
                    : appointment
            )
        );
    };

    const handleUpdateStatus = async (appointmentId) => {
        const appointment = appointments.find(app => app.appointmentId === appointmentId);
        try {
            await axios.put(`https://localhost:8080/api/appointment/${appointmentId}`, {
                patientId: appointment.patientId,  // Ensure these fields are included
                doctorId: appointment.doctorId,
                appointmentDate: appointment.appointmentDate,
                status: appointment.newStatus
            });
            setAppointments(prevAppointments => 
                prevAppointments.map(app => 
                    app.appointmentId === appointmentId 
                        ? { ...app, status: app.newStatus }
                        : app
                )
            );
        } catch (error) {
            console.error("Error updating appointment status:", error);
        }
    };
    

    if (loading) {
        return <p className="text-center text-gray-500">Loading...</p>;
    }

    return (
        <div className="p-6 mt-20 bg-gradient-to-r from-teal-200 to-blue-300">
            <h1 className="text-4xl font-bold mb-6 text-center text-gray-800">Manage Appointments</h1>
            <div className="overflow-x-auto bg-white shadow-sm rounded-lg">
                <table className="min-w-full divide-y divide-gray-300">
                    <thead className="bg-gray-200">
                        <tr>
                            <th className="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Date</th>
                            <th className="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Patient Name</th>
                            <th className="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Patient Contact Number</th>
                            <th className="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Patient Email</th>
                            <th className="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Status</th>
                            <th className="px-4 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Actions</th>
                        </tr>
                    </thead>
                    <tbody className="bg-white divide-y divide-gray-300">
                        {appointments.map((appointment) => (
                            <tr key={appointment.appointmentId}>
                                <td className="px-4 py-4 text-sm font-medium text-gray-900">
                                    {new Date(appointment.appointmentDate).toLocaleDateString()}
                                </td>
                                <td className="px-4 py-4 text-sm text-gray-700">{appointment.patientName}</td>
                                <td className="px-4 py-4 text-sm text-gray-700">{appointment.patientContactNumber}</td>
                                <td className="px-4 py-4 text-sm text-gray-700">{appointment.patientEmail}</td>
                                <td className="px-4 py-4 text-sm text-gray-700">
                                    <select
                                        value={appointment.newStatus || appointment.status}
                                        onChange={(e) => handleStatusChange(appointment.appointmentId, e.target.value)}
                                        className="border p-1 rounded"
                                    >
                                        <option value="Pending">Pending</option>
                                        <option value="Confirmed">Confirmed</option>
                                        <option value="Cancelled">Cancelled</option>
                                    </select>
                                </td>
                                <td className="px-4 py-4 text-sm text-gray-700">
                                    <button
                                        onClick={() => handleUpdateStatus(appointment.appointmentId)}
                                        className="text-blue-600 hover:text-blue-800 font-medium"
                                    >
                                        Update
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Appointments;
