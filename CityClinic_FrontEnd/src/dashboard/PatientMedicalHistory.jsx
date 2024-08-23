import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const PatientMedicalHistory = () => {
  const navigate = useNavigate();
  const [medicalHistory, setMedicalHistory] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    if (!user) {
      navigate('/login');
      return;
    }

    const fetchMedicalHistory = async () => {
      try {
        const response = await axios.get(`/api/medical-history/patient/${user.user.id}`);
        setMedicalHistory(response.data);
      } catch (err) {
        setError('Error fetching medical history.');
        console.error('Error fetching medical history:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchMedicalHistory();
  }, [user, navigate]);

  if (loading) {
    return <p className="text-center text-gray-600">Loading...</p>;
  }

  if (error) {
    return <p className="text-center text-red-500">{error}</p>;
  }

  if (!medicalHistory) {
    return <p className="text-center text-gray-700">No medical history available.</p>;
  }

  
  const documentUrl = medicalHistory.documentURL.startsWith('http') ? medicalHistory.documentURL : `https://${medicalHistory.documentURL}`;

  return (
    <div className="p-6 mt-20 bg-gradient-to-r from-teal-200 to-blue-300">
      <h1 className="text-4xl font-bold mb-6 text-center text-gray-800">Medical History</h1>
      <div className="bg-white shadow-lg rounded-lg p-6">
        <h2 className="text-xl font-semibold text-blue-600">Diagnosis: {medicalHistory.diagnosis}</h2>
        <p className="text-gray-700">Treatment: {medicalHistory.treatment}</p>
        <p className="text-gray-700">Visit Date: {new Date(medicalHistory.visitDate).toLocaleDateString()}</p>
        {medicalHistory.documentURL && (
          <a 
            href={documentUrl} 
            target="_blank" 
            rel="noopener noreferrer" 
            className="text-blue-500"
          >
            View Document
          </a>
        )}
      </div>
    </div>
  );
};

export default PatientMedicalHistory;
