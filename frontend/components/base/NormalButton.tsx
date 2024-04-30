import React, { MouseEventHandler, ReactEventHandler } from 'react'
import "./NormalButton.css"

interface Props{
    text : string,
    handler : () => void,
    className : string
}
export default function NormalButton({text, handler, className}:Props) {
    const styleCss={
        borderRadius: '12px'
    };
  return (
    <button className="btn btn-danger" onClick={handler} type="button" style={styleCss}>
        {text}
    </button>
  );
}
