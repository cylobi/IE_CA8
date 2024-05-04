import * as React from 'react';
import styled from 'styled-components';
import {useState} from "react";
import RatingStars from "Frontend/components/RatingStars";

interface FormInputProps {
    label : string;
    stars : number;
    onDataChange : (e:any) => void;
}

const FormInput : React.FC<FormInputProps> = ( {label, stars, onDataChange} : FormInputProps) => {

    const InputBox = styled.input`
     background-color: #F5F5F5;
     border-radius: 12px;
     border: none;
     padding: 1rem; 
     width: 40%; 
     box-sizing: border-box;
     appearance:none;
     &::-webkit-inner-spin-button,
     &::-webkit-outer-spin-button {
        -webkit-appearance: none;
        margin: 0;
     }
     -moz-appearance: textfield;
    `;

    return (
        <div className="d-flex justify-content-between align-content-center my-2">
            <div className="my-auto"> {label} </div>
            <RatingStars value={stars} onDataChange={(value) => onDataChange(value)} />
        </div>
    )
}

export default FormInput;
