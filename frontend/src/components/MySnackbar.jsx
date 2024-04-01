import styled from "styled-components";
import Snackbar from "@mui/joy/Snackbar";
import Typography from "@mui/joy/Typography";
import Button from "@mui/joy/Button";
import Stack from "@mui/joy/Stack";

const StyledStack = styled(Stack)`
  && {
    margin: 0; /* 마진 제거 */
    padding: 0; /* 패딩 제거 */
    width: 100%; // Take full width to ensure proper centering
    justify-content: center; // Horizontally center the children
    align-items: center; // Vertically center the children
    bottom: 100px;
  }
`;

const StyledSnackbar = styled(Snackbar)`
  background: white;
  width: 350px;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  box-shadow: 0 3px 0px rgba(0, 0, 0, 0.2); /* 스낵바에 그림자를 추가하여 더 돋보이게 함 */
  display: flex; // Use flexbox to center the content
  justify-content: center; // Horizontally center the content
  align-items: center; // Vertically center the content
  white-space: normal;
  padding: 0px 0; // Adjust padding as necessary to create space around the content
`;

const CustomTypography = styled(Typography)`
  && {
    margin: 0px 0; // 여백을 없애고 싶다면 이 값을 조정하세요.
    padding: 10px 5px 10px 5px; // 상단, 우측, 하단, 좌측 순서로 패딩 값을 설정하세요.
    font-family: "SpoqaHanSansNeo", sans-serif; // 글씨체를 변경하고 싶다면 이 값을 수정하세요.
    font-size: 16px; // 글씨 크기를 조정하고 싶다면 이 값을 수정하세요.
    font-weight: 400; // 글씨 두께를 조절하고 싶다면 이 값을 수정하세요.
    text-align: center;
  }
`;
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

const MySnackbar = ({
  open,
  onClose,
  messages,
  actionLabel1,
  actionLabel2,
  onAction,
}) => {
  return (
    <StyledSnackbar
      open={open}
      autoHideDuration={6000}
      onClose={onClose}
      anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      // sx={{
      //   bottom: { xs: "49px", sm: "49px" }, // 모바일(xs)과 태블릿(sm) 뷰포트에서의 위치 조정
      //   // 추가적인 미디어 쿼리나 스타일을 적용할 수 있습니다.
      // }}
    >
      <StyledStack direction="column" spacing={1}>
        {messages.map(
          (
            messageLine,
            index // 'messages' 배열의 각 항목에 대해 반복합니다.
          ) => (
            <CustomTypography key={index}>{messageLine}</CustomTypography>
          )
        )}{" "}
        <Stack direction="row" spacing={1} justifyContent="center">
          <StyledButton1 onClick={onClose}>{actionLabel1}</StyledButton1>
          <StyledButton2 onClick={onAction}>{actionLabel2}</StyledButton2>
        </Stack>
      </StyledStack>
    </StyledSnackbar>
  );
};

export default MySnackbar;
