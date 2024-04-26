

export default function CustomerTopMessage(){
    const alertStyle: React.CSSProperties = {
        borderRadius: '12px',
        borderColor: 'transparent',
      };
    const wrapperStyle:React.CSSProperties = {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        height: '100%',
      }

    const alertInfoStyle : React.CSSProperties = {
        backgroundColor: '#FFF6F7',
        borderColor: "pink"
    };
      

    return (
        <div className="container alert alert-info" style={alertInfoStyle}>
        <div className="wrapper" style={wrapperStyle} >
            <p>
                Your reservations are also emailed to&nbsp;&nbsp;
                <a href="mailto:Tom_holland@ut.ac.ir" style={ {color: "#ed3545"} } >Tom_holland@ut.ac.ir</a>
            </p>
            <p> Address: Tehran, Iran</p>
        </div>
        </div>
    );
}