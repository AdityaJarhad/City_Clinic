import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast, Toaster } from 'react-hot-toast';
const Clinic = () => {
    const navigate = useNavigate();
    const userId = JSON.parse(localStorage.getItem("user"))?.user?.id; // Get the user ID from local storage
    const [exists, setExists] = useState(false); 
    const specializations = [
        { id: 1, name: 'Cardiology' },
        { id: 2, name: 'Pediatrics' },
        { id: 3, name: 'Dermatology' },
        { id: 4, name: 'Orthopedics' },
        { id: 5, name: 'Neurology' },
        { id: 6, name: 'General Medicine' },
        { id: 7, name: 'Gynecology' },
        { id: 8, name: 'Ophthalmology' },
        { id: 9, name: 'Psychiatry' },
        { id: 10, name: 'Gastroenterology' },
    ];

    const [selectedSpecializationId, setSelectedSpecializationId] = useState('');
    const [clinicDetails, setClinicDetails] = useState({
        clinicName: '',
        clinicMoNo: '',
        clinicEmail: '',
        clinicDescription: '',
        locationAddress: '',
        locationCity: '',
        locationState: '',
        locationZipCode: '',
        locationCountry: '',
        qualifications: '',
        experience: '',
        availabilitySchedule: '',
        profilePicture: 'https://drive.google.com/file/d/1VfM3Hh1WBj_7m_pejltnJlFWQ7WXn42H/view?usp=sharing'
    });

    useEffect(() => {
      const fetchDoctorDetails = async () => {
          try {
              const response = await fetch(`/api/doctors/user/${userId}`);
              if (response.ok) {
                  const existingDoctor = await response.json();
                  setExists(true); 
                  setClinicDetails((prevDetails) => ({
                      ...prevDetails,
                      clinicName: existingDoctor.clinicName,
                      clinicMoNo: existingDoctor.clinicMoNo,
                      clinicEmail: existingDoctor.clinicEmail,
                      clinicDescription: existingDoctor.clinicDescription,
                      locationAddress: existingDoctor.locationAddress,
                      locationCity: existingDoctor.locationCity,
                      locationState: existingDoctor.locationState,
                      locationZipCode: existingDoctor.locationZipCode,
                      locationCountry: existingDoctor.locationCountry,
                      qualifications: existingDoctor.qualifications,
                      experience: existingDoctor.experience,
                      availabilitySchedule: existingDoctor.availabilitySchedule,
                      profilePicture: existingDoctor.profilePicture
                  }));
                  setSelectedSpecializationId(existingDoctor.specializationId || ''); // Fallback to empty string
              } else if (response.status === 404) { // Check for 404 Not Found
                  const errorData = await response.json();
                  console.error('Doctor not found:', errorData.message);
                  // Show a user-friendly message in your UI
                  toast.error('Doctor not found. Please check the user ID or register a new doctor.');
              } else {
                  // Handle other error responses
                  const errorData = await response.json();
                  console.error('Failed to fetch doctor details:', errorData.message);
                  toast.error('Failed to fetch doctor details.');
              }
          } catch (error) {
              console.error('Error fetching doctor details:', error);
              // Set error state or show a user-friendly message in the UI
              toast.error('Error fetching doctor details.');
          }
      };
  
      fetchDoctorDetails();
  }, [userId]);
  
    const handleChange = (e) => {
        const { name, value } = e.target;
        setClinicDetails((prevDetails) => ({
            ...prevDetails,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        
        const requestData = {
            userId,
            specializationId: selectedSpecializationId,
            ...clinicDetails
        };

        console.log('Request Data:', requestData);


        try {
            if (exists) {
                // If the doctor exists, update their details
                const updateResponse = await fetch(`/api/doctors/${userId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(requestData),
                });

                if (updateResponse.ok) {
                    console.log('Doctor details updated successfully');
                    toast.success('Doctor details updated successfully');
                    //navigate('/dashboard'); // Redirect to dashboard after successful update
                } else {
                    console.error('Failed to update doctor details');
                    toast.error('Failed to update doctor details.');
                }
            } else {
                // If the doctor does not exist, register a new doctor
                const registerResponse = await fetch('/api/doctors/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(requestData),
                });

                if (registerResponse.ok) {
                    console.log('Doctor registered successfully');
                    toast.success('Doctor registered successfully');
                   // navigate('/dashboard'); // Redirect to dashboard after successful registration
                } else {
                    toast.error('Failed to register doctor.');
                    console.error('Failed to register doctor');
                }
            }
        } catch (error) {
            console.error('Error:', error);
            toast.error('An error occurred.');
        }
    };

    return (
        <div className="p-6 mt-20 bg-gradient-to-r from-teal-200 to-blue-300">
            <h1 className="text-4xl font-bold mb-6 text-center text-gray-800">Register Clinic</h1>
            <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                    <label className="block mb-1">Specialization:</label>
                    <select
                        required
                        value={selectedSpecializationId}
                        onChange={(e) => setSelectedSpecializationId(e.target.value)}
                        className="border p-2 rounded w-full"
                    >
                        <option value="">Select Specialization</option>
                        {specializations.map((spec) => (
                            <option key={spec.id} value={spec.id}>
                                {spec.name}
                            </option>
                        ))}
                    </select>
                </div>
                <div>
                    <label className="block mb-1">Clinic Name:</label>
                    <input
                        type="text"
                        name="clinicName"
                        value={clinicDetails.clinicName}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block mb-1">Clinic Mobile No:</label>
                    <input
                        type="text"
                        name="clinicMoNo"
                        value={clinicDetails.clinicMoNo}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block mb-1">Clinic Email:</label>
                    <input
                        type="email"
                        name="clinicEmail"
                        value={clinicDetails.clinicEmail}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block mb-1">Clinic Description:</label>
                    <textarea
                        name="clinicDescription"
                        value={clinicDetails.clinicDescription}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block mb-1">Location Address:</label>
                    <input
                        type="text"
                        name="locationAddress"
                        value={clinicDetails.locationAddress}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block mb-1">City:</label>
                    <input
                        type="text"
                        name="locationCity"
                        value={clinicDetails.locationCity}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block mb-1">State:</label>
                    <input
                        type="text"
                        name="locationState"
                        value={clinicDetails.locationState}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block mb-1">Zip Code:</label>
                    <input
                        type="text"
                        name="locationZipCode"
                        value={clinicDetails.locationZipCode}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block mb-1">Country:</label>
                    <input
                        type="text"
                        name="locationCountry"
                        value={clinicDetails.locationCountry}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block mb-1">Qualifications:</label>
                    <input
                        type="text"
                        name="qualifications"
                        value={clinicDetails.qualifications}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block mb-1">Experience:</label>
                    <input
                        type="text"
                        name="experience"
                        value={clinicDetails.experience}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block mb-1">Availability Schedule:</label>
                    <input
                        type="text"
                        name="availabilitySchedule"
                        value={clinicDetails.availabilitySchedule}
                        onChange={handleChange}
                        required
                        className="border p-2 rounded w-full"
                    />
                </div>
                <button type="submit" className="mt-4 bg-blue-600 text-white p-2 rounded hover:bg-blue-700 w-full">
                    {clinicDetails.userId ? 'Update Doctor' : 'Update'}
                </button>
            </form>
            <Toaster position='bottom-center'/>
        </div>
    );
};

export default Clinic;
