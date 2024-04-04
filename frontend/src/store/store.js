import { create } from "zustand";

const store = create((set) => ({
  herbs: {}, // 초기 상태를 빈 객체로 설정
  setHerbs: (newHerbs) => set(() => ({ herbs: newHerbs })),
}));

export default store;
