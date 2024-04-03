import styled from 'styled-components';
import useModalStore from '../../store/modalStore';
import { herbsService } from '../../apis/herbs';
import { configService } from '../../apis/config';
import { useNavigate } from 'react-router-dom';

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

const PictureResultListItem = ({ item, index, saved, herbId, img, onItemClick }) => {
  const openModal = useModalStore((state) => state.openModal);
  const navigate = useNavigate();

  const handleButtonClick = () => {
    console.log(JSON.stringify(item))
    onItemClick(item.herbId)
    if (!saved) {
      const myHerbRequestDto = {
        herbId: item.herbId,
        imgId: img,
        similarity: item.similarity
      };
      herbsService.postHerb(myHerbRequestDto, configService.loginConfig());
    }

    const modalFunction = () => {
      navigate(`/detail/${herbId == undefined ? item.herbId : herbId}`);
    }

    const modalItem = {
      url: `/detail/${item.herbId}`,
      progress: "확인하러 가기 >",
      done: "머무르기",
      title: saved ? "이미 저장된 이미지 입니다." : "'내 도감' 저장이 완료되었습니다.",
      function: modalFunction
    }
    openModal("pictureResult", modalItem);
  };

  return (
    <ListItemWrapper>
      <ItemNumber>{index}</ItemNumber>
      <ItemImage src={item.herbImgUrl} alt={item.herbName} />
      <ItemContent>
        <ItemName>{item.herbName}</ItemName>
        <ItemSimilarity>{item.similarity}</ItemSimilarity>
        <ItemButton onClick={handleButtonClick}>도감에 추가하기</ItemButton>
      </ItemContent>
    </ListItemWrapper>
  );
};

export default PictureResultListItem;