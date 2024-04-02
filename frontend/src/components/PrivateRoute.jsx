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
      // 로그인이 안 되어 있고, 모달이 여전히 보여지고 있다면
      // 모달을 띄우고 페이지는 리디렉션하지 않습니다.
      return (
        <div>
          <LoginModal isOpen={showModal} onClose={handleModalClose} redirectUri={"/"}/>
        </div>
      );
    }
  }

  // 로그인이 되어 있다면 Outlet을 통해 자식 라우트를 렌더링합니다.
  return <Outlet />;
};

export default PrivateRoute;
