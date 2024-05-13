import RestaurantInfo from "Frontend/types/RestaurantInfo";
import React from "react";

function RestaurantLargeCard(info: RestaurantInfo) {
  // TODO OPEN CLOSE
  return (
    <div className="container-fluid p-0 m-0" id="tumbdnail">
      <img alt="img" className="card-img-top" src={info.imageUrl} />
      <div
        className="d-flex justify-content-between pb-2 pt-3 px-3"
        id="title_status_container"
      >
        <h1 className="card-title mr-auto my-auto">{info.name}</h1>
        <p
          className="text-white my-auto px-3 py-1 small display-5 bg-success"
          id="open_label"
        >
          Open!
        </p>
      </div>
    </div>
  );
}

export default RestaurantLargeCard;
