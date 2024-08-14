import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import { FaBarsStaggered, FaHospital, FaXmark } from "react-icons/fa6";

export default function NavbarComp() {

    const [isMenuOpen, setMenuOpen] = useState(false);
    const [isSticky, setSticky] = useState(false);

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    useEffect(() => {
        const handleScroll = () => {
            if(window.scrollY > 100){
                setIsSticky(true);
            }
            else{
                setIsSticky(false);
            }

            window.addEventListener("scroll", handleScroll);
            return () => {
                window.addEventListener("scroll", handelScroll);
            }
        }
    }, [])

    //NavItems
    const NavItems = [
        { link: "home", path: "/" },
        { link: "Login", path: "/login" },
        { link: "Dashboard", path: "/admin/dashboard" },
        { link: "Clinic", path: "/appointments" },
        { link: "Profile", path: "/profile" },

        {/*  { label: 'Login', path: '/login' },
        { label: 'Dashboard', path: '/admin/dashboard' },
        { label: 'Clinic', path: '/appointments' },
        { label: 'Profile', path: '/profile' },
 */}
    ]

  return (
    <header  className='w-full bg-transparent fixed top-0 left-0 right-0 transition-all ease-in duration-300'>
        <nav className={`py-5 lg:px-24 px-4 ${isSticky ? "sticky top-0 right-0 left-0 bg-green-300" : ""}`}>
            <div className='flex justify-between items-center text-base gap-8'>
                {/* logo */}

                <Link to="/" className='text-2xl font-bold text-green-700 flex items-center gap-2'>
                <FaHospital className='inline-block '></FaHospital > CityClinic </Link>

                {/* navber menu for desktops */}

                <ul className='md:flex space-x-12 hidden'>
                    {
                         NavItems.map(({ link, path }) => <Link key={path} to={path} className='block text-base text-black uppercase cursor-pointer hover:text-blue-500'>{link}</Link>)
                    }
                </ul>

                {/* Button for desktop appln */}
                <div className='space-x-12 hidden lg:flex items-center'>
                    <button><FaBarsStaggered className='w-5 hover:text-green-700' /></button>
                </div>

                {/* Button for mobile */}
                <div className='md:hidden'>
                    <button onClick={toggleMenu} className='text-black focus: outline-none' >
                        {
                            isMenuOpen ? <FaXmark className='h-5 w-5 text-black' /> : <FaBarsStaggered className='h-5 w-5 text-black' />
                        }
                    </button>
                </div>

                
            </div>
            <div className={`space-y-4 px-4 mt-16 py-7 bg-blue-700 ${isMenuOpen ? "block fixed top-0 right-0 left-0" : "hidden"}`}>
                    {
                        NavItems.map(({ link, path }) => <Link key={path} to={path} className='block text-base text-white uppercase cursor-pointer '>{link}</Link>)
                    }
                </div>
        </nav>
    </header>
  )
}
