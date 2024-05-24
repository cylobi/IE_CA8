import LoginResponse from "Frontend/types/LoginResponse";

export function storeTokensAfterLogin(data: LoginResponse) {
  localStorage.setItem("access_token", data.access_token);
  localStorage.setItem("refresh_token", data.refresh_token);

  console.log("storing tokens ", data);
}

function addAuthToken(headers: Headers) {
  headers.set(
    "Authorization",
    `Bearer ${localStorage.getItem("access_token")}`
  );
}

function isLoggedIn() {
  return localStorage.getItem("access_token") != null;
}

export function fetchAuth(
  input: string | URL | globalThis.Request,
  init?: RequestInit
): Promise<Response> {
  let newInit: RequestInit = init || {};
  const headers = newInit?.headers
    ? new Headers(newInit.headers)
    : new Headers();
  if (!headers.has("Authorization") && isLoggedIn()) {
    addAuthToken(headers);
  }
  newInit.headers = headers;
  return fetch(input, newInit);
}
