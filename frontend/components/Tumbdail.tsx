import RestaurantInfo from "Frontend/types/RestaurantInfo";
import React from "react";
import RestaurantLargeCard from "./RestaurantLargeCard";

import { tumbdailSummary } from "./TumbdailSummary";
interface Props {
  info: RestaurantInfo;
}
function Tumbdail({ info }: Props) {
  return (
    <div>
      {RestaurantLargeCard(info)} {tumbdailSummary(info)}
      <article>
        <p className="card-text text-dark">{info.description}</p>
      </article>
    </div>
  );
}

export default Tumbdail;
