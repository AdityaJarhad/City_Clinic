// src/dashboard/MedicalHistory.jsx
import React, { useEffect, useState } from 'react';

const MedicalHistory = () => {
    const [history, setHistory] = useState([]);

    useEffect(() => {
        const fetchMedicalHistory = async () => {
            const response = await fetch('http://localhost:8080/medical-history');
            const data = await response.json();
            setHistory(data);
        };
        fetchMedicalHistory();
    }, []);

    return (
        <div>
            <h2>Medical History</h2>
            <ul>
                {history.map(record => (
                    <li key={record.id}>
                        {record.date} - {record.details}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default MedicalHistory;
