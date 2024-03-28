import React, { useState } from 'react';
import styled from 'styled-components';
import Snackbar from '@mui/joy/Snackbar';
import Button from '@mui/joy/Button';
import Stack from '@mui/joy/Stack';

const StyledSnackbar = styled(Snackbar)`
  background: linear-gradient(45deg, ${props => props.theme.palette.primary[600]} 30%, ${props => props.theme.palette.primary[500]} 90%);
  max-width: 360px;
`;

const MySnackbar = ({ children, buttonTexts }) => {
  const [open, setOpen] = useState(false);

  const handleClick = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <StyledSnackbar
      autoHideDuration={5000}
      variant="solid"
      color="primary"
      size="lg"
      invertedColors
      open={open}
      onClose={handleClose}
      anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
    >
      <div>
        {children}
        <Stack direction="row" spacing={1}>
          <Button variant="solid" color="primary" onClick={handleClose}>
            {buttonTexts.confirmText || 'Yes, Maybe later'}
          </Button>
          <Button variant="outlined" color="primary" onClick={handleClose}>
            {buttonTexts.cancelText || 'No, I want to stay'}
          </Button>
        </Stack>
      </div>
    </StyledSnackbar>
  );
};

export default MySnackbar;