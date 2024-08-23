import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast, Toaster } from 'react-hot-toast';
import { Link } from 'react-router-dom';

const Login = () => {
    const [credentials, setCredentials] = useState({
        email: "",
        password: ""
    });

    const [errors, setErrors] = useState({});
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCredentials({ ...credentials, [name]: value });
        setErrors({ ...errors, [name]: "" }); // Clear the error for the specific field on change
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/api/users/signin', {
                email: credentials.email,
                password: credentials.password
            });
    
            const token = response.data.jwt;
            const user = response.data.user;
            localStorage.setItem("token", token);
            localStorage.setItem("user", JSON.stringify(user));
            toast.success("Successfully logged in");
            navigate("/");
        } catch (error) {
            if (error.response && error.response.data) {
                const responseErrors = error.response.data;
                setErrors(responseErrors);
                if (responseErrors.message) {
                    toast.error(responseErrors.message);
                } else {
                    toast.error("Login failed. Please check your credentials.");
                }
            } else {
                toast.error("An unexpected error occurred. Please try again later.");
            }
        }
    };

    return (
        <div>
            <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
                <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                    <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                        Sign in to your account
                    </h2>
                </div>

                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                    <form onSubmit={handleSubmit} className="space-y-6">
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900">
                                Email address
                            </label>
                            <div className="mt-2">
                                <input
                                    id="email"
                                    name="email"
                                    type="text"
                                    value={credentials.email}
                                    onChange={handleChange}
                                    required
                                    autoComplete="email"
                                    className={`block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ${errors.email ? 'ring-red-500' : 'ring-gray-300'} placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6`}
                                />
                                {errors.email && <p className="text-red-500 text-sm mt-1">{errors.email}</p>}
                            </div>
                        </div>

                        <div>
                            <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">
                                Password
                            </label>
                            <div className="mt-2">
                                <input
                                    id="password"
                                    name="password"
                                    type="password"
                                    value={credentials.password}
                                    onChange={handleChange}
                                    required
                                    autoComplete="current-password"
                                    className={`block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ${errors.password ? 'ring-red-500' : 'ring-gray-300'} placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6`}
                                />
                                {errors.password && <p className="text-red-500 text-sm mt-1">{errors.password}</p>}
                            </div>
                        </div>

                        <div>
                            <button
                                type="submit"
                                className="flex w-full justify-center rounded-md bg-blue-700 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-blue-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue-600"
                            >
                                Sign in
                            </button>
                        </div>
                    </form>

                    <p className="mt-10 text-center text-sm text-gray-500">
                        Not a member?{' '}
                        <Link to="/register" className="font-semibold leading-6 text-blue-700 hover:text-blue-500">
                            Register here
                        </Link>
                    </p>
                </div>
            </div>
            <Toaster position='bottom-center'/>
        </div>
    );
};

export default Login;
