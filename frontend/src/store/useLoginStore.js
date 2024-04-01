import create from "zustand";

const loginId = localStorage.getItem("userId");
const loginProfileImg = localStorage.getItem("profileImg");
const refreshToken = localStorage.getItem("refreshToken");
const accessToken = localStorage.getItem("accessToken");

// 로그인 상태를 관리하는 스토어를 생성합니다.
const useLoginStore = create((set) => ({
  isLogin: loginId !== null, // 로그인 상태
  userId: loginId, // 사용자 ID
  profileImg: loginProfileImg, // 프로필 이미지
  accessToken: accessToken, // 액세스 토큰
  refreshToken: refreshToken, // 리프레시 토큰
  // 상태 업데이트 함수
  setLogin: (isLogin) => set({ isLogin }),
  setUserId: (userId) => set({ userId }),
  setProfileImg: (profileImg) => set({ profileImg }),
  setAccessToken: (accessToken) => set({ accessToken }),
  setRefreshToken: (refreshToken) => set({ refreshToken }),
}));

export default useLoginStore;
