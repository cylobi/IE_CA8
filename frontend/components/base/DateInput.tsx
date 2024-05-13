import React from "react";
import DateSign from "Frontend/public/images/date_sign.svg";
import { styled } from "styled-components";

interface Props {
  onChanged: (event: any) => void;
}

function DateInput({ onChanged }: Props) {
  const StyledDiv = styled.div`
    #date_input_wrapper {
      background-color: #f5f5f5;
      border-color: #f5f5f5;
      border-radius: 0.5rem;
      padding: 0.3rem;
    }

    #date_input,
    #date_input {
      background-color: #f5f5f5;
      border: #f5f5f5;
    }
  `;
  return (
    <StyledDiv className="mx-2 my-auto form-control" id="date_input_wrapper">
      <img alt="img" className="fa" src={DateSign} />
      <input type="date" id="date_input" name="date" onChange={onChanged} />
    </StyledDiv>
  );
}

export default DateInput;
