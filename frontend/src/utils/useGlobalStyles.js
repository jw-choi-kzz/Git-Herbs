import { useEffect } from "react";

const useGlobalStyles = () => {
  useEffect(() => {
    const originalStyle = window.getComputedStyle(document.body).overflowY;
    // document.body.style.overflowY = "auto"; // 글로벌 스타일로 스크롤바 숨김
    document.body.style.overflowY = "hidden"; // 글로벌 스타일로 스크롤바 숨김
    document.body.style.margin = 0;
    document.body.style.height = "100dvh";

    return () => {
      document.body.style.overflowY = originalStyle;
    };
  }, []);
};

export default useGlobalStyles;
