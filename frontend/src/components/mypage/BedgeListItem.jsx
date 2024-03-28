import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import styled from 'styled-components';


const MySwal = withReactContent(Swal);

const BadgeImage = styled.img`
  width: auto; // Adjust width as necessary
  height: 60px; // Adjust height to fit the design
  // border-radius: 10px; // Optional: Rounded corners for the image
  filter: drop-shadow(0 4px 8px rgba(0, 0, 0, 0.2)); // Shadow effect following the shape of the image
  cursor: pointer;
`;

const BedgeListItem = () => {
  const handleClick = () => {
    MySwal.fire({
      title: '<span style="color: #ffffff;">나는 약초꾼이다</span>', // HTML을 사용하여 타이틀 색상 변경
      html: '<span style="color: #ffffff;">획득 조건- Git Herbs 가입</span>', // HTML을 사용하여 텍스트 색상 변경
      imageUrl: '/bedge.png',
      imageWidth: 120,
      imageHeight: 120,
      imageAlt: '뱃지 아이콘',
      background: 'rgba(0,0,0,0.75)',
      width: '320',
      padding: '10',
      confirmButtonText: '닫기',
      confirmButtonColor: '#407700', // 버튼 색상 설정
      customClass: {
        confirmButton: 'custom-swal-button', // 커스텀 버튼 클래스
        popup: 'custom-swal-popup', // 커스텀 팝업 클래스
        title: 'custom-swal-title' // 커스텀 타이틀 클래스
      }
    });

    // 커스텀 스타일 정의
    const customStyles = `
      .custom-swal-popup {
        font-size: 12px !important;
        whiteSpace: nowrap; // 줄바꿈 방지
      }
      .custom-swal-title {
        font-size: 8 !important;
      }
    `;
    
    // 커스텀 스타일을 페이지에 삽입
    const head = document.head || document.getElementsByTagName('head')[0];
    const style = document.createElement('style');
    head.appendChild(style);
    style.type = 'text/css';
    style.appendChild(document.createTextNode(customStyles));
  };
  return (
    <>
      <BadgeImage src='/bedge.png' alt='Badge' onClick={handleClick}/>
      <BadgeImage src='/bedge.png' alt='Badge' onClick={handleClick}/>
      <BadgeImage src='/bedge.png' alt='Badge' onClick={handleClick}/>
      <BadgeImage src='/bedge.png' alt='Badge' onClick={handleClick}/>
    </>
  );
};

export default BedgeListItem;