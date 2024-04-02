import React from "react";
import Autocomplete, { autocompleteClasses } from "@mui/material/Autocomplete";
import TextField from "@mui/material/TextField";
import Popper from "@mui/material/Popper";
import styled from "styled-components";

const CustomTextField = styled(TextField)`
  & .MuiOutlinedInput-root {
    font-size: 12px;
    color: #407700;
    width: 105px;
    height: 30px;
    padding-left: 5px !important;
    padding-right: 0px !important;
    & .MuiOutlinedInput-input {
      padding: 10px 14px; // 상하 패딩 조정
      height: calc(100% - 20px); // 입력 필드 내부 높이
    }
    & fieldset {
      border-color: #216e0d;
    }
    &:hover fieldset {
      border-color: lightgreen;
    }
    &.Mui-focused fieldset {
      border-color: #407700;
    }
  }
  background-color: #ffffff;
`;

const CustomAutocomplete = styled(Autocomplete)`
  & .${autocompleteClasses.endAdornment} {
    right: 0 !important; // 필터링 내부 아이콘 위치 조정
  }
`;

const StyledPopper = styled(Popper)`
  .MuiAutocomplete-listbox {
    font-size: 12px;
    color: #4a4a4a;
  }
`;

const PediaFilter = ({ filterOption, setFilterOption }) => {
  const filterOptions = ["가나다 순", "즐겨찾기 순", "도감 저장 순"];

  return (
    <CustomAutocomplete
      disableClearable
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
            readOnly: true,
            inputProps: {
              ...(params.inputProps || {}),
              readOnly: true,
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
