import RestaurantInfo from "Frontend/types/RestaurantInfo";
import React from "react";
import RestaurantLargeCard from "./RestaurantLargeCard";

import { tumbdailSummary } from "./TumbdailSummary";

function Tumbdail(info: RestaurantInfo) {
  return (
    <div>
      {RestaurantLargeCard(info)} {tumbdailSummary(info)}
    </div>
  );
}

export default Tumbdail;
