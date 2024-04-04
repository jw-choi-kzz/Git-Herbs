import { useState, useEffect } from "react";
import { Navigate } from "react-router-dom";
import useLoginStore from "../store/useLoginStore";
import LoginModal from "../components/LoginModal";
import { Outlet } from "react-router-dom";

const PrivateRoute = () => {
  const token = localStorage.getItem('accessToken');
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    if (!token) {
      setShowModal(true);
    } 
  }, [token]);

  const handleModalClose = () => {
    setShowModal(false);
  };

  if (!token) {
    if (!showModal) {
      return <Navigate to="/" />;
    } else {
      return (
        <div>
          <LoginModal isOpen={showModal} onClose={handleModalClose} redirectUri={"/"}/>
        </div>
      );
    }
  }

  // 로그인이 되어 있다면 Outlet을 통해 자식 라우트를 렌더링
  return <Outlet />;
};

export default PrivateRoute;
