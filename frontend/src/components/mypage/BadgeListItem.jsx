import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import styled, { css } from 'styled-components';

const MySwal = withReactContent(Swal);

const BadgeImage = styled.img`
  width: auto; 
  height: 60px; 
  // filter: drop-shadow(0 4px 8px rgba(0, 0, 0, 0.2)); 
  cursor: pointer;
  opacity: ${(props) => (props.check ? '1' : '0.4')};
`;

const BadgeListItem = ( {badge} ) => {
  const handleClick = () => {
    MySwal.fire({
      title: `<span style="color: #ffffff;">${badge.name}</span>`,
      html: `<span style="color: #ffffff;">획득 조건 - ${badge.details}</span>`,
      imageUrl: '/badge.png',
      imageWidth: 120,
      imageHeight: 120,
      imageAlt: '뱃지 아이콘',
      background: 'rgba(0,0,0,0.75)',
      width: '320',
      padding: '10',
      confirmButtonText: '닫기',
      confirmButtonColor: '#407700', 
      customClass: {
        confirmButton: 'custom-swal-button',
        popup: 'custom-swal-popup',
        title: 'custom-swal-title'
      },
      imageClass: `custom-swal-image${badge.check ? '' : '-unchecked'}`
    });

    // 커스텀 스타일 정의
    const customStyles = `
      .custom-swal-image-unchecked {
        opacity: 0.4; // SweetAlert 이미지 투명도 조정
      }
      .custom-swal-popup {
        font-size: 12px !important;
        whiteSpace: nowrap; // 줄바꿈 방지
      }
      .custom-swal-title {
        font-size: 8 !important;
      }
    `;
    
    const head = document.head || document.getElementsByTagName('head')[0];
    const style = document.createElement('style');
    if (style.styleSheet) {
      style.styleSheet.cssText = customStyles;
    } else {
      style.appendChild(document.createTextNode(customStyles));
    }
    head.appendChild(style);
  };
  return (
    <>
      <BadgeImage src='/badge.png' alt='Badge' onClick={handleClick} check={badge.check}/>
    </>
  );
};

export default BadgeListItem;