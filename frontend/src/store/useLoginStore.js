import { create } from "zustand";

const loginId = localStorage.getItem("userId");
const loginProfileImg = localStorage.getItem("profileImg");
const refreshToken = localStorage.getItem("refreshToken");
const accessToken = localStorage.getItem("accessToken");

const useLoginStore = create((set) => ({
  isLogin: loginId !== null,
  userId: loginId,
  profileImg: loginProfileImg,
  accessToken: accessToken,
  refreshToken: refreshToken,

  setLogin: (isLogin) => set({ isLogin }),
  setUserId: (userId) => set({ userId }),
  setProfileImg: (profileImg) => set({ profileImg }),
  setAccessToken: (accessToken) => set({ accessToken }),
  setRefreshToken: (refreshToken) => set({ refreshToken }),
}));

export default useLoginStore;
