import {create} from 'zustand';

const useSearchStore = create((set) => ({
  keyword: '',
  setKeyword: (keyword) => set(() => ({ keyword })),
}));

export default useSearchStore;