import { useState, useEffect } from "react";
import { Navigate } from "react-router-dom";
import useLoginStore from "../store/useLoginStore";
import LoginModal from "../components/LoginModal";
import { Outlet } from "react-router-dom";

const PrivateRoute = () => {
  const { userId } = useLoginStore();
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    // 로그인이 안 되어있으면 로그인 모달을 띄웁니다.
    if (!userId) {
      setShowModal(true);
    }
  }, [userId]);

  const handleModalClose = () => {
    // 모달 닫기 이벤트 핸들러
    setShowModal(false);
  };

  if (!userId && !showModal) {
    // 모달을 닫았을 때만 로그인 페이지로 리디렉션합니다.
    return <Navigate to="/loginpage" />;
  }

  return (
    <div>
      {userId ? <Outlet /> : null}
      <LoginModal isOpen={showModal} onClose={handleModalClose} />
    </div>
  );
};

export default PrivateRoute;
