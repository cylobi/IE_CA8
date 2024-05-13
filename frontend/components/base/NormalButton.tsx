import "./NormalButton.css";
import stled from "styled-components";

interface Props {
  text: string;
  handler: () => void;
  otherClass?: string;
  isDisabled?: boolean;
}
export default function NormalButton({ text, handler, otherClass }: Props) {
  const styleCss = {
    borderRadius: "12px",
  };
  const classNames = "btn btn-danger" + " " + otherClass;
  return (
    <button
      className={classNames}
      onClick={handler}
      type="button"
      style={styleCss}
    >
      {text}
    </button>
  );
}
