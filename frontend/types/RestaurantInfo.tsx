export interface ResturantAddress {
  city: string;
  country: string;
  street: string;
}
export default interface RestaurantInfo {
  name: string;
  id: number;
  managerUsername: string;
  type: string;
  startTime: string;
  endTime: string;
  description: string;
  imageUrl: string;
  address: ResturantAddress;
  overall: number;
  reviewsCount: number;
}
