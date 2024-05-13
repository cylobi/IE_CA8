import RestaurantInfo from "Frontend/types/RestaurantInfo";
import RatingStars from "./RatingStars";
import LocationIcon from "Frontend/public/images/location_sign.svg";
import DotIcon from "Frontend/public/images/dot_vector.svg";
import "./RestaurantSmallCard.css";

export default function (info: RestaurantInfo) {
  return (
    <>
      <div className="col-md-2">
        <a href={"/restaurant_detail/" + info.id}>
          <div className="card">
            <RatingStars value={info.overall} />
            <img
              className="card-img-top"
              src={info.imageUrl}
              alt="The Ivy Brasserie Picture"
            />
            <div className="card-body">
              <div className="card-name">{info.name}</div>
              <div className="card-reviews">{info.reviewsCount} reviews</div>
              <div className="card-type">{info.type}</div>
              <div className="card-location">
                <img alt="img" src={LocationIcon} />
                <span>{info.address.city}</span>
              </div>
              <div className="card-status">
                <span className="open">Open</span>
                <img alt="img" src={DotIcon} />
                Closes at {info.endTime} PM
              </div>
            </div>
          </div>
        </a>
      </div>
    </>
  );
}
