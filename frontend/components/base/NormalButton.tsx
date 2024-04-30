import {MouseEventHandler, ReactEventHandler} from "react";
import "./NormalButton.css"

interface Props{
    text : string,
    handler : () => void,
    isDisabled : boolean
}
export default function NormalButton({text, handler, isDisabled}:Props) {
    const styleCss={
        borderRadius: '12px',
        backgroundColor: isDisabled ? 'grey' : 'initial'
    };
  return (
    <button
        className="btn btn-danger"
        onClick={handler}
        type="button"
        style={styleCss}
    >
        {text}
    </button>
  );
}
