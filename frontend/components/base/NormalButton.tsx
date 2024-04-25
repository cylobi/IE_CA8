import React, { MouseEventHandler, ReactEventHandler } from 'react'
interface Props{
    text : string,
    handler : (event: React.MouseEvent<HTMLInputElement>) => void
}
export default function NormalButton({text, handler}:Props) {

  return (
    <div className="btn btn-primary" onClick={handler}>
        {text}
    </div>
  )
}
