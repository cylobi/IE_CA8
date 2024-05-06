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

export default function App() {
  const [loginnedUser, setLoginnedUser] = useState<UserInfo | null>(null);

  useEffect(() => {
    fetch("/api/users/current_user")
      .then((r) => r.json())
      .then((data) => setLoginnedUser(data));
  }, []);

  return (
    <>
      <UserInfoContext.Provider value={loginnedUser}>
        <SetUserInfoContext.Provider value={setLoginnedUser}>
          <RouterProvider router={router} />
        </SetUserInfoContext.Provider>
      </UserInfoContext.Provider>
    </>
  );
}
