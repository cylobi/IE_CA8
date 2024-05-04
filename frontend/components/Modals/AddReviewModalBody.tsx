import * as React from 'react';
import "./Modal.css";
import LongButton from "../base/LongButton";
import RatingFormInput from '../base/RatingFormInput';
import FormInput from "../base/FormInput";
import {useState} from "react";
import styled from 'styled-components';

interface AddReviewModalBodyProps {
    handler : (e:any) => void;
}

const AddReviewModalBody : React.FC<AddReviewModalBodyProps> = ({handler} : AddReviewModalBodyProps) => {

    const [formData, setFormData] = useState({
        foodQualityRating: 0,
        serviceRating: 0,
        ambienceRating: 0,
        overallRating: 0,
        comment: ""
    });

    const [isAbleToSubmit, setIsAbleToSubmit] = useState(false);

    const checkIsAbleToSubmit = () => {
        const isValid = Object.values(formData).every(value => {
            if (typeof value === 'number') {
                return value > 0;
            }
            return value !== '';
        });
        setIsAbleToSubmit(isValid);
    }

    const handleChange = (key: keyof typeof formData, value: number | string) => {
        setFormData(prevState => ({
            ...prevState,
            [key]: value
        }));
        checkIsAbleToSubmit();
    };

    const handleSubmit = async (e : any) => {
        e.preventDefault();
    };


    return (
        <>
            <RatingFormInput label="Food Quality" stars={formData.foodQualityRating} onDataChange={(value) => handleChange('foodQualityRating', value)}/>
            <RatingFormInput label="Service" stars={formData.serviceRating} onDataChange={(value) => handleChange('serviceRating', value)}/>
            <RatingFormInput label="Ambience" stars={formData.ambienceRating} onDataChange={(value) => handleChange('ambienceRating', value)}/>
            <RatingFormInput label="Overall" stars={formData.overallRating} onDataChange={(value) => handleChange('overallRating', value)}/>
            <FormInput label="Comment" formData={formData.comment} onDataChange={(value) => handleChange('comment', value)} />
            <LongButton isDisabled={!isAbleToSubmit} text="Submit" handler={handleSubmit}/>
            <LongButton isDisabled={false} text="Cancel" handler={handler}/>
        </>
    )
}

export default AddReviewModalBody;