import React from 'react';
import { Link } from 'react-router-dom';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/pagination';
import { Pagination } from 'swiper/modules';

export default function CategoryBanner() {
  const categories = [
    { imgUrl: 'src/images/general.jpg', title: 'General Checkup' },
    { imgUrl: 'src/images/pediatrics.jpg', title: 'Pediatrics' },
    { imgUrl: 'src/images/dental.jpg', title: 'Dental Care' },
    { imgUrl: 'src/images/cardiology.jpg', title: 'Cardiology' },
    { imgUrl: 'src/images/dermatology.jpg', title: 'Dermatology' },
  ];

  return (
    <div className='my-16 px-4 lg:px-24'>
      <h2 className='text-4xl text-center font-bold my-11 text-black'>Choose Your Service Category</h2>
      <Swiper
        slidesPerView={1}
        spaceBetween={10}
        pagination={{ clickable: true }}
        breakpoints={{
          640: { slidesPerView: 2, spaceBetween: 20 },
          768: { slidesPerView: 4, spaceBetween: 40 },
          1024: { slidesPerView: 5, spaceBetween: 50 },
        }}
        modules={[Pagination]}
        className="mySwiper w-full h-full"
      >
        {categories.map(category => (
          <SwiperSlide key={category.title} className=''>
            <Link to={`/ListOfClinics`}>
              <div className='relative'>
                <img className='h-60' src={category.imgUrl} alt={category.title}/>
              </div>
              <div>
                <h3 className='font-bold text-xl text-blue-700'>{category.title}</h3>
              </div>
            </Link>
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}
