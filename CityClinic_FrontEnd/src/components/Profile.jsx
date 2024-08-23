import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Profile = () => {
    const [user, setUser] = useState(null);
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        contactNumber: '',
        address: '',
        role: ''
    });
    const [showModal, setShowModal] = useState(false);
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState(''); // State for message
    const navigate = useNavigate();


    // useEffect(() => {
    //     const storedUser = localStorage.getItem("user");
    //     if (storedUser) {
    //         const userData = JSON.parse(storedUser).user;
    //         setUser(userData);
    //         setFormData({
    //             name: userData.name,
    //             email: userData.email,
    //             contactNumber: userData.contactNumber,
    //             address: userData.address,
    //             role: userData.role
    //         });
    //     }
    // }, []);

    useEffect(() => {
        const storedUser = localStorage.getItem("user");
        if (storedUser) {
            const userData = JSON.parse(storedUser).user;
            setUser(userData);
            setFormData({
                name: userData.name,
                email: userData.email,
                contactNumber: userData.contactNumber,
                address: userData.address,
                role: userData.role
            });
        } else {
            navigate('/login'); // Redirect to login if no user is found
        }
    }, [navigate]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleUpdate = () => {
        setShowModal(true);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleSubmit = async () => {
        try {
            const token = localStorage.getItem("token");
            const userId = user.id;

            const userData = { ...formData, password };

            const response = await axios.put(`/api/users/${userId}`, userData, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            // Update user state and local storage after successful update
            setUser(response.data);
            localStorage.setItem("user", JSON.stringify({ user: response.data }));

            setMessage("User details updated successfully!"); // Set success message
            setShowModal(false);
            setPassword('');
        } catch (error) {
            const errorMessage = error.response && error.response.data ? error.response.data.message : error.message;
            console.error('Error updating user:', errorMessage);

            // Set error message
            setMessage("Failed to update user details. ");
        }
    };

    const handleLogout = () => {
        localStorage.clear(); // Clear all local storage
        navigate('/login'); // Redirect to login page
    };

    

   if (!user) return <div>Loading...</div>;
  

    return (
        <div className="px-4 lg:px-24 py-12 bg-teal-200 flex justify-center items-center">
            <div className="w-full md:w-2/3 lg:w-1/2 bg-white p-8 rounded-lg shadow-lg">
                <h2 className="text-3xl font-bold text-center text-black mb-6">Your Profile</h2>
                {message && (
                    <div className="mb-4 text-red-500 text-center">
                        {message} {/* Display the message */}
                    </div>
                )}
                <div className="space-y-4">
                    <div>
                        <label className="block text-sm font-medium">Name:</label>
                        <input
                            type="text"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                            className="mt-1 block w-full border rounded-md p-2"
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium">Email:</label>
                        <input
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            className="mt-1 block w-full border rounded-md p-2"
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium">Contact Number:</label>
                        <input
                            type="text"
                            name="contactNumber"
                            value={formData.contactNumber}
                            onChange={handleChange}
                            className="mt-1 block w-full border rounded-md p-2"
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium">Address:</label>
                        <input
                            type="text"
                            name="address"
                            value={formData.address}
                            onChange={handleChange}
                            className="mt-1 block w-full border rounded-md p-2"
                        />
                    </div>
                    <button onClick={handleUpdate} className="mt-4 bg-blue-700 text-white w-full py-2 rounded">
                        Update Details
                    </button>
                </div>

                {/* Logout Button */}
                <button onClick={handleLogout} className="mt-4 bg-red-500 text-white w-full py-2 rounded">
                    Logout
                </button>

                {/* Modal for Password Input */}
                {showModal && (
                    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
                        <div className="bg-white p-4 rounded shadow-md w-1/3">
                            <h3 className="text-lg font-bold mb-2">Enter Password</h3>
                            <input
                                type="password"
                                value={password}
                                onChange={handlePasswordChange}
                                className="mt-1 block w-full border rounded-md p-2"
                                placeholder="Password"
                            />
                            <div className="mt-4">
                                <button onClick={handleSubmit} className="bg-blue-600 text-white px-4 py-2 rounded">
                                    Submit
                                </button>
                                <button onClick={() => setShowModal(false)} className="ml-2 bg-gray-300 text-black px-4 py-2 rounded">
                                    Cancel
                                </button>
                            </div>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
};

export default Profile;
