import styled from 'styled-components';
import PictureResultListItem from './PictureResultListItem';
import { useState } from 'react';

const ListWrapper = styled.ul`
  list-style: none;
  padding: 0;
  margin: 0;
`;

const EmptyMessage = styled.p`
  text-align: center;
  font-size: 18px;
  color: #888;
`;

const PictureResultList = ({ items, img, onItemClick }) => {
  console.log(items);
  const [count, setCount] = useState(0);
  const [herbId, setHerbId] = useState();
  const [showWarning, setShowWarning] = useState(false);

  const clicked = (data) => {
    if (count === 0) {
      setHerbId(data)
    }
    setCount(count + 1)
    onItemClick();
  }

  for (let item of items) {
    if (isToxicHerb(item.herbName)) {
      setShowWarning(true);
      break;
    }
  }

  // 주어진 이름이 독초인지 확인하는 함수
  function isToxicHerb(herbName) {
    const toxicHerbs = ["강황", "토천궁", "대반하", "왜당귀", "배초향", "고본", "맥문동", "삽주", "천궁", "오미자", "남오미자", "등칡", "황금"];
    return toxicHerbs.includes(herbName);
  }



  if (!items || items.length === 0) {
    return <EmptyMessage>검색 결과가 없습니다.</EmptyMessage>;
  }
  return (
    <ListWrapper className='bold'>
      <h2>사진 판별 결과</h2>
      {showWarning && <div><p><strong>독초일 수도 있으니 조심하세요!!</strong></p></div>}
      {items.map((item, index) => (
        <PictureResultListItem key={index} item={item} index={index + 1} count={count} herbId={herbId} img={img} onItemClick={clicked} />
      ))}
    </ListWrapper>
  );
};

export default PictureResultList;