import * as React from 'react';
import styled from 'styled-components';
import {useState} from "react";

interface FormInputProps {
    label : string;
    formData : any;
    onDataChange : (e:any) => void;
}

const FormInput : React.FC<FormInputProps> = ( {label, formData, onDataChange} : FormInputProps) => {

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
            <label className="my-auto"> {label} </label>
            <InputBox type="number" name="numberOfSeats" value={formData} onChange={onDataChange}/>
        </div>
    )
}

export default FormInput;
