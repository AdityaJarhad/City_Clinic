// src/dashboard/UserDetail.jsx
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const UserDetail = () => {
    const { userId } = useParams();
    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchUser = async () => {
            const response = await fetch(`http://localhost:8080/users/${userId}`);
            const data = await response.json();
            setUser(data);
        };
        fetchUser();
    }, [userId]);

    if (!user) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h2>User Details</h2>
            <p>Name: {user.name}</p>
            <p>Email: {user.email}</p>
            <p>Role: {user.role}</p>
            <p>Contact: {user.contact}</p>
            <p>Address: {user.address}</p>
        </div>
    );
};

export default UserDetail;
