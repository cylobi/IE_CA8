import Footer from "Frontend/components/Footer";
import Header from "Frontend/components/Header";
import ReserveForm from "Frontend/components/ReserveForm";
import Tumbdail from "Frontend/components/Tumbdail";
import RestaurantInfo from "Frontend/types/RestaurantInfo";
import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import styled from "styled-components";
function RestaurantDetailView() {
  const StyledComponent = styled.div`
    #open_label {
      border-radius: 12px;
    }

    #tumbdnail {
      position: relative;
    }

    #title_status_container {
      position: absolute;
      bottom: 0%;
      width: 100%;
      background-color: rgba(255, 255, 255, 0.88);
    }

    #reviews_container {
      background-color: #fff6f7;
    }

    .profile_letters {
      font-size: 1.5rem;
      background-color: #f0b5ba;
      color: black;
      text-align: center;
      padding: 0.5rem;
    }

    .profile_letters p {
      margin: auto;
      text-align: center;
    }

    .comment_wrapper {
      box-shadow: 0px 1px 0px #d9d9d9;
    }

    .page_button,
    .active_page_button {
      width: 2rem;
      height: 2rem;
      padding: 0.3rem;
      text-align: center;
    }

    .page_button {
      border: 1px solid #d9d9d9;
      color: #303030;
    }

    .page_button:hover {
      color: #c82333;
    }

    .page_button:active {
      color: #881c25;
      text-shadow: #dc3545;
    }

    .active_page_button {
      border: 2px solid #dc3545;
      color: #303030;
    }

    .page_button:focus {
      box-shadow: 0 0 0 0.2rem pink;
    }
  `;

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
    <StyledComponent>
      <Header />
      <div className="bg-white my-2 justify-content-center row">
        <div className="row container justify-content-center">
          <div className="card col mr-3 px-0 border-0">
            {restaurantData && Tumbdail(restaurantData)}
          </div>
          <div className="card col mr-3 px-0 border-0">
            {restaurantData && ReserveForm(restaurantData)}
          </div>
        </div>
      </div>
      <Footer />
    </StyledComponent>
  );
}

export default RestaurantDetailView;
