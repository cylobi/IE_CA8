import * as React from 'react';
import Header from "../components/Header";
import Footer from "../components/Footer";
import Register from "Frontend/components/Register";
import Login from "Frontend/components/Login";
import styled from 'styled-components';
import {useState, useEffect} from "react";
import {GoogleLogin, useGoogleLogin} from "@react-oauth/google";
import {jwtDecode} from "jwt-decode";
import {storeTokensAfterLogin} from "Frontend/utils/Authentication";
import { useNavigate } from "react-router-dom";

const Background = styled.div`
    width : 100%;
    height: 100%;
    background-color : #FFFCFC;
    `;

// const GoogleLoginButton = styled.button`
//     display: inline-block;
//     padding: 10px 20px;
//     font-size: 16px;
//     margin-top: 20px;
//     border-radius: 12px;
//     background-color: blue;
//     color: white;
//     cursor: pointer;
//     transition: background-color 0.3s ease;
//
//     &:hover {
//         background-color: darkblue;
//     }
// `

const AuthenticationView = () => {
    enum AuthMethod {
        Register,
        Login
    }

    const [authMethod, setAuthMethod] = useState(AuthMethod.Login);

    // const login = useGoogleLogin({
    //     onSuccess: (response) => {
    //         // Accessing user's email and name from the response
    //         const userEmail = response.profile.email;
    //         const userName = response.profile.name;
    //         console.log(`User Email: ${userEmail}, User Name: ${userName}`);
    //     },
    //     onError: (error) => console.log("Login Failed:", error)
    // });

    const registerIndicatorStyle: React.CSSProperties = {
        borderTopLeftRadius: '12px',
        borderBottomLeftRadius: '12px',
        backgroundColor: authMethod === AuthMethod.Register ? "#ED3545" : "#FFF6F7",
    } as React.CSSProperties;

    const loginIndicatorStyle : React.CSSProperties = {
        borderTopRightRadius : '12px',
        borderBottomRightRadius: '12px',
        backgroundColor: authMethod === AuthMethod.Login ? "#ED3545" : "#FFF6F7",
    } as React.CSSProperties;

    const navigate = useNavigate();
    const handleSubmit = async (jwt) => {
        try {
            const response = await fetch("/api/auth/googleOauth", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(jwt),
            });

            if (!response.ok) {
                throw new Error("Network response was not ok");
            }

            const data = await response.json();
            console.log(data);
            storeTokensAfterLogin(data);
            navigate("/home");
        } catch (error) {
            console.error("There was a problem with the fetch operation:", error);
        }
    };

    return (
        <>
            <Background className="d-flex flex-column justify-content-center align-items-center mh-100 ">
                <div className="container my-auto w-50">
                    <div className="container d-flex w-100 flex-row mb-2">
                        <div id="registerIndicator" className='w-50 text-center' style={registerIndicatorStyle} onClick={() => {setAuthMethod(AuthMethod.Register)}}>
                            Register
                        </div>
                        <div id="LoginIndicator" className='w-50 text-center' style={loginIndicatorStyle} onClick={() => {setAuthMethod(AuthMethod.Login)}}>
                            Login
                        </div>
                    </div>
                    {authMethod === AuthMethod.Register && <Register/>}
                    {authMethod === AuthMethod.Login && <Login/>}
                    <div id="google-login_button" class="container align-content-center">
                        <GoogleLogin
                            onSuccess={res => {
                                handleSubmit(res);
                            }}
                            onError={() => {
                                console.log('Login Failed');
                            }}
                        />
                    </div>
                </div>
            </Background>
            <Footer className="align-self-end"/>
        </>
    );
}

export default AuthenticationView;