import { useState } from "react";
import styled from "styled-components";
// import { BiChevronLeft, BiChevronRight } from "react-icons/bi";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";

// 기본 달력 컨테이너 스타일링
const StyledCalendar = styled(Calendar)`
  .react-calendar {
    border-radius: 20px;
  }

  .react-calendar__navigation {
    background: #fff;
    height: 24px;
    border-radius: 20px;
    font-size: 1px;
    span {
      font-size: 16px;
      font-weight: 400;
    }
  }

  .react-calendar__navigation
    button:disabled
    .react-calendar__navigation
    button:enabled:hover,
  .react-calendar__navigation button:enabled:focus {
    border-radius: 20px 20px 0 0;
  }
  .react-calendar__month-view {
    padding: 8px;
    abbr {
      color: #21351f;
      font-size: 10px;
      font-weight: 500;
    }
  }
  .react-calendar__month-view__weekdays {
    abbr {
      // 텍스트 부분
      font-size: 12px;
      font-weight: 600;
    }
  }
  .react-calendar__tile {
    text-align: center;
    height: 50px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    color: #21351f;
    align-items: center;
  }
  // /*hover, focus, 선택됐을 시 */
  .react-calendar__tile:enabled:hover,
  .react-calendar__tile:enabled:focus,
  .react-calendar__tile--active {
    background-color: #f8ffec;
    color: #21351f;
    border-radius: 10px;
  }
  .react-calendar__tile--now {
    background-color: #f8ffec;
    color: #21351f;
    border-radius: 10px;
  }
`;

const DayTile = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
`;

const IconImage = styled.img`
  width: 20px; // 원하는 너비로 설정
  height: 20px; // 원하는 높이로 설정
`;

const icons = {
  0: "/grass/step0.png",
  1: "/grass/step1.png",
  2: "/grass/step2.png",
  3: "/grass/step3.png",
};

const MonthGrass = ({ grassList }) => {
  // const [value] = useState(new Date());

  // tileContent 함수 내에서 모든 날짜에 대해 icons[0] 아이콘을 보여주도록 설정
  const tileContent = ({ date, view }) => {
    if (view === "month") {
      // 여기에서 다른 로직을 추가하여 특정 조건에 따라 다른 아이콘을 반환할 수 있습니다.
      return (
        <DayTile>
          <IconImage src={icons[2]} alt="Icon" />
        </DayTile>
      );
    }
    return null;
  };

  return (
    <StyledCalendar
      // value={value}
      tileContent={tileContent}
      next2Label={null}
      prev2Label={null}
      nextLabel={null}
      prevLabel={null}
      showNeighboringMonth={false}
      navigationLabel={null} // 네비게이션 라벨을 숨깁니다.
    />
  );
};

export default MonthGrass;
