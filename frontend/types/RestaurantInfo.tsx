interface ResturantAddress {
  city: string;
  country: string;
  street: string;
}
interface RestaurantInfo {
  name: string;
  id: number;
  managerUsername: string;
  type: string;
  startTime: string;
  endTime: string;
  description: string;
  imageUrl: string;
  address: ResturantAddress;
}

// {"name":"The Commoner","id":1001,"managerUsername":"ali","type":"American","startTime":"07:00","endTime":"23:00","description":"At our gastropub, we don't distinguish between commoners and kings; we just want to feed the good people of Pittsburgh. In the restaurant, seasonal menus add a modern flair to classic comforts, complemented by a robust selection of local beers and craft spirits. It's all served in an industrial-inspired setting in downtown Pittsburgh. Come and join us for an uncommonly good time.","imageUrl":"https://resizer.otstatic.com/v2/photos/xlarge/1/31676318.webp","address":{"country":"US","city":"Pittsburgh","street":"620 William Penn Place"},"startHour":7,"endHour":23,"city":"Pittsburgh"},
