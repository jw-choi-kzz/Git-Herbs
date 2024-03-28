import styled from 'styled-components';
import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import PickPicture from "../components/picture/PickPicture";
import CroppedImage from "../components/picture/CroppedImage";

const PicturePage = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const [imageUrl, setImageUrl] = useState(null);
    const [croppedImage, setCroppedImage] = useState(null);

    const Container = styled.div`
    width: 95%;
    margin: auto;
    border-radius: 10px;
    padding: 20px;
    box-sizing: border-box;
    background-color: #fff;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 40px;
  `;

    useEffect(() => {
        const selectedImageUrl = location.state?.imageUrl;
        if (selectedImageUrl) {
            setImageUrl(selectedImageUrl);
        }
    }, [location.state]);

    const handleImageSelect = (selectedImageUrl) => {
        setImageUrl(selectedImageUrl);
    };

    const handleImageCrop = (croppedImageUrl) => {
        setCroppedImage(croppedImageUrl);
    };

    const imageGoBack = () => {
        setImageUrl(null);
        setCroppedImage(null);
        navigate('/');
    };

    const croppeGoBack = () => {
        setCroppedImage(null);
    };

    return (
        <Container>
            {!imageUrl && (
                <PickPicture onImageSelect={handleImageSelect} />
            )}
            {imageUrl && !croppedImage && (
                <PickPicture
                    imageUrl={imageUrl}
                    onImageCrop={handleImageCrop}
                    onGoBack={imageGoBack}
                />
            )}
            {croppedImage && (
                <CroppedImage
                    croppedImage={croppedImage}
                    onGoBack={croppeGoBack}
                />
            )}
        </Container>
    );
};

export default PicturePage;