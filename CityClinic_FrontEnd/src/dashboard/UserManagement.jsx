// src/dashboard/UserManagement.jsx
import React, { useEffect, useState } from 'react';

const UserManagement = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const fetchUsers = async () => {
            const response = await fetch('http://localhost:8080/users');
            const data = await response.json();
            setUsers(data);
        };
        fetchUsers();
    }, []);

    return (
        <div>
            <h2>User Management</h2>
            <ul>
                {users.map(user => (
                    <li key={user.id}>
                        {user.name} - {user.role}
                        <a href={`/dashboard/user/${user.id}`}> View Details</a>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default UserManagement;
