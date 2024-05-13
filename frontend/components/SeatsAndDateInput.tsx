import { useState } from "react";
import DropDown from "./base/DropDown";
import DateInput from "./base/DateInput";

interface Props {
  seats: number;
  date: string;
  onSeatsChanged: (num: number) => void;
  onDateChanged: (str: string) => void;
}

export default function SeatsAndDateInput({
  onSeatsChanged,
  onDateChanged,
  seats,
  date,
}: Props) {
  // const [seats, setSeaets] = useState<number>(0);

  // const handleSeatsChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
  //   setSeaets(Number(event.target.value));
  //   onSeatsChanged(Number(event.target.value));
  // };

  // const [date, setDate] = useState("");
  // function handleDateChange(event: React.ChangeEvent<HTMLSelectElement>) {
  //   console.log("date changed ", typeof event.target.value);
  //   setDate(event.target.value);
  //   onDateChanged(event.target.value);
  // }

  return (
    <div className="d-flex flex-row text-center">
      <span className="mx-2 my-auto">For</span>

      <div className="mx-2 my-auto select_box">
        <DropDown
          className="mizdooni_dropdown"
          value={seats}
          onChange={(e) => {
            onSeatsChanged(Number(e.target.value));
          }}
          id="number_input"
          items={["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]}
        ></DropDown>
      </div>

      <span className="mx-2 my-auto">people, on date</span>

      <DateInput
        onChanged={(e) => {
          onDateChanged(e.target.value);
        }}
      />
    </div>
  );
}
