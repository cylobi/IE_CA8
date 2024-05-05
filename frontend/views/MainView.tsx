// noinspection TypeScriptCheckImport
import * as React from "react";
import { useState } from "react";
import BaseModal from "../components/Modals/BaseModal";
import AddRestaurantModalBody from "Frontend/components/Modals/AddRestaurantModalBody";

import Header from "Frontend/components/Header";
import Footer from "Frontend/components/Footer";
import MainPageHero from "Frontend/components/MainPageHero";
import RestaurantInfo from "Frontend/types/RestaurantInfo";
import RestaurantsGrid from "Frontend/components/RestaurantsGrid";
import AboutUs from "Frontend/components/AboutUs";

interface RestaurantGroupProps {
  url: string;
  title: string;
}

function RestaurantGroup({ url, title }: RestaurantGroupProps) {
  const [restaurantsData, setRestaurantsData] = useState<
    RestaurantInfo[] | null
  >();
  function fetchRestaurants() {
    fetch(url)
      .then((r) => r.json())
      .then((data) => {
        setRestaurantsData(data);
      });
  }
  React.useEffect(fetchRestaurants, []);

  return (
    <div className="container-fluid row">
      <h5 className="text-secondary my-2">{title}</h5>
      {restaurantsData ? (
        RestaurantsGrid(restaurantsData)
      ) : (
        <p>Data is loading</p>
      )}
    </div>
  );
}


function MainView() {
  return (
    <>
      <Header />
      <MainPageHero />
      <div className="container row my-5 mx-auto">
        <RestaurantGroup
          url="/restaurants/best_ones"
          title="Top Restaurants in Mizdooni"
        />
        <RestaurantGroup
          url="/restaurants/recommend"
          title="You Might Also Like"
        />
      </div>
      <AboutUs />
      <Footer />
    </>
  );
}

export default MainView;
