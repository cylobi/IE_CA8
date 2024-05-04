import Footer from "Frontend/components/Footer";
import Header from "Frontend/components/Header";
import UserInfo from "Frontend/types/UserInfo";
import { useState } from "react";
import { json } from "react-router-dom";

export default function ResturantsView() {
  const backgroundCss = {
    backgroundColor: "#fffcfc",
    color: "white",
    height: "100vh",
    width: "100%",
    margin: 0,
  };

  const [resturantsData, setRestaurantsData] = useState<UserInfo[] | null>();
  fetch("/restaurants")
    .then((r) => r.json())
    .then((data) => {
      setRestaurantsData(data);
    })
    .catch((any) => {
      console.log("ohhh ... ", any); // TODO remove it :)
    });

  return (
    <div style={backgroundCss}>
      <Header />
      <div className="container-fluid">{}</div>
      <Footer />
    </div>
  );
}
