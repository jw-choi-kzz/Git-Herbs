import styled from 'styled-components';
import { herbsService } from '../../apis/herbs';
import { configService } from '../../apis/config';
import { useNavigate } from 'react-router-dom';
import MySnackbar from '../MySnackbar';
import { useState } from 'react';

const ListItemWrapper = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
`;

const ItemNumber = styled.span`
  font-size: 18px;
  font-weight: bold;
  margin-right: 10px;
`;

const ItemImage = styled.img`
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 8px;
  margin-right: 10px;
`;

const ItemContent = styled.div`
  flex: 1;
`;

const ItemName = styled.h3`
  font-size: 18px;
  margin: 0 0 5px;
`;

const ItemSimilarity = styled.p`
  font-size: 16px;
  color: #333;
  margin: 0;
  padding: 0;
`;

const ItemButton = styled.button`
  padding: 6px 12px;
  background-color: #4caf50;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
  white-space: nowrap;
`;

const PictureResultListItem = ({ item, index, count, herbId, img, onItemClick }) => {
  const navigate = useNavigate();
  const [snackbarOpen, setSnackbarOpen] = useState(false);

  const handleCloseSnackbar = () => {
    setSnackbarOpen(false);
  };

  const moveFunction = () => {
    navigate(`/detail/${herbId == undefined ? item.herbId : herbId}`);
  }

  const handleButtonClick = async () => {
    setSnackbarOpen(true);
    if (count === 0) {
      const myHerbRequestDto = {
        herbId: item.herbId,
        imgId: img,
        similarity: item.similarity
      };
      herbsService.postHerb(myHerbRequestDto, configService.loginConfig());
    }
    onItemClick(item.herbId)
  };

  return (
    <>
      <ListItemWrapper>
        <ItemNumber>{index}</ItemNumber>
        <ItemImage src={item.herbImgUrl} alt={item.herbName} />
        <ItemContent>
          <ItemName>{item.herbName}</ItemName>
          <ItemSimilarity>유사도 {item.similarity}%</ItemSimilarity>
          <ItemButton onClick={handleButtonClick}>도감에 추가하기</ItemButton>
        </ItemContent>
      </ListItemWrapper>

      {/* 스낵바를 호출하는 부분 */}
      <MySnackbar
        open={snackbarOpen}
        onClose={handleCloseSnackbar}
        messages={count === 1 ? ["'내 도감' 저장이 완료되었습니다."] : ["이미 저장된 이미지 입니다."]}
        actionLabel1="머무르기"
        actionLabel2="확인하러 가기 >"
        onAction={moveFunction}
      />
    </>
  );
};

export default PictureResultListItem;