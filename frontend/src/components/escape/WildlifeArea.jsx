import React from 'react';
import styled from 'styled-components';

const WildlifeArea = () => {
  return (
    <Container>
    <div className="example-component">

      <p>This is a simple React functional component used for demonstration purposes.</p>
    </div>
    </Container>
  );
};

export default WildlifeArea;

const Container = styled.div`
  width: 280px;
  height: auto; /* Changed to auto to wrap content */
  margin: auto;
  border-radius: 10px;
  padding: 20px;
  box-sizing: border-box;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* Align items to start */
  cursor: pointer;
`;