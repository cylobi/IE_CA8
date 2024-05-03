// noinspection TypeScriptCheckImport
import * as React from 'react'
import { useState } from 'react';
import BaseModal from '../components/Modals/BaseModal';
import CancelReservationModalBody from "Frontend/components/Modals/CancelReservationModalBody";

const MainView = () => {
    const [isModalOpen, setIsModalOpen] = useState(false); // State to manage modal visibility

    const toggleModal = () => {
        setIsModalOpen(!isModalOpen); // Toggle modal visibility
    };

    const handlerA = () => {
        console.log("Added");
    }

    return (
        <div className="w-100 h-100">
            <BaseModal
                title="This is a specific "
                highLightedTitle="modal"
            >
                <CancelReservationModalBody
                    handler={handlerA}
                />
            </BaseModal>
        </div>
    );
};

export default MainView;
