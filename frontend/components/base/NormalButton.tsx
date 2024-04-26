import React, { MouseEventHandler, ReactEventHandler } from 'react'
import "./NormalButton.css"

interface Props{
    text : string,
    handler : () => void,
    className : string
}
export default function NormalButton({text, handler, className}:Props) {

  return (
    <button className="btn normal_button" onClick={handler} type="button">
        {text}
    </button>
  );
}
