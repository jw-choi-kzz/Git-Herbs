import { useState } from 'react';
import styled from 'styled-components';
import Header from "../components/Header";
import Footer from "../components/Footer";
import { Outlet } from "react-router-dom";
import ImagePickerModal from "../components/picture/ImagePickerModal";

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
  min-height: 100vh;
  margin: 0 auto; /* 가운데 정렬 */
  background-color: #f5f5f5;
  `;

const MainContent = styled.main`
  width: 100%;
  flex: 1;
  overflow-y: auto;
  min-height: calc(100vh - 105px);
  // padding-bottom: 55px; // 내용물과 푸터 사이의 간격을 조정하려면 다시 추가
`;

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 50px; // 푸터 높이만큼 여백 추가
  background-color: rgba(0, 0, 0, 0.5);
  display: ${props => props.visible ? 'block' : 'none'}; // isVisible 대신 visible 사용
  z-index: 999;
`;

const Layout = () => {

  const [isModalVisible, setIsModalVisible] = useState(false);
  const [ setSelectedImage ] = useState(null);

  const handlePictureButtonClick = () => {
    setIsModalVisible(true);
  };

  const handleImagePicked = (imageData) => {
    setSelectedImage(imageData);
  };

  return (
    <>
      <LayoutContainer>
        <Header />
        <MainContent>
          <Outlet />
        </MainContent>
        <Footer onPictureButtonClick={handlePictureButtonClick} />
      </LayoutContainer>
      <ModalOverlay visible={isModalVisible ? 'true' : undefined}>
      <ImagePickerModal
        isVisible={isModalVisible}
        onClose={() => setIsModalVisible(false)}
        onImagePicked={handleImagePicked}
      />
    </ModalOverlay>
    </>
  );
};

export default Layout;
