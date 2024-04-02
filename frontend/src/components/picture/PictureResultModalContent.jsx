import styled from 'styled-components';
import Button from "@mui/joy/Button";
import { useNavigate } from 'react-router-dom';

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

const PictureResultModalContent = ({ item, onClose }) => {

  const select = () => {
    onClose();
    item.function();
  }

  return (
    <div>
      <h2>{item.title}</h2>
      <ButtonContainer>
        <StyledButton1 onClick={onClose}>{item.done}</StyledButton1>
        <StyledButton2 onClick={select}>{item.progress}</StyledButton2>
      </ButtonContainer>
    </div>
  );
};

export default PictureResultModalContent;