import NormalButton from "./base/NormalButton";
import HeaderLogo from "./base/HeaderLogo";

interface Pros{
    user ?: string
}

function Header({user} : Pros) {

    return (
        <>
            <HeaderLogo />
        </>
    );
}

export default Header;