import UserInfo from "Frontend/types/UserInfo";
import { Dispatch, SetStateAction, createContext, useContext } from "react";

export const UserInfoContext = createContext<UserInfo | null>(null);
export const SetUserInfoContext = createContext<
  Dispatch<SetStateAction<UserInfo | null>> | undefined
>(undefined);

export function useUserInfoContext() {
  const user = useContext(UserInfoContext);

  if (user === null) {
    return null;
    //   throw new Error('useUserContext must be used with a DashboardContext');
  }

  return user;
}
export function useSetUserInfoContext() {
  const setUser = useContext(SetUserInfoContext);
  if (setUser === undefined) {
    throw new Error("useUserContext must be used with a DashboardContext");
  }

  return setUser;
}
