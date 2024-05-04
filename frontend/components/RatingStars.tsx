import FilledStar from "Frontend/public/images/star_filled.svg";
import UnfilledStar from "Frontend/public/images/star_unfilled.svg";

interface Props {
  value: number;
}

export default function RatingStars({ value }: Props) {
  const boolArray = [
    value >= 0,
    value >= 1,
    value >= 2,
    value >= 3,
    value >= 4,
  ];
  function getIcon(state: boolean) {
    return state ? <img src={FilledStar} /> : <img src={UnfilledStar} />;
  }
  return <div className="img-bar d-flex">{boolArray.map(getIcon)}</div>;
}
