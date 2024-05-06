import React, { useState } from "react";
import styled from "styled-components";
import DropDown from "./base/DropDown";
import DateInput from "./base/DateInput";
import NormalButton from "./base/NormalButton";
import ReserveHourInput from "./ReserveHourInput";
import RestaurantInfo from "Frontend/types/RestaurantInfo";
import SeatsAndDateInput from "./SeatsAndDateInput";
const StyledDiv = styled.div`
  #number_date_form_container {
    width: 300px;
  }
`;
interface Props {
  info: RestaurantInfo;
}

function ReserveForm({ info }: Props) {
  const [date, setDate] = useState("");
  const [seats, setSeats] = useState(1);

  const NoticeMessage = () => (
    <div className="row ">
      <p className="text-danger">
        You will reserve this table only for <u className="text-danger">one</u>{" "}
        hour, for more time please contact the restaurant.
      </p>
    </div>
  );

  return (
    <div className="container-fluid col m-2">
      <h5 className="row">Reserve Table</h5>
      <form>
        <SeatsAndDateInput
          date={date}
          seats={seats}
          onDateChanged={setDate}
          onSeatsChanged={setSeats}
        />
        <ReserveHourInput info={info} />
        <NoticeMessage />
        <NormalButton
          otherClass="container-fluid row"
          text="Complete the Reservation"
          handler={() => {}}
        />
      </form>
    </div>
  );
}

export default ReserveForm;
