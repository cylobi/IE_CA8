import MainView from "Frontend/views/MainView.js";
import CustomerView from "Frontend/views/CustomerView.js";

import {
    createBrowserRouter,
    RouteObject
} from "react-router-dom";

export const routes: readonly RouteObject[] = [
  { path: "/", element: <MainView /> },
  { path: "/home", element: <CustomerView /> },

];

export const router = createBrowserRouter([...routes], {basename: new URL(document.baseURI).pathname });
