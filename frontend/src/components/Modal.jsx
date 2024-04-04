import styled from 'styled-components';

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
  max-height: calc(100vh - 110px);
  overflow-y: auto;
  font-family: 'SpoqaHanSansNeo', sans-serif;
  color: #21351F;
`;

const Modal = ({ isOpen, onClose, children }) => {
  if (!isOpen) return null;

  return (
    <ModalWrapper>
      <ModalContent>
        {children}
      </ModalContent>
    </ModalWrapper>
  );
};

export default Modal;