import React, { useState, useEffect } from "react";
import styled from "styled-components";
import Header from "../components/Header";
import Footer from "../components/Footer";
import { Outlet } from "react-router-dom";
import useLoadingStore from "../store/loadingStore";
import LoadingContent from "../components/LoadingContent";
import Modal from "../components/Modal";
import ImagePickerModalContent from "../components/picture/ImagePickerModalContent";
import useModalStore from "../store/modalStore";
import useLoginStore from "../store/useLoginStore";
import LoginModal from "../components/LoginModal";

// 스타일드 컴포넌트 정의
const LayoutContainer = styled.div`
  max-width: 375px;
  width: 100%;
  height: 100vh;
  margin: 0 auto;
  background-color: #f5f5f5;
  display: flex;
  flex-direction: column;
  position: relative;
`;

const MainContent = styled.main`
  width: 100%;
  flex: 1;
  overflow-y: auto;
  padding-bottom: 60px; /* 푸터 높이만큼 padding 추가 */
`;

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
  z-index: 999;
  overflow: auto;
`;

const FooterContainer = styled.div`
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 1;
`;
// 레이아웃 컴포넌트
const Layout = () => {
  const isLoading = useLoadingStore((state) => state.isLoading);
  const { isLogin } = useLoginStore();
  const { isModalVisible, modalContent, selectedItem, openModal, closeModal } = useModalStore();

  const [selectedImage, setSelectedImage] = useState();

  const handlePictureButtonClick = () => {
    openModal("imagePicker");
  };

  const handleImagePicked = (imageData) => {
    setSelectedImage(imageData);
  };

  const handleModalOutsideClick = (event) => {
    if (event.target === event.currentTarget || event.target.closest("footer")) {
      closeModal();
    }
  };

  const renderModalContent = () => {
    if (isModalVisible) {
      if (!isLogin) {
        return <LoginModal isOpen={true} onClose={closeModal} />;
      } else {
        return (
          <ModalOverlay onClick={handleModalOutsideClick}>
            <Modal isOpen={isModalVisible} onClose={closeModal}>
              {modalContent === "imagePicker" && <ImagePickerModalContent onImagePicked={handleImagePicked} onClose={closeModal} />}
            </Modal>
          </ModalOverlay>
        );
      }
    }
  };

  useEffect(() => {
    if (isLoading) {
      window.scrollTo(0, 0);
    }
  }, [isLoading]);

  if (isLoading) {
    return <LoadingContent />;
  }

  return (
    <>
      <LayoutContainer>
        <Header />
        <MainContent>
          <Outlet />
        </MainContent>
        <FooterContainer>
          <Footer onPictureButtonClick={handlePictureButtonClick} />
        </FooterContainer>
      </LayoutContainer>
      {renderModalContent()}
    </>
  );
};

export default Layout;
