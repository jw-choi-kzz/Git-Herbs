import { useState } from "react";
import styled from 'styled-components';
import { useLocation } from "react-router-dom";
import PictureResultList from "../components/picture/PictureResultList";

const ImageWrapper = styled.div`
  display: flex;
  justify-content: center;
`;

const Image = styled.img`
  width: 244px;
  height: 244px;
  border-radius: 8px;
  margin-bottom: 20px;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
`;

const Container = styled.div`
  width: 320px;
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

const PictureResultPage = () => {
  const location = useLocation();
  const data = location.state?.responseData;
  const [selectedItem, setSelectedItem] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleItemClick = (item) => {
    setSelectedItem(item);
    setIsModalOpen(true);
  };


  return (
    <>
      <ImageWrapper>
        <Image src={data.pictureUrl} alt="Main" />
      </ImageWrapper>
      <Container>
        <PictureResultList items={data.candidates} img={data.pictureImg} onItemClick={handleItemClick} />
      </Container>
    </>
  );
};

export default PictureResultPage;