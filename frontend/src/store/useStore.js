import { create } from "zustand";

const useStore = create((set) => ({
  herbs: [],
  setHerbs: (herbs) => set(() => ({ herbs: herbs })),
  filterOption: "가나다 순",
  setFilterOption: (option) => set(() => ({ filterOption: option })),
}));

export default useStore;
