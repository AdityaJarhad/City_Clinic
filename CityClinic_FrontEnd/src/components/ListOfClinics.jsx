import React, { useState, useEffect } from 'react';

const ListOfClinics = () => {
  const [clinics, setClinics] = useState([]);
  const [loading, setLoading] = useState(true); // State to manage loading
  const [error, setError] = useState(null); // State to manage errors

  useEffect(() => {
    const fetchClinics = async () => {
      try {
        const response = await fetch('https://localhost:8080/api/doctors'); // Use the provided URL
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setClinics(data); // Assuming the API returns a list of clinics
      } catch (error) {
        console.error('Error fetching clinic data:', error);
        setError(error.message); // Set error message
      } finally {
        setLoading(false); // Set loading to false after fetching data
      }
    };

    fetchClinics();
  }, []);

  // Render loading message
  if (loading) {
    return <div className="text-center">Loading clinics...</div>;
  }

  // Render error message if there is an error
  if (error) {
    return <div className="text-center text-red-500">Error: {error}</div>;
  }

  return (
    <div className="p-6 mt-10 bg-gradient-to-r from-gray-100 to-white rounded shadow">
      <h1 className="text-2xl font-bold mb-4 text-center">List of Clinics</h1>
      <ul className="space-y-4">
        {clinics.map((doctor) => (
          <li key={doctor.id} className="bg-white shadow-md rounded p-4">
            <h2 className="font-semibold text-blue-600 text-lg">{doctor.clinic.name}</h2>
            <p className="text-gray-700">
              Location: {doctor.clinic.location?.address || 'Address not available'}, {doctor.clinic.location?.city || 'City not available'}
            </p>
            <p className="text-gray-700">Contact: {doctor.clinic.moNo || 'Contact not available'}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListOfClinics;
