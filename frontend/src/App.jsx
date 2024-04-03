import { useEffect } from "react";
import useLoadingStore from "./store/loadingStore";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./App.css";
import useGlobalStyles from "./utils/useGlobalStyles";
import Layout from "./pages/Layout";
import MainPage from "./pages/MainPage";
import ErrorPage from "./pages/ErrorPage";
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
import BadgePage from "./pages/BadgePage";
import HerbMap from "./components/herbdetail/HerbMap";
import PrivateRoute from "./components/PrivateRoute";
import ScrollToTop from "./components/ScrollToTop";

function App() {
  useGlobalStyles();
  const script = document.createElement("script");
  script.src = `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${import.meta.env.VITE_NAVER_CLIENT_KEY}&callback=map_callback`;
  script.async = true;
  document.head.appendChild(script);
  const setIsLoading = useLoadingStore((state) => state.setIsLoading);
  useEffect(() => {
    setIsLoading(false);
  }, [setIsLoading]);

  const router = createBrowserRouter([
    {
      path: "/",
      element: (
        <Layout>
          <ScrollToTop />
        </Layout>
      ),
      errorElement: <ErrorPage />,
      children: [
        {
          path: "/",
          element: <MainPage />,
          errorElement: <ErrorPage />,
        },
        {
          path: "/login/oauth2/code/kakao",
          element: <LoginLoad />,
          errorElement: <ErrorPage />,
        },
        {
          path: "/escape",
          element: <EscapePage />,
          errorElement: <ErrorPage />,
        },
        {
          path: "/picture",
          element: <PrivateRoute />,
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
            },
          ],
        },
        {
          path: "/detail/:herbId",
          element: <DetailPage />,
          errorElement: <ErrorPage />,
        },
        {
          path: "/mypage",
          element: <PrivateRoute />,
          errorElement: <ErrorPage />,
          children: [
            {
              path: "",
              element: <MyPage />,
            },
            {
              path: "badge",
              element: <BadgePage />,
              errorElement: <ErrorPage />,
            },
          ],
        },
        {
          path: "/map",
          element: <HerbMap />,
          errorElement: <ErrorPage />,
        },
      ],
    },
  ]);
  return (
    <>
      <RouterProvider router={router} fallbackElement={<p>Loading...</p>} />
    </>
  );
}

export default App;
