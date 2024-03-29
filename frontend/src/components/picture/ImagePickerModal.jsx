import { useRef, useState } from "react";
import { BiCamera, BiImage } from 'react-icons/bi';
import styled from "styled-components";
import Webcam from "react-webcam";
import { useNavigate } from "react-router-dom";

const ModalWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: flex-end;
  width: 100%;
  max-width: 375px;
  margin: 0 auto;
`;

const ModalContent = styled.div`
  background-color: #fff;
  padding: 20px;
  border-radius: 20px 20px 0 0;
  box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.15);
  width: 100%;
  max-height: calc(100vh - 110px); // 푸터 높이와 여백을 고려하여 최대 높이 조정
  overflow-y: auto;
  font-family: 'SpoqaHanSansNeo', sans-serif;
  color: #21351F;
`;

const WebcamContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
`;

const Button = styled.button`
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 100%;
  padding: 15px;
  border: none;
  border-radius: 10px;
  background-color: #fff;
  margin-bottom: 10px;
  font-size: 1em;
  font-weight: 400;
  cursor: pointer;
  font-family: 'SpoqaHanSansNeo', sans-serif;
  color: #21351F;

  svg {
    margin-right: 10px;
    font-size: 24px;
    color: #407700;
  }
`;

const ImagePickerModal = ({ isVisible, onClose, onImagePicked }) => {
  const fileInputRef = useRef(null);
  const webcamRef = useRef(null);
  const [isCameraOpen, setIsCameraOpen] = useState(false);
  const navigate = useNavigate();

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = () => {
      onImagePicked(reader.result);
      navigate("/picture", { state: { imageUrl: reader.result } });
      onClose();
    };

    if (file) {
      reader.readAsDataURL(file);
    }
  };

  const capturePhoto = () => {
    const imageSrc = webcamRef.current.getScreenshot();
    onImagePicked(imageSrc);
    navigate("/picture", { state: { imageUrl: imageSrc } });
    setIsCameraOpen(false);
    onClose();
  };

  if (!isVisible) return null;

  return (
    <ModalWrapper>
      <ModalContent>
        {isCameraOpen ? (
          <WebcamContainer>
            <Webcam audio={false} ref={webcamRef} width='100%' screenshotFormat="image/jpeg" />
          </WebcamContainer>
        ) : (
          <>
            <input
              type="file"
              accept="image/*"
              ref={fileInputRef}
              onChange={handleImageUpload}
              style={{ display: "none" }}
            />
            <Button onClick={() => setIsCameraOpen(true)}>
              <BiCamera />
              사진찍기
            </Button>
            <Button onClick={() => fileInputRef.current.click()}>
              <BiImage />
              앨범에서 선택
            </Button>
          </>
        )}
        {isCameraOpen && (
          <Button onClick={capturePhoto}>사진 찍기</Button>
        )}
      </ModalContent>
    </ModalWrapper>
  );
};

export default ImagePickerModal;