import RestaurantInfo from "Frontend/types/RestaurantInfo";
import React from "react";

import ClockIcon from "Frontend/public/images/clock_sign.svg";
import ReviewIcon from "Frontend/public/images/review_sign.svg";
import TypeIcon from "Frontend/public/images/food_tyle_sign.svg";
import LocationIcon from "Frontend/public/images/location_vector.svg";

export function tumbdailSummary(info: RestaurantInfo) {
  function displayTime(time: string) {
    let [hour, minute] = time.split(":");
    let hourNum = Number(hour);
    let AMPM = hourNum > 12 ? "PM" : "AM";
    hourNum = hourNum % 13;
    return `${hourNum}:${minute} ${AMPM}`;
  }

  return (
    <div className="container">
      <div className="row d-flex justify-content-between container-fluid p-0">
        <div className="mr-2 text-dark">
          <img alt="img" className="fa" src={ClockIcon} />
          <span className="align-middle ml-1">{`From ${
            info.startTime && displayTime(info.startTime)
          } to ${info.startTime && displayTime(info.endTime)}`}</span>
        </div>

        <div className="mx-2 text-dark">
          <img alt="img" className="fa" src={ReviewIcon} />
          <span className="align-middle ml-1">
            {info.reviewsCount + " Reviews"}
          </span>
        </div>

        <div className="mx-2 text-dark">
          <img alt="img" className="fa" src={TypeIcon} />
          <span className="align-middle ml-1">{info.type}</span>
        </div>
      </div>
      <div className="container row p-0 my-3">
        <img alt="img" className="fa" src={LocationIcon} />
        <span className="align-middle small ml-2 text-muted">
          {info.address.country +
            ", " +
            info.address.city +
            ", " +
            info.address.street}
        </span>
      </div>
    </div>
  );
}
