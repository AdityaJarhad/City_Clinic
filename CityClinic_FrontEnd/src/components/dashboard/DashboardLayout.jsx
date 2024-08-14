import React from 'react'
import { Outlet } from 'react-router-dom'
import SideBar from './SideBar'

const DashboardLayout = () => {
  return (
    <div className='flex gap-4 flex-col md:flex-row'>
        <SideBar></SideBar>
        <Outlet></Outlet>
    </div>
  )
}

export default DashboardLayout