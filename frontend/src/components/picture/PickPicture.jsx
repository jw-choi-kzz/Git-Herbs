import { useState } from 'react';
import Cropper from 'react-easy-crop';
import getCroppedImg from './CropImage';

const PickPicture = ({ imageUrl, onImageCrop, onGoBack }) => {
  const [crop, setCrop] = useState({ x: 0, y: 0 });
  const [zoom, setZoom] = useState(1);
  const [croppedAreaPixels, setCroppedAreaPixels] = useState(null);

  const onCropComplete = async (croppedArea, croppedAreaPixels) => {
    setCroppedAreaPixels(croppedAreaPixels);
  };

  const showCroppedImage = async () => {
    try {
      const croppedImage = await getCroppedImg(imageUrl, croppedAreaPixels);
      onImageCrop(croppedImage);
    } catch (e) {
      console.error(e);
    }
  };

  const handleGoBack = () => {
    onGoBack();
  };

  const confirmGoBack = () => {
    if (window.confirm('이전 단계로 이동하시겠습니까? 현재 이미지가 삭제됩니다.')) {
      handleGoBack();
    }
  };
  return (
    <div>
      <div style={{ position: 'relative', width: 300, height: 500 }}>
        {imageUrl && (
          <>
            <Cropper
              image={imageUrl}
              crop={crop}
              zoom={zoom}
              aspect={3 / 3}
              onCropChange={setCrop}
              onCropComplete={onCropComplete}
              onZoomChange={setZoom}
            />
          </>
        )}
      </div>
      <button onClick={confirmGoBack}>뒤?로?가?기?</button>
      <button onClick={showCroppedImage}>선택하기</button>
    </div>
  );
};

export default PickPicture;