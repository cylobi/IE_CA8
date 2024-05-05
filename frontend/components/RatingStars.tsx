import * as React from "react";
import FilledStar from "Frontend/public/images/star_filled.svg";
import UnfilledStar from "Frontend/public/images/star_unfilled.svg";

interface Props {
  value: number;
  onDataChange?: (index: number) => void;
}

export default function RatingStars({ value, onDataChange }: Props) {
  const boolArray = [
    value >= 0,
    value >= 1,
    value >= 2,
    value >= 3,
    value >= 4,
  ];

  function getIcon(state: boolean, index: number) {
    return (
      <img
        src={state ? FilledStar : UnfilledStar}
        onClick={() => onDataChange && onDataChange(index)}
      />
    );
  }
  if (onDataChange) {
    return (
      <div className="d-flex justify-content-end w-50">
        {boolArray.map((state, index) => getIcon(state, index))}
      </div>
    );
  } else {
    return <div className="img-bar d-flex">{boolArray.map(getIcon)}</div>;
  }
}
