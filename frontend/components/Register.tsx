import * as React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

import LongFormInput from "Frontend/components/base/LongFormInput";
import LongButton from "Frontend/components/base/LongButton";

const Register = () => {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    country: "",
    city: "",
    password: "",
    role: "",
  });
  const navigate = useNavigate();

  const handleChange = (
    event: React.ChangeEvent<HTMLInputElement>,
    field: keyof typeof formData
  ) => {
    setFormData((prevState) => ({ ...prevState, [field]: event.target.value }));
  };

  const handleSubmit = async () => {
    try {
      const response = await fetch("/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      const data = await response.json();
      console.log(data);
      navigate("/home");
    } catch (error) {
      console.error("There was a problem with the fetch operation:", error);
    }
  };

  return (
    <div className="container flex-column">
      <LongFormInput
        className="my-2"
        label="Username"
        value={formData.username}
        onChange={(event) => handleChange(event, "username")}
      />
      <LongFormInput
        className="my-2"
        label="Email"
        value={formData.email}
        onChange={(event) => handleChange(event, "email")}
      />
      <LongFormInput
        className="my-2"
        label="Country"
        value={formData.country}
        onChange={(event) => handleChange(event, "country")}
      />
      <LongFormInput
        className="my-2"
        label="City"
        value={formData.city}
        onChange={(event) => handleChange(event, "city")}
      />
      <LongFormInput
        className="my-2"
        label="Password"
        value={formData.password}
        onChange={(event) => handleChange(event, "password")}
      />
      <div className="d-flex flex-row justify-content-between w-50 my-2">
        <div>I am</div>
        <div>
          <input
            type="radio"
            id="customer"
            name="role"
            value="Customer"
            checked={formData.role === "Customer"}
            onChange={(event) => handleChange(event, "role")}
          />
          <label className="m-1" htmlFor="customer">
            Customer
          </label>
        </div>
        <div>
          <input
            type="radio"
            id="manager"
            name="role"
            value="Manager"
            checked={formData.role === "Manager"}
            onChange={(event) => handleChange(event, "role")}
          />
          <label className="m-1" htmlFor="manager">
            Manager
          </label>
        </div>
      </div>
      <LongButton
        className="my-2"
        text="Register"
        isDisabled={false}
        handler={handleSubmit}
      />
    </div>
  );
};

export default Register;
