import * as React from 'react'
import "./Modal.css"
import LongButton from "../base/LongButton"
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

        <div className="d-flex flex-column" id="cancel-reservation-modal-body">
            <div className="alert-note">
                Note: Once you hit the Cancel button, your reserve will be canceled
            </div>
            <label>
                <input type="checkbox" onChange={handleCheckboxChange} />
                I agree
            </label>
            <LongButton
                text="Add"
                handler={handler}
                isDisabled={!isAgreed}
            />
        </div>

    )
}

export default CancelReservationModalBody;