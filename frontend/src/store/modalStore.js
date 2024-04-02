import { create } from "zustand";

const useModalStore = create((set) => ({
  isModalVisible: false,
  modalContent: null,
  selectedItem: null,
  openModal: (content, item = null) =>
    set({ isModalVisible: true, modalContent: content, selectedItem: item }),
  closeModal: () =>
    set({ isModalVisible: false, modalContent: null, selectedItem: null }),
}));

export default useModalStore;
