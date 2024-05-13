// import HeroLogo from ""
import LogoImage from "Frontend/public/images/Logo.svg";
import HeroBackground from "Frontend/public/images/HeroBackground.png";
import styled from "styled-components";
import SearchBox from "./SearchBox";

export default function MainPageHero() {
  const HeroContainerStyle: React.CSSProperties = {
    backgroundImage: "url(" + HeroBackground + ")",
    backgroundRepeat: "no-repeat",
    backgroundPosition: "center",
    backgroundSize: "cover",
    position: "relative",
    height: "830px",
  };

  const LogoAndSearchContainerStyle: React.CSSProperties = {
    position: "absolute",
    top: "25%",
  };

  const RestaurantNameInput = styled.input`
    border-radius: 12px;

    :focus {
      box-shadow: 0 0 0 0.2rem pink;
    }
  `; //TODO fix shadow color
  // TODO ask javad for input
  return (
    <>
      <div
        className="jumbotron jumbotron-fluid m-0 py-4"
        id="hero_container"
        style={HeroContainerStyle}
      >
        <div
          style={LogoAndSearchContainerStyle}
          className="container h-100 ml-5 col-md-6"
          id="logo_search_container"
        >
          <img
            alt="img"
            style={{ maxHeight: "25%" }}
            className="h-50 mx-auto row mb-2"
            src={LogoImage}
            id="hero_logo"
          />
          <SearchBox />
        </div>
      </div>
    </>
  );
}
