import "./CustomerReserveList.css";
import React, { useState, useEffect } from "react";

interface Reservation {
  reservationNumber: number;
  datetime: string;
  restaurantName: string;
  username: string;
  tableNumber: number;
  seatsNumber: number;
}

export default function () {
  const [data, setData] = useState<Reservation[] | null>(null);
  const currentDate = Date.now();

  useEffect(() => {
    fetch("reservations/current_user")
      .then((r) => r.json())
      .then((data) => setData(data));
  }, []);

  function generateTableItem(item: Reservation, index: number) {
    let isDatePassed = (dateStr: string) => {
      return new Date(dateStr).getTime() < currentDate;
    };
    let itemState = isDatePassed(item.datetime) ? "disabled" : "enabled";
    let itemActionButton = isDatePassed(item.datetime)
      ? "Add Comment"
      : "Cancel";
    let elementResult = (
      <tr className={itemState}>
        <td id="date">{item.datetime}</td>
        <td id="restaurant">
          <a href="">{item.restaurantName}</a>
        </td>
        <td id="table">Table-{item.tableNumber}</td>
        <td id="seats">{item.seatsNumber} Seats</td>
        <td className="action" id="Cancel">
          <a href="">{itemActionButton}</a>
        </td>
      </tr>
    );

    return elementResult;
  }

  function generateTable(data: Reservation[]) {
    return <tbody>{data.map(generateTableItem)}</tbody>;
  }

  return (
    <div className="container" id="customer_reservations">
      <div></div>
      <table>
        <thead>
          <tr>
            <th colSpan={5}>My reservations</th>
          </tr>
        </thead>
        {data ? generateTable(data) : <p>Loading data...</p>}
      </table>
    </div>
  );
}
