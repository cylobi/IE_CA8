import RestaurantInfo from "Frontend/types/RestaurantInfo";
import React from "react";
import styled from "styled-components";

interface Props {
  info: RestaurantInfo;
}
const StyledDiv = styled.div`
  .time_radio {
    color: #ed3545;
    background-color: #fffcfc;
    border-color: #ed3545;
    border-radius: 0.5rem;
  }

  .time_radio:hover {
    color: #fff;
    background-color: #ee6573;
    border-color: #bd2130;
    cursor: pointer;
  }

  .time_radio:focus {
    box-shadow: 0 0 0 0.2rem pink;
  }

  .time_radio:active,
  .time_radio_active,
  .time_radio_active:hover {
    border-color: #dc3545;
    background-color: #ed3545;
    border-radius: 0.5rem;
    color: #ffffff;
  }
  #number_date_form_container {
    width: 300px;
  }

  .time_radio {
    color: #ed3545;
    background-color: #fffcfc;
    border-color: #ed3545;
    border-radius: 0.5rem;
  }

  .time_radio:hover {
    color: #fff;
    background-color: #ee6573;
    border-color: #bd2130;
    cursor: pointer;
  }

  .time_radio:focus {
    box-shadow: 0 0 0 0.2rem pink;
  }

  .time_radio:active,
  .time_radio_active,
  .time_radio_active:hover {
    border-color: #dc3545;
    background-color: #ed3545;
    border-radius: 0.5rem;
    color: #ffffff;
  }
`;

function ReserveHourInput({ info }: Props) {
  console.log("generate hour", info);
  let startHour = Number(info.startTime.split(":").at(0));
  let endHour = Number(info.endTime.split(":").at(0));
  let hoursCount = endHour - startHour;

  function generateHours() {
    let result = Array(hoursCount)
      .fill(0)
      .map((element, index) => index + startHour);
    return result;
  }

  function getHourText(hourNum: number) {
    let isPM = hourNum > 12;
    let newHour = hourNum % 12;
    let result =
      (newHour < 10 ? "0" + newHour : newHour) + ":00" + (isPM ? " PM" : " AM");
    return result;
  }

  function generateItem(startHour: number) {
    return (
      <label className="item m-2 btn time_radio">
        <input type="radio" />
        {getHourText(startHour)}
      </label>
    );
  }
  // TODO make sizes equal
  return (
    <StyledDiv className="container-fuild row m-1 p-1">
      <h6>Available Times</h6>
      <div className="row btn-group-toggle " data-toggle="buttons">
        {generateHours().map(generateItem)}
      </div>
    </StyledDiv>
  );
}

export default ReserveHourInput;
