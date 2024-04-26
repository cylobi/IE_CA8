import React from "react"

interface Pros{
    user ?: string
}

function Footer({user} : Pros) {
    const text = "Copyright © 2024 Mizdooni - All rights reserved.";

    const footerStyle : React.CSSProperties = {
        //   position: "absolute",
          bottom: 0,
          width: "100%",
          height: "2.5rem",
            borderTop: ".05rem solid #e5e5e5",
            backgroundColor: "#ED3545",
            boxShadow: "0px 0px 4px #303030",
              /* Center vertically and horizontally */
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
    };
    const footerTextStyle: React.CSSProperties = {
          color: "#FFFCFC",
          fontSize: "14px",
    };
    return (
    <>
        <footer style={footerStyle}>
           <div className="h-100 row align-items-center" style={footerTextStyle}>
            {text}
           </div>
        </footer>
        </>
    );
}

export default Footer;