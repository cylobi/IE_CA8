import RestaurantInfo from "Frontend/types/RestaurantInfo";
import RestaurantSmallCard from "./RestaurantSmallCard";
import "./RestaurantSmallCard.css";

export default function ResturantsGrid(restaurantsData: RestaurantInfo[]) {
  return (
    <>
      <div className="row row_card">
        {restaurantsData.map(RestaurantSmallCard)}
      </div>
    </>
  );
}
