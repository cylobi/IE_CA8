export interface UserAddress {
  country: string;
  city: string;
}
export default interface UserInfo {
  username: string;
  role: string;
  password: string;
  email: string;
  address: UserAddress;
}
