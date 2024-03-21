import Header from "../components/Header";
import Footer from "../components/Footer";
import { Outlet } from "react-router-dom";

const Layout = () => {
  return (
    <div style={{
      display: 'flex',
      flexDirection: 'column',
      justifyContent: 'space-between', 
      alignItems: 'center',
      maxWidth: '375px',
      minHeight: '100vh', 
      margin: '0 auto',
    }}>
      <Header />
      <main style={{ width: '100%', flex: 1, overflowY: 'auto' }}> 
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};

export default Layout;
