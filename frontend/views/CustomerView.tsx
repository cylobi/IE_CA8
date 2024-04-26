import Header from "../components/Header"
import Footer from "../components/Footer"

function CustomerView () {
    const backgroundCss = {
        backgroundColor: "#fffcfc",
        color: "white",
        height: "100vh",
        width: "100%",
        margin: 0
    };
    return (
    <div style={backgroundCss}>
    <Header />
    <Footer />
    </div>
    );
}

export default CustomerView;