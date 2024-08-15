import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';

const Appointments = () => {
    const [appointments, setAppointments] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchAppointments = async () => {
            try {
                const response = await axios.get('https://localhost:8080/api/appointments');
                setAppointments(response.data);
            } catch (error) {
                console.error('Error fetching appointments:', error);
                toast.error('Failed to fetch appointments.');
            } finally {
                setLoading(false);
            }
        };

        fetchAppointments();
    }, []);

    const handleStatusChange = async (id, newStatus) => {
        try {
            await axios.patch(`https://localhost:8080/api/appointments/${id}`, { status: newStatus });
            setAppointments(appointments.map(app =>
                app.id === id ? { ...app, status: newStatus } : app
            ));
            toast.success('Appointment status updated.');
        } catch (error) {
            console.error('Error updating status:', error);
            toast.error('Failed to update status.');
        }
    };

    if (loading) return <div>Loading...</div>;

    return (
        <div className="container mx-auto p-20">
            <h1 className="text-2xl font-bold mb-4">Manage Appointments</h1>
            <div className="overflow-x-auto">
                <table className="min-w-full divide-y divide-gray-200">
                    <thead className="bg-gray-50">
                        <tr>
                            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date</th>
                            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Doctor ID</th>
                            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Patient ID</th>
                            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
                            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
                        </tr>
                    </thead>
                    <tbody className="bg-white divide-y divide-gray-200">
                        {appointments.map((appointment) => (
                            <tr key={appointment.id}>
                                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                    {new Date(appointment.appointment_date).toLocaleDateString()}
                                </td>
                                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{appointment.doctor_id}</td>
                                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{appointment.patient_id}</td>
                                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{appointment.status}</td>
                                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                    <button
                                        onClick={() => handleStatusChange(appointment.id, 'Confirmed')}
                                        className="text-blue-500 hover:text-blue-700"
                                    >
                                        Confirm
                                    </button>
                                    <button
                                        onClick={() => handleStatusChange(appointment.id, 'Cancelled')}
                                        className="ml-4 text-red-500 hover:text-red-700"
                                    >
                                        Cancel
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
