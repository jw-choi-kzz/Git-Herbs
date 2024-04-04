import styled from "styled-components";
import Snackbar from "@mui/joy/Snackbar";
import Typography from "@mui/joy/Typography";
import Button from "@mui/joy/Button";
import Stack from "@mui/joy/Stack";

const StyledStack = styled(Stack)`
  && {
    margin: 0;
    padding: 0; 
    width: 100%; 
    justify-content: center; 
    align-items: center; 
    bottom: 100px;
  }
`;

const StyledSnackbar = styled(Snackbar)`
  background: white;
  width: 350px;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  box-shadow: 0 3px 0px rgba(0, 0, 0, 0.2);
  display: flex;
  justify-content: center; 
  align-items: center;
  white-space: normal;
  padding: 0px 0;
`;

const CustomTypography = styled(Typography)`
  && {
    margin: 0px 0;
    padding: 10px 5px 10px 5px; 
    font-family: "SpoqaHanSansNeo", sans-serif; 
    font-size: 16px; 
    font-weight: 400; 
    text-align: center;
  }
`;
const StyledButton1 = styled(Button)`
  && {
    flex-grow: 1;
    margin: 4px; 
    white-space: normal;
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
    >
      <StyledStack direction="column" spacing={1}>
        {messages.map(
          (
            messageLine,
            index 
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
