import { useState } from "react";
import DropDown from "./base/DropDown";
import DateInput from "./base/DateInput";

export default function SeatsAndDateInput() {
  const [seats, setSeaets] = useState<number>(0);
  const handleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSeaets(Number(event.target.value));
    console.log("changed ", event.target.value);
  };

  const [date, setDate] = useState();
  function onDateChanged(event: React.ChangeEvent<HTMLSelectElement>) {
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
