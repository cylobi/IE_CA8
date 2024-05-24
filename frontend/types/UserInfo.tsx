export interface UserAddress {
  country: string;
  city: string;
}
export default interface UserInfo {
  username: string;
  role: string;
  email: string;
  address: UserAddress;
}
