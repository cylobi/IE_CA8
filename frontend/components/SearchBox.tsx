import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import DropDown from "./base/DropDown";
import NormalButton from "./base/NormalButton";

function SearchBox() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: null,
    type: null,
    city: null,
  });

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
    fetch("/restaurants/all_types")
      .then((r) => r.json())
      .then((data) => {
        setTypes(data);
      });
  }
  React.useEffect(fetchAllTypes, []);

  const handleChange = (event) => {
    console.log("changed ", event.target);
    const { name, value } = event.target;
    setFormData((prevFormData) => ({ ...prevFormData, [name]: value }));
    console.log("changed ", formData);
  };
  const handleSubmit = (event) => {
    event.preventDefault();

    let parameters = [];
    if (formData.city != null) {
      parameters.push("city=" + formData.city);
    }
    if (formData.type != null) {
      parameters.push("type=" + formData.type);
    }
    if (formData.name != null) {
      parameters.push("name=" + formData.name);
    }
    let parametersStr = parameters.join("&");
    let searchUrl = "/restaurants_page/?" + parametersStr;
    console.log("Search request... ", searchUrl);
    navigate(searchUrl);
  };

  return (
    <form onSubmit={handleSubmit}>
      <DropDown
        items={allTypes}
        title="Restaurant"
        name="type"
        value={formData.type || ""}
        onChange={handleChange}
      />
      <input
        type="text"
        id="name"
        name="name"
        value={formData.name || ""}
        onChange={handleChange}
      />

      <DropDown
        items={cities}
        title="Location"
        name="city"
        value={formData.city || ""}
        onChange={handleChange}
      />
      <NormalButton
        otherClass="col-2 mx-2 btn"
        text="Search"
        handler={(event) => {
          handleSubmit(event);
        }}
      />
    </form>
  );
}

export default SearchBox;
