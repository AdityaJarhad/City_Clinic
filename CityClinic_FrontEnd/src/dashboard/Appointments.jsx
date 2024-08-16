import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Appointments = () => {
    const [appointments, setAppointments] = useState([]);
    const [loading, setLoading] = useState(true);
    const [filter, setFilter] = useState('all');
    const [user, setUser] = useState(JSON.parse(localStorage.getItem("user"))); // Get the logged-in user details

    useEffect(() => {
        const fetchAppointments = async () => {
            if (user) {
                setLoading(true); // Set loading to true when fetching
                let url = `https://localhost:8080/api/appointment/doctor/${user.user.id}`;
                if (filter === 'today') {
                    url = `https://localhost:8080/api/appointment/doctor/${user.user.id}/today`;
                } else if (filter === 'week') {
                    url = `https://localhost:8080/api/appointment/doctor/${user.user.id}/week`;
                }
                try {
                    const response = await axios.get(url);
                    setAppointments(response.data);
                } catch (error) {
                    console.error("Error fetching appointments:", error);
                } finally {
                    setLoading(false); // Set loading to false after fetching
                }
            }
        };

        fetchAppointments();
    }, [filter, user]);

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
                patientId: appointment.patientId, 
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
            <div className="mb-4">
                <label htmlFor="filter" className="mr-2 text-lg font-medium text-gray-700">Filter:</label>
                <select
                    id="filter"
                    value={filter}
                    onChange={(e) => setFilter(e.target.value)}
                    className="border p-2 rounded"
                >
                    <option value="all">All</option>
                    <option value="today">Today</option>
                    <option value="week">This Week</option>
                </select>
            </div>
            <table className="w-full table-auto border-collapse border border-gray-200">
                <thead>
                    <tr>
                        <th className="border border-gray-300 p-2">ID</th>
                        <th className="border border-gray-300 p-2">Patient</th>
                        <th className="border border-gray-300 p-2">Patient Contact Number</th>
                        <th className="border border-gray-300 p-2">Patient Email</th>
                        <th className="border border-gray-300 p-2">Date</th>
                        <th className="border border-gray-300 p-2">Status</th>
                        <th className="border border-gray-300 p-2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    {appointments.map(appointment => (
                        <tr key={appointment.appointmentId}>
                            <td className="border border-gray-300 p-2">{appointment.appointmentId}</td>
                            <td className="border border-gray-300 p-2">{appointment.patientName}</td>
                            <td className="border border-gray-300 p-2">{appointment.patientContactNumber}</td>
                            <td className="border border-gray-300 p-2">{appointment.patientEmail}</td>
                            <td className="border border-gray-300 p-2">{new Date(appointment.appointmentDate).toLocaleDateString()}</td>
                            <td className="border border-gray-300 p-2">{appointment.status}</td>
                            <td className="border border-gray-300 p-2">
                                <button
                                    onClick={() => handleUpdateStatus(appointment.appointmentId)}
                                    className="bg-blue-500 text-white p-2 rounded"
                                >
                                    Update Status
                                </button>
                                <select
                                    value={appointment.newStatus || appointment.status}
                                    onChange={(e) => handleStatusChange(appointment.appointmentId, e.target.value)}
                                    className="ml-2 border p-1 rounded"
                                >
                                    <option value="Confirmed">Confirmed</option>
                                    <option value="Completed">Completed</option>
                                    <option value="Cancelled">Cancelled</option>
                                </select>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default Appointments;
