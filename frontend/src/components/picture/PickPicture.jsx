import { useState } from 'react';
import Cropper from 'react-easy-crop';
import getCroppedImg from './CropImage';
import styled from 'styled-components';
import Button from "@mui/joy/Button";
import useModalStore from '../../store/modalStore';

const StyledButton1 = styled(Button)`
  && {
    flex-grow: 1; // 버튼이 공간 채우도록
    margin: 4px; //버튼 사이 공간
    white-space: normal; // 텍스트 줄바꿈
    background: #adadad;
    font-family: "SpoqaHanSansNeo", sans-serif;
    font-weight: 400;
    font-size: 15px;
  }
  &&:hover {
    background: #004d26;
  }
`;

const StyledButton2 = styled(Button)`
  && {
    flex-grow: 1;
    margin: 4px;
    white-space: normal;
    background: #407700;
    font-family: "SpoqaHanSansNeo", sans-serif;
    font-weight: 400;
    font-size: 15px;
  }
  &&:hover {
    background: #004d26;
  }
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: space-between;
`;

const PickPicture = ({ imageUrl, onImageCrop, onGoBack }) => {
  const [crop, setCrop] = useState({ x: 0, y: 0 });
  const [zoom, setZoom] = useState(1);
  const [croppedAreaPixels, setCroppedAreaPixels] = useState(null);
  const openModal = useModalStore((state) => state.openModal);

  const onCropComplete = async (croppedArea, croppedAreaPixels) => {
    setCroppedAreaPixels(croppedAreaPixels);
  };

  const showCroppedImage = async () => {
    try {
      const croppedImage = await getCroppedImg(imageUrl, croppedAreaPixels);
      onImageCrop(croppedImage);
    } catch (e) {
      alert('사진을 선택 해 주세요.');
      handleGoBack();
    }
  };

  const handleGoBack = () => {
    onGoBack();
  };

  const confirmGoBack = () => {
    const modalItem = {
      progress: "확인",
      done: "취소",
      title: "이전 단계로 이동하시겠습니까? 현재 이미지가 삭제됩니다.",
      function: onGoBack
    }
    openModal("pictureResult", modalItem);
  };

  return (
    <div>
      <div style={{ position: 'relative', width: 300, height: 500 }}>
        {imageUrl && (
          <>
            <Cropper
              image={imageUrl}
              crop={crop}
              zoom={zoom}
              aspect={3 / 3}
              onCropChange={setCrop}
              onCropComplete={onCropComplete}
              onZoomChange={setZoom}
            />
          </>
        )}
      </div>
      <ButtonContainer>
        <StyledButton1 onClick={confirmGoBack}>뒤로가기</StyledButton1>
        <StyledButton2 onClick={showCroppedImage}>선택하기</StyledButton2>
      </ButtonContainer>
    </div>
  );
};

export default PickPicture;