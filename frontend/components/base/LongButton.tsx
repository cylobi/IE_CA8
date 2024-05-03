import {MouseEventHandler, ReactEventHandler} from "react";
import styled from 'styled-components';
import "./NormalButton.css"

interface Props{
    text : string,
    handler : () => void,
    isDisabled : boolean,
}

const LongButton = ({text, handler, isDisabled}:Props) => {

    const Button = styled.button<{ isDisabled: boolean }>`
     border-radius: 12px;
     color: white;
     background-color: ${props => props.isDisabled ? '#ED3545' : '#D9D9D9'};
     border: none;
     padding-top: 0.5rem;
     padding-bottom: 0.5rem;
    `;


    return (
        <Button
            className="container-fluid text-center"
            onClick={handler}
            type="button"
            disabled={isDisabled}
        >
            {text}
        </Button>
    );
}

export default LongButton;