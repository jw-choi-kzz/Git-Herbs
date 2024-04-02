import { useRef, useState } from "react";
import axios from "axios";
import { useNavigate, useLocation, Route, Routes } from "react-router-dom";
import MySnackbar from "./MySnackbar"; 

const LoginModal = ({ isOpen, onClose, redirectUri}) => {
  const [open, setOpen] = useState(isOpen);
  const navigate = useNavigate();
  const location = useLocation();
  const url = "https://j10a205.p.ssafy.io/api/v1/user/login";
  const kakao_url = useRef();
  
  const handleClose = () => {
    setOpen(false);
    if (onClose) onClose();
  };

  const handleLogin = () => {
    axios.get(url, {params: {"redirect-uri": redirectUri}})
    .then((response)=>{
      console.log("response");
      console.log(response);
      const loginUrl = response.data.data; 
      console.log("loginUrl");
      console.log(loginUrl);
      if(loginUrl) {
        kakao_url.current.setAttribute("href", loginUrl);
        kakao_url.current.click();
      }
    })
    .catch((error)=>{
      console.error("로그인 실패:", error);
    });
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
      <a ref={kakao_url} style={{ display: 'none' }}/> 
    </div>
  );
};

export default LoginModal;
