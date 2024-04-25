import React from 'react';
import logo from '/public/Logo.svg'; // Tell webpack this JS file uses this image

function HeaderLogo(){
    return <img src={logo} alt= "Logo" />;
}

export default HeaderLogo;