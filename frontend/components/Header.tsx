import NormalButton from "./base/NormalButton";
import HeaderLogo from "./base/HeaderLogo";
import { useNavigate } from "react-router-dom";
import "./Header.css";
import { useUserInfoContext } from "Frontend/contexts/UserInfoContext";

interface Pros {
  user?: string;
}

function Header({ user }: Pros) {
  const userInfo = useUserInfoContext();
  const navigate = useNavigate();
  return (
    <nav id="mizdooni_header" className="navbar navbar-expand-md mb-4">
      <div className="container-fluid">
        <HeaderLogo />
        <p className="nav-item mr-auto navbar-nav" id="header_brand_text">
          Reserve Table from anywhere!
        </p>
        <p className="nav-item navbar-nav" id="welcome_message">
          {userInfo && "Welcome, " + userInfo.username}
        </p>
        <NormalButton
          otherClass="mt-2 mt-md-0"
          text="My Reservations"
          handler={() => {
            navigate("/home");
          }}
          isDisabled={false}
        />
      </div>
    </nav>
  );
}

export default Header;
