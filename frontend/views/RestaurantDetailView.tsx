import Footer from "Frontend/components/Footer";
import Header from "Frontend/components/Header";
import Tumbdail from "Frontend/components/Tumbdail";
import RestaurantInfo from "Frontend/types/RestaurantInfo";
import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
function RestaurantDetailView() {
  const id = useLocation().pathname.split("/").at(-1);
  const getRestaurantUrl = "/restaurant/" + id;
  const [restaurantData, setRestaurantData] = useState<RestaurantInfo | null>(
    null
  );
  function fetchRestaurants() {
    fetch(getRestaurantUrl)
      .then((r) => r.json())
      .then((data) => {
        setRestaurantData(data);
      });
  }
  useEffect(fetchRestaurants, []);
  return (
    <>
      <Header />
      <div className="bg-white my-2 justify-content-center row">
        <div className="row container justify-content-center">
          <div className="card col mr-3 px-0 border-0">
            {restaurantData && Tumbdail(restaurantData)}
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default RestaurantDetailView;
