import { useRef, useState } from "react";
import { BiCamera, BiImage } from 'react-icons/bi';
import styled from "styled-components";
import Webcam from "react-webcam";
import { useNavigate } from "react-router-dom";

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 48px;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: flex-end;
  z-index: 1000;
`;

const ModalContent = styled.div`
  background-color: #fff;
  padding: 20px;
  border-radius: 20px 20px 0 0;
  box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.15);
  width: 100%;
  max-width: 375px;
  position: relative;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
`;

const WebcamContainer = styled.div`
  width: 300px;
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
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;

  svg {
    margin-right: 10px;
    font-size: 24px;
  }
`;

const ImagePickerModal = ({ isVisible, onClose }) => {
  const fileInputRef = useRef(null);
  const webcamRef = useRef(null);
  const [isCameraOpen, setIsCameraOpen] = useState(false);
  const navigate = useNavigate();

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = () => {
      navigate("/picture", { state: { imageUrl: reader.result } });
      onClose();
    };

    if (file) {
      reader.readAsDataURL(file);
    }
  };

  const capturePhoto = () => {
    const imageSrc = webcamRef.current.getScreenshot();
    navigate("/picture", { state: { imageUrl: imageSrc } });
    setIsCameraOpen(false);
    onClose();
  };

  const handleOutsideClick = (event) => {
    if (event.target === event.currentTarget) {
      onClose();
    }
  };

  if (!isVisible) return null;

  return (
    <ModalOverlay onClick={handleOutsideClick}>
      <ModalContent>
        {isCameraOpen ? (
          <WebcamContainer>
            <Webcam audio={false} ref={webcamRef} width='300' screenshotFormat="image/jpeg" />
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
    </ModalOverlay>
  );
};

export default ImagePickerModal;