import React, { Children } from "react";
import styled from "styled-components";
import SelectArrow from "Frontend/public/images/select_arrow.svg";

interface Props {
  children?: any;
  className?: string;
  title?: string;
  items?: string[];
}

function DropDown({ children, className, title, items }: Props) {
  const StyledDropDown = styled.select`
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    background: url(${SelectArrow}) right no-repeat;
    background-color: #fffcfc;
    border-radius: 12px;
    border-color: #fffcfc;
    border-right-width: 10px;

    :focus {
      box-shadow: 0 0 0 0.1rem pink;
      border-color: #fffcfc;
    }
  `;
  return (
    <StyledDropDown className={className}>
      {title && (
        <option selected disabled hidden>
          {title}
        </option>
      )}
      {items && items.map((value) => <option>{value}</option>)}
      {children}
    </StyledDropDown>
  );
}

export default DropDown;
