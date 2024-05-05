import React, { useState } from "react";
import styled from "styled-components";
import DropDown from "./base/DropDown";
import DateInput from "./base/DateInput";
import NormalButton from "./base/NormalButton";
import ReserveHourInput from "./ReserveHourInput";
import RestaurantInfo from "Frontend/types/RestaurantInfo";

function ReserveForm(info: RestaurantInfo) {
  const StyledDiv = styled.div`
    #number_date_form_container {
      width: 300px;
    }
  `;
  function SeatsAndDateInput() {
    const [seats, setSeaets] = useState();
    const handleChange = (event) => {
      setSeaets(event.target.value);
      console.log("changed ", event.target.value);
    };

    const [date, setDate] = useState();
    function onDateChanged(event) {
      console.log("date changed ", typeof event.target.value);
    }

    return (
      <div className="d-flex flex-row text-center">
        <span className="mx-2 my-auto">For</span>

        <div className="mx-2 my-auto select_box">
          <DropDown
            className="form-control mizdooni_dropdown"
            value={seats}
            onChange={handleChange}
            id="number_input"
            items={["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]}
          ></DropDown>
        </div>

        <span className="mx-2 my-auto">people, on date</span>

        <DateInput onChanged={onDateChanged} />
      </div>
    );
  }

  return (
    <div className="container-fluid col m-2">
      <h5 className="row">Reserve Table</h5>
      <form>
        <SeatsAndDateInput />
        {info && ReserveHourInput(info)}
        <div className="row ">
          <p className="text-danger">
            You will reserve this table only for{" "}
            <u className="text-danger">one</u> hour, for more time please
            contact the restaurant.
          </p>
        </div>
        <NormalButton
          otherClass="btn container-fluid row"
          text="Complete the Reservation"
          handler={() => {}}
        />
      </form>
    </div>
  );
}

export default ReserveForm;
