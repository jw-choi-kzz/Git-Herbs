import { useState } from 'react';
import styled from 'styled-components';
import Header from "../components/Header";
import Footer from "../components/Footer";
import { Outlet } from "react-router-dom";
import ImagePickerModal from "../components/picture/ImagePickerModal";
import useLoadingStore from '../store/loadingStore';
import LoadingContent from '../components/LoadingContent';

const LayoutContainer = styled.div`
  // display: flex;
  // flex-direction: column;  
  // align-items: center;
  // width: 375px;
  // min-height: 100vh;
  // margin: 0 auto;
  // // padding-bottom: 10px; // 푸터의 높이만큼 padding 추가
  // background-color: #f5f5f5;
  max-width: 375px; /* 모바일 화면에 최적화된 너비를 최대 너비로 설정 */
  width: 100%; /* 디바이스의 너비에 따라 유연하게 조정되도록 함 */
  height: 100%;
  margin: 0 auto; /* 가운데 정렬 */
  background-color: #f5f5f5;
  `;

const MainContent = styled.main`
  width: 100%;
  flex: 1;
  height: calc(100dvh - 105px);
  overflow-y: auto;
  // padding-bottom: 55px; // 내용물과 푸터 사이의 간격을 조정하려면 다시 추가
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
  overflow: hidden;
`;

const FooterContainer = styled.div`
  position: relative;
  z-index: 1;
`;

const Layout = () => {
  const isLoading = useLoadingStore((state) => state.isLoading);

  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedImage, setSelectedImage] = useState(null);

  const handlePictureButtonClick = () => {
    setIsModalVisible(true);
  };

  const handleImagePicked = (imageData) => {
    setSelectedImage(imageData);
  };

  const handleModalOutsideClick = (event) => {
    if (event.target === event.currentTarget || event.target.closest('footer')) {
      setIsModalVisible(false);
    }
  };

  if (isLoading){
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
      {isModalVisible && (
        <ModalOverlay onClick={handleModalOutsideClick}>
          <ImagePickerModal
            isVisible={isModalVisible}
            onClose={() => setIsModalVisible(false)}
            onImagePicked={handleImagePicked}
          />
        </ModalOverlay>
      )}
    </>
  );
};

export default Layout;
