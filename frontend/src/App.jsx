import { useState, useEffect, useRef } from 'react';
import { } from 'react-dom';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import './App.css';
import useGlobalStyles from './utils/useGlobalStyles';
import Layout from "./pages/Layout";
import MainPage from "./pages/MainPage";
import ErrorPage from "./pages/ErrorPage";
import LoginPage from "./pages/LoginPage";
import LoginLoad from "./pages/LoginLoad";
import EscapePage from "./pages/EscapePage";
import PicturePage from "./pages/PicturePage";
import PictureResultPage from "./pages/PictureResultPage";
import PediaPage from "./pages/PediaPage";
import BoardPage from "./pages/BoardPage";
import DetailPage from "./pages/DetailPage";
import SearchPage from "./pages/SearchPage";
import SearchResultPage from "./pages/SearchResultPage";
import MyPage from "./pages/MyPage";
import BedgePage from "./pages/BedgePage";
import PrivateRoute from "./components/PrivateRoute";

function App() {
  useGlobalStyles();
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Layout />,
      errorElement: <ErrorPage />,
      children: [
        {
          path:"/",
          element: <MainPage />,
          errorElement: <ErrorPage />,
        },
        {
          path: "/loginpage",
          element: <LoginPage />,
          errorElement: <ErrorPage />,
        },
        {
          path: "/login/oauth2/code/kakao",
          element: <LoginLoad />,
          errorElement: <ErrorPage />,
        },
        {
          path:"/escape",
          element: <EscapePage />,
          errorElement: <ErrorPage />,
        },
        {
          path: "/picture",
          errorElement: <ErrorPage />,
          children: [
            {
              path: "",
              element: <PicturePage />,
            },
            {
              path: "result",
              element: <PictureResultPage />,
              errorElement: <ErrorPage />,
            },
          ],
        },
        {
          path: "/pedia",
          element: <PediaPage />,
          errorElement: <ErrorPage />,
        },
        {
          path: "/board",
          element: <BoardPage />,
          errorElement: <ErrorPage />,
        },
        {
          path: "/search",
          errorElement: <ErrorPage />,
          children: [
            {
              path: "",
              element: <SearchPage />,
            },
            {
              path: "result",
              element: <SearchResultPage />,
              errorElement: <ErrorPage />,
            }
          ]
        },
        {
          path: "/detail/1",
          element: <DetailPage />,
          errorElement: <ErrorPage />,
        },
        {
          // path: "",
          // element: <PrivateRoute />,
          // children: [
          //   {
              path: "/mypage",
              element: <MyPage />,
              errorElement: <ErrorPage />,
        },
        {
                  path: "/mypage/bedge",
                  element: <BedgePage />,
                  errorElement: <ErrorPage />,
                }
        //   ]
        // }
      ],
    },
  ]);
  return (
    <>
      <div className="app-container">
        <RouterProvider router={router} fallbackElement={<p>Loading...</p>} />
      </div>

    </>
  )
}

export default App;