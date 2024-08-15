import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import App from '../App';
import Dashboard from "../dashboard/Dashboard";
import AppointmentManagement from "../dashboard/AppointmentManagement"; 
import MedicalHistory from "../dashboard/MedicalHistory"; 
import ReviewManagement from "../dashboard/ReviewManagement"; 
import UserManagement from "../dashboard/UserManagement"; 
import UserDetail from "../dashboard/UserDetail"; 
import Clinic from "../dashboard/Clinic";
import Login from "../Components/Login";
import HomeComp from "../Components/HomeComp"; // Updated to HomeComp based on the first example
import NotFound from "../Components/NotFound"; // Import the NotFound component
import Register from '../Components/Register';
import Profile from '../Components/Profile';

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
                path: "/dashboard/appointments",
                element: <AppointmentManagement />,
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
                path: "/dashboard/user/:id",
                element: <UserDetail />,
                loader: ({ params }) => fetch(`http://localhost:8080/users/${params.id}`)
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
