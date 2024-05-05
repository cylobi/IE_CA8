import React, { useState } from "react";
import DropDown from "./base/DropDown";

function SearchBox() {
  const [formData, setFormData] = useState({ name: "", type: "", city: "" });

  const [cities, setCities] = useState<string[]>([]);
  function fetchAllCities() {
    fetch("/restaurants/all_cities")
      .then((r) => r.json())
      .then((data) => {
        setCities(data);
      });
  }
  React.useEffect(fetchAllCities, []);

  const [allTypes, setTypes] = useState<string[]>([]);
  function fetchAllTypes() {
    fetch("/restaurants/all_cities")
      .then((r) => r.json())
      .then((data) => {
        setTypes(data);
      });
  }
  React.useEffect(fetchAllTypes, []);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({ ...prevFormData, [name]: value }));
  };
  const handleSubmit = (event) => {
    event.preventDefault();
  };

  return (
    <form onSubmit={handleSubmit}>
      <DropDown items={allTypes} title="Restaurant"></DropDown>
      <DropDown items={cities} title="Location" />
      {/* <label htmlFor="name">Name:</label>
      <input type="text" id="name" name="name" value={formData.name} onChange={handleChange}/>

      <label htmlFor="email">Email:</label>
      <input type="email" id="email" name="email" value={formData.email} onChange={handleChange}/>

      <label htmlFor="message">Message:</label>
      <textarea id="message" name="message" value={formData.message} onChange={handleChange}/>

      <button type="submit">Submit</button> */}
    </form>
  );
}

export default SearchBox;

{
  /* <form>
<div className="row">
  <DropDown className="form-control col-2 mx-2"></DropDown>

  <select
    className="mizdooni_dropdown form-control col-2 mx-2"
    id="restaurant_type"
  >
    <option selected disabled hidden>
      Restaurant
    </option>
    <option>Irani</option>
    <option>Pizza</option>
    <option>Italy</option>
    <option>Dizy</option>
  </select>
  <RestaurantNameInput
    className="form-control col-5 mx-2"
    type="text"
    placeholder="Type Restaurant..."
    id="restaurant_name"
  />
  <button type="submit" className="col-2 mx-2 btn normal_button">
    Search
  </button>
</div>
</form> */
}
