import { useState } from 'react';
import ReactLoading from 'react-loading';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Button from "@mui/joy/Button";
import useModalStore from '../../store/modalStore';
import { configService } from '../../apis/config';
import imageCompression from 'browser-image-compression';

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

const CroppedImage = ({ croppedImage, onGoBack }) => {
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);
  const openModal = useModalStore((state) => state.openModal);

  function dataURLtoFile(dataURL, filename) {
    const arr = dataURL.split(',');
    const mime = arr[0].match(/:(.*?);/)[1];
    const bstr = atob(arr[1]);
    let n = bstr.length;
    const u8arr = new Uint8Array(n);

    while (n--) {
      u8arr[n] = bstr.charCodeAt(n);
    }

    return new File([u8arr], filename, { type: mime });
  }

  const resizeImageForMobile = async (file) => {
    const originalImage = await createImageBitmap(file);
    const originalWidth = originalImage.width;
    if (originalWidth <= 750) {
      return file;
    }
    const maxWidth = 750;
    const options = {
      maxWIdthOrHeight: maxWidth,
      useWebWorker: true
    }
    try {
      const resizedImage = await imageCompression(file, options);
      return resizedImage;
    } catch (error) {
      console.log('리사이즈 오류 : ', error)
    }
  }

  const send = async () => {
    setIsLoading(true);
    const file = dataURLtoFile(croppedImage, "croppedImgae.png");

    const resizedFile = await resizeImageForMobile(file);

    const form = new FormData();
    form.append("image", resizedFile);

    axios.post('https://j10a205.p.ssafy.io/api/v1/search/image', form, configService.loginConfig())
      .then(({ data }) => {
        const responseData = data.data;
        navigate('/picture/result', { state: { responseData } })
      }
      ).catch(() => {
        setIsLoading(false);
      });
  }

  const handleGoBack = () => {
    onGoBack();
    navigate('/picture');
  };

  const confirmGoBack = () => {
    const modalItem = {
      progress: "확인",
      done: "취소",
      title: "이전 단계로 이동하시겠습니까? 현재 이미지가 삭제됩니다.",
      function: handleGoBack
    }
    openModal("pictureResult", modalItem);
  };

  return (
    <div>
      {isLoading ? (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '70vh' }}>
          <ReactLoading type="spin" color="#000" height={50} width={50} />
        </div>
      ) : (
        <>
          {croppedImage && (
            <div>
              <img src={croppedImage} alt="Cropped" style={{ width: '100%', height: 'auto' }} />
            </div>
          )}
          <ButtonContainer>
            <StyledButton1 onClick={confirmGoBack}>뒤로가기</StyledButton1>
            <StyledButton2 onClick={send}>분석하기</StyledButton2>
          </ButtonContainer>
        </>
      )}
    </div>
  );
};

export default CroppedImage;