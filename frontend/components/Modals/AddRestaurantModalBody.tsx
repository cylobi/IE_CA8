import * as React from 'react';
import "./Modal.css";
import LongButton from "../base/LongButton";
import FormInput from '../base/FormInput';
import {useState} from "react";
import styled from 'styled-components';

interface AddRestaurantModalBodyProps {
    handler : (e:any) => void;
}

const AddRestaurantModalBody : React.FC<AddRestaurantModalBodyProps> = ({handler} : AddRestaurantModalBodyProps) => {

    const [formData, setFormData] = useState({
        name: "",
        type: "",
        description: "",
        country: "",
        city: "",
        street: "",
        startHour: "",
        endHour: "",
    });


    const handleSubmit = async (e : any) => {
        e.preventDefault();
        // Here, we'll add the code to post the form data using fetch
    };

    const handleChange = (e : any) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };


    return (
        <>
            <FormInput label="Name"  formData={formData.name} onDataChange={handleChange}/>
            <FormInput label="Type"  formData={formData.type} onDataChange={handleChange}/>
            <FormInput label="Description"  formData={formData.description} onDataChange={handleChange}/>
            <FormInput label="Country"  formData={formData.country} onDataChange={handleChange}/>
            <FormInput label="City"  formData={formData.city} onDataChange={handleChange}/>
            <FormInput label="Street"  formData={formData.street} onDataChange={handleChange}/>
            <FormInput label="Start Hour"  formData={formData.startHour} onDataChange={handleChange}/>
            <FormInput label="End Hour"  formData={formData.endHour} onDataChange={handleChange}/>
            <LongButton isDisabled={false} text="Submit" handler={handleSubmit}/>
        </>
    )
}

export default AddRestaurantModalBody;