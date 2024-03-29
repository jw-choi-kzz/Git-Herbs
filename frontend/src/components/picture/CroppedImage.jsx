import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Button from "@mui/joy/Button";
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

const CroppedImage = ({ croppedImage, onGoBack }) => {
  const navigate = useNavigate();

  const handleGoBack = () => {
    onGoBack();
    navigate('/picture');
  };

  const confirmGoBack = () => {
    if (window.confirm('이전 단계로 이동하시겠습니까? 현재 이미지가 삭제됩니다.')) {
      handleGoBack();
    }
  };

  return (
    <div>
      {croppedImage && (
        <div>
          <img src={croppedImage} alt="Cropped" style={{ width: '100%', height: 'auto' }} />
        </div>
      )}
      <StyledButton1 onClick={confirmGoBack}>뒤로가기</StyledButton1>
      <StyledButton2>분석하기</StyledButton2>
    </div>
  );
};

export default CroppedImage;