import React, { Children, useState } from "react";
import styled from "styled-components";
import SelectArrow from "Frontend/public/images/select_arrow.svg";

interface Props extends React.SelectHTMLAttributes<HTMLSelectElement> {
  children?: any;
  className?: string;
  title?: string;
  items?: string[];
}

function DropDown({
  children,
  className,
  title,
  items,
  onChange,
  ...rest
}: Props) {
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

  const [selectedValue, setSelectedValue] = useState("");

  const itemsWithNone = [""].concat(items || []);
  return (
    <StyledDropDown
      className={className}
      onChange={(e) => {
        setSelectedValue(e.target.value);
        onChange && onChange(e);
      }}
      {...rest}
    >
      {selectedValue === "" && title && (
        <option selected disabled hidden>
          {title}
        </option>
      )}
      {itemsWithNone &&
        itemsWithNone.map((value) => (
          <option key={value} value={value}>
            {value}
          </option>
        ))}
      {children}
    </StyledDropDown>
  );
}

export default DropDown;
