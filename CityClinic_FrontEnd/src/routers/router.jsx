import {
    createBrowserRouter,
    RouterProvider,
  } from "react-router-dom";
import App from "../App";
import HomeComp from "../components/HomeComp";

  const router = createBrowserRouter([
    {
      path: "/",
      element: <App />,
      children:[
        {
            path: '/',
            element: <HomeComp />
        }
      ]
    },
  ]);

  export default router