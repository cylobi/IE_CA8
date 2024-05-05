import * as React from "react";
import "./Modal.css";
import LongButton from "../base/LongButton";
import { useState } from "react";
import styled from "styled-components";

interface AddTableModalBodyProps {
  handler: () => void;
}

const AddTableModalBody: React.FC<AddTableModalBodyProps> = ({
  handler,
}: AddTableModalBodyProps) => {
  const [formData, setFormData] = useState({
    numberOfSeats: 1,
  });

  const InputBox = styled.input`
    background-color: #f5f5f5;
    border-radius: 12px;
    border: none;
    padding: 1rem;
    width: 30%;
    box-sizing: border-box;
  `;

  const handleSubmit = async (e: React.ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    // Here, we'll add the code to post the form data using fetch
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  return (
    <>
      <div className="d-flex justify-content-between align-content-center my-2">
        <label className="my-auto"> Number of Seats</label>
        <InputBox
          type="number"
          name="numberOfSeats"
          value={formData.numberOfSeats}
          onChange={handleChange}
        />
      </div>
      <LongButton isDisabled={false} text="Submit" handler={handleSubmit} />
    </>
  );
};

export default AddTableModalBody;
