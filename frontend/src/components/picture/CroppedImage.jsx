import { useNavigate } from 'react-router-dom';

const CroppedImage = ({ croppedImage, onGoBack }) => {
  const navigate = useNavigate();

  const handleGoBack = () => {
    onGoBack();
    navigate('/picture');
  };

  const confirmGoBack = () => {
    if (window.confirm('이전 단계로 이동하시겠습니까? 현재 이미지가 삭제됩니다.')) {
      handleGoBack();
    }
  };

  return (
    <div>
      {croppedImage && (
        <div>
          <img src={croppedImage} alt="Cropped" style={{ width: '100%', height: 'auto' }} />
        </div>
      )}
      <button onClick={confirmGoBack}>뒤?로?가?기?</button>
      <button>분석하기</button>
    </div>
  );
};

export default CroppedImage;