// store.js
import create from 'zustand';

const useStore = create(set => ({
  herbs: {
    // 초기 상태는 빈 객체 또는 로딩 상태일 수 있습니다.
    data: {},
    loading: true,
  },
  // 액션을 통해 herbs 상태를 업데이트하는 함수
  setHerbs: (herbData) => set(state => ({ herbs: { ...state.herbs, data: herbData, loading: false }})),
}));

export default useStore;
