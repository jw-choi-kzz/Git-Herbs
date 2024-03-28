import React from 'react';
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
import Popper from '@mui/material/Popper';
import styled from 'styled-components';

const CustomTextField = styled(TextField)`
  & .MuiOutlinedInput-root {
    font-size: 12px;
    color: #4A4A4A;
    width: 120px;
    height: 35px;
    // 패딩이나 마진으로 여백을 조정
    & .MuiOutlinedInput-input {
      padding: 3px; // 입력 필드 내부의 텍스트와 테두리 사이의 여백 조정
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

const StyledPopper = styled(Popper)`
  .MuiAutocomplete-listbox {
    font-size: 12px; // 원하는 폰트 크기로 변경하세요
    color: #4A4A4A; // 폰트 색상을 설정하세요
    // 추가적인 스타일링을 이곳에 넣으세요
  }
`;

const PediaFilter = ({ filterOption, setFilterOption }) => {
  const filterOptions = ['가나다 순', '즐겨찾기 순', '도감 저장 순'];

  return (
    <Autocomplete
      disableClearable // 지우기 버튼 비활성화
      value={filterOption}
      onChange={(event, newValue) => {
        setFilterOption(newValue);
      }}
      options={filterOptions}
      PopperComponent={StyledPopper}
      renderInput={(params) => (
        <CustomTextField
          {...params}
          InputProps={{
            ...params.InputProps,
            readOnly: true, // 입력 필드를 읽기 전용으로 설정
            inputProps: {
              ...(params.inputProps || {}),
              readOnly: true, // 명시적으로 inputProps에도 readOnly 속성을 적용
            },
          }}
          InputLabelProps={{
            ...params.InputLabelProps,
            shrink: false,
          }}
        />
      )}
    />
  );
};

export default PediaFilter;