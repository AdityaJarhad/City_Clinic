import { Outlet } from "react-router-dom";
import NavbarComp from "./components/NavbarComp";

export default function App() {
  return (
    <>
      <NavbarComp></NavbarComp>
      <Outlet />
    </>
  )
}