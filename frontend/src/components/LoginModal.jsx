import { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom"; // useNavigate 훅 추가
import MySnackbar from "./MySnackbar"; // MySnackbar 컴포넌트의 경로에 맞게 수정해주세요.

const LoginModal = ({ isOpen, onClose }) => {
  const [open, setOpen] = useState(isOpen);
  const navigate = useNavigate();
  const location = useLocation();

  const handleClose = () => {
    setOpen(false);
    if (onClose) onClose();
  };

  const handleLogin = () => {
    navigate(
      `/login/oauth2/code/kakao?state=${encodeURIComponent(location.pathname)}`
    );
  };

  return (
    <div>
      <MySnackbar
        open={open}
        onClose={handleClose}
        messages={["로그인이 필요한 기능입니다"]}
        actionLabel1="뒤로가기"
        actionLabel2="로그인하기"
        onAction={handleLogin}
      />
    </div>
  );
};

export default LoginModal;
