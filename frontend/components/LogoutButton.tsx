import {
  SetUserInfoContext,
  useSetUserInfoContext,
} from "Frontend/contexts/UserInfoContext";
import NormalButton from "./base/NormalButton";
import { useNavigate } from "react-router-dom";

export default function LogoutButton() {
  // Logout request
  const setUserInfo = useSetUserInfoContext();
  const navigate = useNavigate();

  function onLogoutResponse(response: any) {
    console.log("Logout is recived");

    setUserInfo(null);
    navigate("/");
  }

  function sendLogoutRequest() {
    console.log("Sending logout request...");
    const requestOptions = {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({}),
    };

    fetch("/users/current_user/logout", requestOptions).then((response) =>
      onLogoutResponse(response)
    );
  }

  return (
    <NormalButton
      text="Logout"
      otherClass="btn-sm"
      handler={sendLogoutRequest}
    />
  );
}
