import React from 'react';

export default function BannerComp() {
  return (
    <div className='px-4 lg:px-24 bg-teal-200 flex items-center'>
      <div className='flex flex-col md:flex-row justify-between items-center gap-12 py-40'>

        {/* Left Side */}
        <div className='md:w-1/2 h-full space-y-8'>
          <h2 className='text-5xl font-bold leading-snug text-black'>Book Appointments<span className='text-green-700'> with best Doctors</span> </h2>
          <p className='md:w-4/5 font-medium'>Providing top-notch healthcare services tailored to your needs. Book your appointment today!</p>
          <input type="search" name='search' id='search' placeholder='Search by clinic' className='py-2 px-2 rounded-s-sm outline-none' />
          <button className='px-6 py-2 bg-blue-300 text-white font-medium'>Search</button>
        </div>

        {/* Right Side */}
        <div className='md:w-1/2'>
          <img src='src/images/doctor_banner.jpg' alt='Doctor consultation' className='w-full h-auto' />
        </div>

      </div>
    </div>
  );
}
