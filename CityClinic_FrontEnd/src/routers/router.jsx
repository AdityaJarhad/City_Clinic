import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import App from '../App';
import Dashboard from "../dashboard/Dashboard";

import MedicalHistory from "../dashboard/MedicalHistory"; 
import ReviewManagement from "../dashboard/ReviewManagement"; 
import UserManagement from "../dashboard/UserManagement"; 
import UserDetail from "../dashboard/UserDetail"; 
import Clinic from "../dashboard/Clinic";
import DoctorListForAppointments from "../dashboard/DoctorListForAppointments";
import BookAppointment from "../dashboard/BookAppointment";
import BookedAppointments from "../dashboard/BookedAppointments";
import Appointments from "../dashboard/Appointments";
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
                path: "/dashboard/medical-history",
                element: <MedicalHistory />,
            },
            {
                path: "/dashboard/reviews",
                element: <ReviewManagement />,
            },
            {
                path: "/dashboard/users",
                element: <UserManagement />,
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
                path: "/dashboard/user/:id",
                element: <UserDetail />,
                loader: ({ params }) => fetch(`http://localhost:8080/users/${params.id}`)
            },
            {
                path: "/dashboard/appointments",
                element: <Appointments />, // Route for the Appointments component
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
