import styled from 'styled-components';
import Header from "../components/Header";
import Footer from "../components/Footer";
import { Outlet } from "react-router-dom";

const LayoutContainer = styled.div`
  display: flex;
  flex-direction: column;  
  align-items: 'center';
  max-width: 320px;
  min-height: 100vh;
  margin: 0 auto;
  background-color: #f5f5f5;
`;

const MainContent = styled.main`
  width: 100%;
  flex: 1,
  overflow-y: 'anto';
`;

const Layout = () => {
  return (
    <LayoutContainer>
      <Header />
      <MainContent> 
        <Outlet />
      </MainContent>
      <Footer />
    </LayoutContainer>
  );
};

export default Layout;
