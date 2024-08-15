import React from 'react';

export default function ServiceCardComp({ services, headline }) {
  return (
    <div className='my-16'>
      <h2 className='text-4xl text-center font-bold my-11 text-black'>{headline}</h2>
      <div className='flex flex-wrap justify-center gap-8'>
        {services.map(service => (
          <div key={service.id} className='p-4 border rounded-lg shadow-md'>
            <h3 className='font-bold text-xl'>{service.name}</h3>
            <p>{service.description}</p>
            <a href={`/services/${service.id}`} className='text-blue-700 hover:underline'>
              View Details
            </a>
          </div>
        ))}
      </div>
    </div>
  );
}
