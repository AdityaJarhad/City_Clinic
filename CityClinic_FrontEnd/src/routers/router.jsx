import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import App from '../App';
import Dashboard from "../dashboard/Dashboard";

import MedicalHistory from "../dashboard/MedicalHistory"; 
import PatientMedicalHistory from "../dashboard/PatientMedicalHistory";
import ReviewManagement from "../dashboard/ReviewManagement"; 
import Clinic from "../dashboard/Clinic";
import DoctorListForAppointments from "../dashboard/DoctorListForAppointments";
import BookAppointment from "../dashboard/BookAppointment";
import BookedAppointments from "../dashboard/BookedAppointments";
import Appointments from "../dashboard/Appointments";
import DoctorPatients from "../dashboard/DoctorPatients";
import Login from "../Components/Login";
import HomeComp from "../Components/HomeComp"; // Updated to HomeComp based on the first example
import NotFound from "../Components/NotFound"; // Import the NotFound component
import Register from '../Components/Register';
import Profile from '../Components/Profile';
import ListOfClinics from '../Components/ListOfClinics';

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />, // Use the App component as the root element
        children: [
            {
                path: "/",
                element: <HomeComp />, // Render the Home component at the root path
            },
            {
                path: "/register",  // Add this route for registration
                element: <Register />,
            },
            {
                path: "/dashboard", // Directly render the Dashboard component
                element: <Dashboard />, // Removed DashboardLayout
            },
            {
                path: "/dashboard/medical-history/:patientId",
                element: <MedicalHistory />,
            },
            {
                path: "/dashboard/patient-medical-history",
                element: <PatientMedicalHistory />,
            },
            {
                path: "/dashboard/reviews",
                element: <ReviewManagement />,
            },
            {
                path: "/dashboard/clinic",
                element: <Clinic />, // Route for the Clinic component
            },
            {
                path: "/book-appointment",
                element: <BookAppointment />, // Route for the BookAppointment component
            },
            {
                path: "/dashboard/booked-appointments",
                element: <BookedAppointments />, 
            },
            {
                path: "/dashboard/doctors", // New route for viewing doctors
                element: <DoctorListForAppointments />, // Route for the new component
            },
            {
                path: "/dashboard/appointments",
                element: <Appointments />, // Route for the Appointments component
              },
              {
                path: "/dashboard/doctor-patients",
                element: <DoctorPatients />,
            },            
            {
                path: "/ListOfClinics",
                element: <ListOfClinics />,
            },
            {
                path: "/profile",
                element: <Profile />,
            },    
            {
                path: "/login",
                element: <Login />,
            },
            {
                path: "*", // This catches all unmatched routes
                element: <NotFound /> // Render the NotFound component for unmatched routes
            }
        ]
    }
]);

export default router;
