import {
  SetUserInfoContext,
  useSetUserInfoContext,
} from "Frontend/contexts/UserInfoContext";
import NormalButton from "./base/NormalButton";
import { useNavigate } from "react-router-dom";
import { removeTokensAfterLogout } from "Frontend/utils/Authentication";

export default function LogoutButton() {
  // Logout request
  const setUserInfo = useSetUserInfoContext();
  const navigate = useNavigate();

  function onLogoutResponse(response: Response) {
    if (response.ok) {
      removeTokensAfterLogout();
      setUserInfo(null);
      navigate("/");
    }
    console.log("Logout is recived");
  }

  function sendLogoutRequest() {
    console.log("Sending logout request...");
    const requestOptions = {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({}),
    };

    fetch("/api/auth/logout", requestOptions).then((response) =>
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
