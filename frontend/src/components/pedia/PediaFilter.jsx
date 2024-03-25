import React from 'react';
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
import styled from 'styled-components';

const CustomTextField = styled(TextField)`
  & .MuiOutlinedInput-root {
    font-size: 12px;
    color: #4A4A4A;
    width: 120px;
    height: 35px;
    // 패딩이나 마진으로 여백을 조정하려면 아래와 같이 추가
    & .MuiOutlinedInput-input {
      padding: 2px; // 입력 필드 내부의 텍스트와 테두리 사이의 여백 조정
    }

    & fieldset {
      border-color: #216E0D; // 테두리 색상 설정
    }
    &:hover fieldset {
      border-color: lightgreen; // 호버 상태일 때 테두리 색상 변경
    }
    &.Mui-focused fieldset {
      border-color: darkgreen; // 포커스 상태일 때 테두리 색상 변경
    }
  }
  background-color: #FFFFFF; // 배경색 설정
`;

const PediaFilter = ({ filterOption, setFilterOption }) => {
  const filterOptions = ['가나다 순', '즐겨찾기 순', '도감 저장 순'];

  return (
    <Autocomplete
      value={filterOption}
      onChange={(event, newValue) => {
        setFilterOption(newValue);
      }}
      options={filterOptions}
      renderInput={(params) => (
        <CustomTextField
          {...params}
          //label="정렬 기준"
          InputLabelProps={{
            ...params.InputLabelProps,
            shrink: false // 이 부분을 추가하여 레이블이 축소되지 않도록 합니다.
          }}
        />
        )}
    />
  );
};

export default PediaFilter;