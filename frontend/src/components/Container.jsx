import styled from 'styled-components';

const Container = styled.div`
  width: 320px;
  height: ${props => props.height || 'auto'};
  margin: auto;
  border-radius: 10px;
  padding: ${props => props.padding || '20px'};
  background-color: ${props => props.backgroundColor || '#fff'};
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  display: flex;
  flex-direction: column;
  align-items: flex-start;
`;

export default Container;
