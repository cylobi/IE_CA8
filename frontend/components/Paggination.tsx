import { useState } from "react";
import styled from "styled-components";

interface Props {
  initialIndex: number;
  itemsCount: number;
  pageCapacity: number;
  onChanged: (startIndex: number, endIndex: number) => void;
}
export default function Paggination({
  initialIndex,
  itemsCount,
  pageCapacity,
  onChanged,
}: Props) {
  const PagginationButton = styled.a`
    width: 2rem;
    height: 2rem;
    padding: 0.3rem;
    text-align: center;

    border: 1px solid #d9d9d9;
    color: #303030;

    :hover {
      color: #c82333;
    }

    :active {
      color: #881c25;
      text-shadow: #dc3545;
    }

    :focus {
      box-shadow: 0 0 0 0.2rem pink;
    }
  `; // TODO more style

  const neighberLimit = 1;
  const pagesCount = Math.ceil(itemsCount / pageCapacity);
  const [indexArray, setIndexArray] = useState<number[]>(); // use -1 for inserting cut (`...`)
  const [currentIndex, setCurrentIndex] = useState<number>(initialIndex);

  function makeRange(start: number, end: number) {
    let count = end - start + 1;
    return Array(count)
      .fill(0)
      .map((element, index) => index + start);
  }

  function getDisplayArray(current: number): number[] {
    if (pagesCount <= 1) {
      return [];
    }
    let isBeginCutNeeded = current - neighberLimit < 0;
    let isEndCutNeeded = current + neighberLimit < pagesCount;
    let firstElement = [1];
    let middlePart = isBeginCutNeeded
      ? [
          -1,
          current - neighberLimit,
          Math.max(current + neighberLimit, pagesCount),
        ]
      : makeRange(2, Math.max(current + neighberLimit));

    let finalPart = isEndCutNeeded ? [-1, pagesCount] : [];
    let result = firstElement.concat(middlePart, finalPart);
    return result;
  }

  function getPagginationItem(num: number) {
    if (num < 0) {
      return (
        <li className="page-item col">
          <p>...</p>
        </li>
      );
    } else {
      return (
        <li className="page-item col">
          <PagginationButton
            className="page-link rounded-circle page_button"
            onClick={() => onButtonClicked(num)}
          >
            {num}
          </PagginationButton>
        </li>
      );
    }
  }

  function onButtonClicked(buttonNum: number) {
    let newItemStartIndex = (buttonNum - 1) * pageCapacity;
    let newItemEndIndex = Math.min(
      newItemStartIndex + pageCapacity,
      itemsCount
    );
    onChanged(newItemStartIndex, newItemEndIndex);
  }

  return (
    <>
      <div className="row">
        <nav aria-label="Page navigation example">
          <ul className="pagination justify-content-center">
            {getDisplayArray(currentIndex).map(getPagginationItem)}
          </ul>
        </nav>
      </div>
    </>
  );
}
