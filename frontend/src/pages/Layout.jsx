import Header from "../components/Header";
import Footer from "../components/Footer";
import { Outlet } from "react-router-dom";

const Layout = () => {
  return (
    <div className="app-container">
      <Header />
      {/* <div style={{ flex: 1 }}> */}
        <Outlet />
      {/* </div> */}
      <Footer />
    </div>
  );
};

export default Layout;
