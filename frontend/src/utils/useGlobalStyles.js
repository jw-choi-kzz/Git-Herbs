import { useEffect } from "react";

const useGlobalStyles = () => {
  useEffect(() => {
    const originalStyle = window.getComputedStyle(document.body).overflowY;
    //document.body.style.overflow = "auto"; // 글로벌 스타일로 스크롤바 숨김
    document.body.style.overflowY = "hidden"; // 글로벌 스타일로 스크롤바 숨김
    document.body.style.margin = 0;

    return () => {
      document.body.style.overflowY = originalStyle; // 컴포넌트 언마운트 시 원래 스타일로 복원
    };
  }, []);
};

export default useGlobalStyles;
