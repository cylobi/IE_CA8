import Footer from "Frontend/components/Footer";
import Header from "Frontend/components/Header";
import RestaurantInfo from "Frontend/types/RestaurantInfo";
import RestaurantsGrid from "Frontend/components/RestaurantsGrid";
import { useEffect, useState } from "react";
import Paggination from "Frontend/components/Paggination";

export default function ResturantsView() {
  const queryString = location.search; // Get the entire query string
  const getUrl = "/restaurants/" + queryString;
  const backgroundCss = {
    backgroundColor: "#fffcfc",
    color: "white",
    height: "100vh",
    width: "100%",
    margin: 0,
  };
  const itemsPerPage = 12;
  const [displayIndexes, setDisplayIndex] = useState<number[]>([
    0,
    itemsPerPage,
  ]);
  const [restaurantsData, setRestaurantsData] = useState<
    RestaurantInfo[] | null
  >();
  function fetchRestaurants() {
    fetch(getUrl)
      .then((r) => r.json())
      .then((data) => {
        setRestaurantsData(data);
      });
  }
  useEffect(fetchRestaurants, []);

  function scrollToTop() {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  }
  // TODO margin
  return (
    <div style={backgroundCss}>
      <Header />
      <div className="bg-white my-2 justify-content-center row">
        <div className="container-fluid row">
          {restaurantsData ? (
            RestaurantsGrid(
              restaurantsData.slice(displayIndexes[0], displayIndexes[1])
            )
          ) : (
            <p>Data is loading</p>
          )}
        </div>
        <div className="row container justify-content-center">
          <Paggination
            initialIndex={1}
            itemsCount={restaurantsData ? restaurantsData.length : 0}
            pageCapacity={12}
            onChanged={(a, b) => {
              setDisplayIndex([a, b]);
              scrollToTop();
            }}
          />
        </div>
      </div>
      <Footer />
    </div>
  );
}
