import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { FaBarsStaggered, FaXmark } from 'react-icons/fa6';
import { GiMedicalDrip } from 'react-icons/gi';

export default function NavbarComp() {
    const [isMenuOpen, setIsMenuOpen] = useState(false);
    const [isSticky, setSticky] = useState(false);

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    useEffect(() => {
        const handleScroll = () => {
            if (window.scrollY > 100) {
                setSticky(true);
            } else {
                setSticky(false);
            }
        };

        window.addEventListener('scroll', handleScroll);
        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);

    const navItems = [
        { label: 'Home', path: '/' },
        { label: 'Dashboard', path: '/dashboard' },
        { label: 'Clinic', path: '/ListOfClinics' },
        { label: 'Profile', path: '/profile' },
        { label: 'Login', path: '/login' },
    ];

    return (
        <header className='w-full bg-transparent fixed top-0 left-0 right-0 transition-all ease-in duration-300'>
            <nav className={`py-5 lg:px-24 px-4 ${isSticky ? 'sticky top-0 right-0 left-0 bg-blue-300' : ''}`}>
                <div className='flex justify-between items-center text-base gap-8'>
                    {/* Logo Here */}
                    <Link to="/" className='text-2xl font-bold text-purple-700 flex items-center gap-2'>
                        <GiMedicalDrip className='inline-block' /> City Clinic
                    </Link>

                    {/* Nav items for large devices */}
                    <ul className='md:flex space-x-12 hidden'>
                        {navItems.map(({ label, path }) => (
                            <Link key={path} to={path} className='block text-base text-black uppercase cursor-pointer hover:text-blue-500'>
                                {label}
                            </Link>
                        ))}
                    </ul>

                    {/* Menu button for large devices */}
                    <div className='space-x-12 hidden lg:flex items-center'>
                        <button>
                            <FaBarsStaggered className='w-5 hover:text-blue-700' />
                        </button>
                    </div>

                    {/* Menu button for mobile devices */}
                    <div className='md:hidden'>
                        <button onClick={toggleMenu} className='text-black focus:outline-none'>
                            {isMenuOpen ? (
                                <FaXmark className='h-5 w-5 text-black' />
                            ) : (
                                <FaBarsStaggered className='h-5 w-5 text-black' />
                            )}
                        </button>
                    </div>
                </div>

                {/* Mobile menu */}
                <div className={`space-y-4 px-4 mt-16 py-7 bg-blue-700 ${isMenuOpen ? 'block fixed top-0 right-0 left-0' : 'hidden'}`}>
                    {navItems.map(({ label, path }) => (
                        <Link key={path} to={path} className='block text-base text-white uppercase cursor-pointer'>
                            {label}
                        </Link>
                    ))}
                </div>
            </nav>
        </header>
    );
}
