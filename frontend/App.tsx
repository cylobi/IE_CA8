import { router } from "Frontend/routes.js";
import { RouterProvider } from "react-router-dom";
import "./components/base/global.css";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  SetUserInfoContext,
  UserInfoContext,
} from "./contexts/UserInfoContext";
import { useEffect, useState } from "react";
import UserInfo from "./types/UserInfo";
import { GoogleOAuthProvider } from "@react-oauth/google";
import { fetchAuth } from "./utils/Authentication";

export default function App() {
  const [loginnedUser, setLoginnedUser] = useState<UserInfo | null>(null);

  useEffect(() => {
    fetchAuth("/api/users/current_user")
      .then((r) => r.json())
      .then((data) => setLoginnedUser(data));
  }, []);


  const CLIENT_ID = "379155225364-t3gjj5aua7homc7labkgma4a4jj0mlfd.apps.googleusercontent.com"

  return (
    <>
      <GoogleOAuthProvider clientId={CLIENT_ID}>
        <UserInfoContext.Provider value={loginnedUser}>
          <SetUserInfoContext.Provider value={setLoginnedUser}>
            <RouterProvider router={router} />
          </SetUserInfoContext.Provider>
        </UserInfoContext.Provider>
      </GoogleOAuthProvider>
    </>
  );
}
