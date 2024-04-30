import * as React from 'react'
import "./Modal.css"
import NormalButton from "../base/NormalButton"
import {useState} from "react";

interface CancelReservationModalBodyProps {
    handler : () => void;
}

function CancelReservationModalBody({handler}  : CancelReservationModalBodyProps){
    const [isAgreed, setAgreement] = useState(false);

    const handleCheckboxChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setAgreement(event.target.checked);
    };
    return (
        <div className="container flex-column" id="cancel-reservation-modal-body">
            <div className="alert-note">
                Note: Once you hit the Cancel button, your reserve will be canceled
            </div>
            <input type="checkbox" onChange={handleCheckboxChange}>
                I agree
            </input>
            <NormalButton
                text={"Cancel"}
                handler={handler}
                isDisabled={!isAgreed}
            />
        </div>
    )
}

export default CancelReservationModalBody;