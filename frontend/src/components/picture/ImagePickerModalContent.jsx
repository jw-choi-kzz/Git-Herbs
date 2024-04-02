import { useRef } from "react";
import { BiCamera, BiImage } from 'react-icons/bi';
import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const Button = styled.button`
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 100%;
  padding: 15px;
  border: none;
  border-radius: 10px;
  background-color: #fff;
  margin-bottom: 10px;
  font-size: 1em;
  font-weight: 400;
  cursor: pointer;
  font-family: 'SpoqaHanSansNeo', sans-serif;
  color: #21351F;

  svg {
    margin-right: 10px;
    font-size: 24px;
    color: #407700;
  }
`;

const ImagePickerModalContent = ({ onImagePicked, onClose }) => {
  const fileInputRef = useRef(null);
  const navigate = useNavigate();

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = () => {
      onImagePicked(reader.result);
      onClose();
      navigate("/picture", { state: { imageUrl: reader.result } });
    };

    if (file) {
      reader.readAsDataURL(file);
    }
  };

  return (
    <>
      <input
        type="file"
        accept="image/*"
        capture="camera"
        ref={fileInputRef}
        onChange={handleImageUpload}
        style={{ display: "none" }}
      />
      <Button onClick={() => fileInputRef.current.click()}>
        <BiCamera />
        사진찍기
      </Button>

      <input
        type="file"
        accept="image/*"
        ref={fileInputRef}
        onChange={handleImageUpload}
        style={{ display: "none" }}
      />
      <Button onClick={() => fileInputRef.current.click()}>
        <BiImage />
        앨범에서 선택
      </Button>
    </>
  );
};

export default ImagePickerModalContent;