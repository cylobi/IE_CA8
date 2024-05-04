import Footer from "Frontend/components/Footer";
import Header from "Frontend/components/Header";
import RestaurantInfo from "Frontend/types/RestaurantInfo";
import RestaurantsGrid from "Frontend/components/RestaurantsGrid";
import { useState } from "react";

export default function ResturantsView() {
  const backgroundCss = {
    backgroundColor: "#fffcfc",
    color: "white",
    height: "100vh",
    width: "100%",
    margin: 0,
  };

  const [restaurantsData, setRestaurantsData] = useState<
    RestaurantInfo[] | null
  >();
  fetch("/restaurants")
    .then((r) => r.json())
    .then((data) => {
      setRestaurantsData(data);
    })
    .catch((any) => {
      console.log("ohhh ... ", any); // TODO remove it :)
    });

  return (
    <div style={backgroundCss}>
      <Header />
      <div className="container-fluid">
        {restaurantsData ? (
          RestaurantsGrid(restaurantsData.slice(0, 10))
        ) : (
          <p>Data is loading</p>
        )}
      </div>
      <Footer />
    </div>
  );
}
