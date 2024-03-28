import React, { useState } from 'react';
import styled from 'styled-components';
import Snackbar from '@mui/joy/Snackbar';
import Button from '@mui/joy/Button';
import Stack from '@mui/joy/Stack';

const StyledSnackbar = styled(Snackbar)`
background: linear-gradient(45deg, #009688 30%, #4caf50 90%);
max-width: 360px;
`;

const MySnackbar = ({ open, onClose, children }) => {
  return (
    <StyledSnackbar
      open={open}
      autoHideDuration={6000}
      onClose={onClose}
      anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
    >
      <Stack direction="row" spacing={2} alignItems="center">
        {children}
      </Stack>
    </StyledSnackbar>
  );
};

export default MySnackbar;