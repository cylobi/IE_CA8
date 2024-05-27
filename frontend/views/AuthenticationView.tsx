import * as React from 'react';
import Header from "../components/Header";
import Footer from "../components/Footer";
import Register from "Frontend/components/Register";
import Login from "Frontend/components/Login";
import styled from 'styled-components';
import {useState, useEffect} from "react";
import {GoogleLogin, useGoogleLogin} from "@react-oauth/google";


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

    const [user, setUser] = useState([]);

    const login = useGoogleLogin({
        onSuccess: (codeResponse) => {setUser(codeResponse)
            console.log(codeResponse)
        },
        onError: (error) => console.log("Login Failed:", error)
    });

    useEffect(() => {
        if (user) {
            fetch(`https://www.googleapis.com/oauth2/v1/userinfo?access_token=${user.access_token}`, {
                method: 'GET',
                headers: {
                    Authorization: `Bearer ${user.access_token}`,
                    Accept: "application/json",
                },
            })
                .then(response => response.json())
                .then(data => console.log(data))
                .catch(error => console.log(error));
        }
    }, [user]);

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
                            onSuccess={credentialResponse => {
                                console.log(credentialResponse);
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