import { create } from "zustand";

const useLoadingStore = create((set) => ({
  isLoading: true,
  setIsLoading: (isLoading) => set({ isLoading }),
}));

export default useLoadingStore;
