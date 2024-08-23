import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import { toast, Toaster } from 'react-hot-toast';

const MedicalHistory = () => {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));
  const [exists, setExists] = useState(false);
  const [medicalHistory, setMedicalHistory] = useState({
    diagnosis: '',
    treatment: '',
    visitDate: '',
    documentURL: ''
  });
  const [loading, setLoading] = useState(true);
  const { patientId } = useParams();

  useEffect(() => {
    if (!user) {
      navigate('/login');
      return;
    }

    const fetchMedicalHistory = async () => {
      try {
        console.log("Patient ID:", patientId);
        const response = await axios.get(`/api/medical-history/${patientId}`);
        setMedicalHistory(response.data);
        setExists(true);
      } catch (error) {
        if (error.response.status === 500) {
          // Medical history does not exist
          toast.error('No value present / Create first');
          setExists(false);
        } else {
          console.error('Error fetching medical history:', error);
          toast.error('Error fetching medical history.');
        }
      } finally {
        setLoading(false);
      }
    };

    if (loading) {
      fetchMedicalHistory();
    }
  }, [user, navigate, patientId, loading]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setMedicalHistory((prevDetails) => ({
      ...prevDetails,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const requestData = {
      patientId: patientId,
      doctorId: user.user.id,
      ...medicalHistory
    };

    try {
      if (exists) {
        // Update existing medical history
        await axios.put(`/api/medical-history/${patientId}`, requestData);
        toast.success('Medical history updated successfully');
      } else {
        // Create new medical history
        await axios.post('/api/medical-history', requestData);
        toast.success('Medical history created successfully');
      }
     // navigate('/dashboard'); 
    } catch (error) {
      console.error('Error:', error);
      toast.error('An error occurred.');
    }
  };

  return (
    <div className="p-6 mt-20 bg-gradient-to-r from-teal-200 to-blue-300">
      <h1 className="text-4xl font-bold mb-6 text-center text-gray-800">Medical History</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block mb-1">Diagnosis:</label>
          <input
            type="text"
            name="diagnosis"
            value={medicalHistory.diagnosis}
            onChange={handleChange}
            required
            className="border p-2 rounded w-full"
          />
        </div>
        <div>
          <label className="block mb-1">Treatment:</label>
          <input
            type="text"
            name="treatment"
            value={medicalHistory.treatment}
            onChange={handleChange}
            required
            className="border p-2 rounded w-full"
          />
        </div>
        <div>
          <label className="block mb-1">Visit Date:</label>
          <input
            type="date"
            name="visitDate"
            value={medicalHistory.visitDate}
            onChange={handleChange}
            required
            className="border p-2 rounded w-full"
          />
        </div>
        <div>
          <label className="block mb-1">Document URL:</label>
          <input
            type="text"
            name="documentURL"
            value={medicalHistory.documentURL}
            onChange={handleChange}
            className="border p-2 rounded w-full"
          />
        </div>
        <button type="submit" className="p-2 bg-blue-500 text-white rounded">
          {exists ? 'Update Medical History' : 'Create Medical History'}
        </button>
      </form>
      <Toaster position='bottom-center'/>
    </div>
  );
};

export default MedicalHistory;
