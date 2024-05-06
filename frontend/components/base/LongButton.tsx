import {MouseEventHandler, ReactEventHandler} from "react";
import styled from 'styled-components';
import "./NormalButton.css"

interface Props{
    text : string,
    handler : (e:any) => void,
    isDisabled : boolean,
}

const Button = styled.button<{ isDisabled: boolean }>`
     border-radius: 12px;
     color: white;
     background-color: ${props => props.isDisabled ? '#D9D9D9' : '#ED3545' };
     border: none;
     padding-top: 0.5rem;
     padding-bottom: 0.5rem;
    `;

const LongButton = ({text, handler, isDisabled}:Props) => {


    return (
        <Button
            className="container-fluid text-center"
            onClick={handler}
            type="button"
            isDisabled={isDisabled}
        >
            {text}
        </Button>
    );
}

export default LongButton;