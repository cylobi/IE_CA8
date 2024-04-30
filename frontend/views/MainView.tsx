// noinspection TypeScriptCheckImport
import * as React from 'react'
import { useState } from 'react';
import BaseModal from '../components/Modals/BaseModal';

const MainView = () => {
    const [isModalOpen, setIsModalOpen] = useState(false); // State to manage modal visibility

    const handleSubmit = () => {
        // Handle form submission here
        console.log('Form submitted');
        setIsModalOpen(false); // Close the modal after submission
    };

    const toggleModal = () => {
        setIsModalOpen(!isModalOpen); // Toggle modal visibility
        console.log("asdasdafdv");
    };

    return (
        <div className="w-100 h-100">
            <BaseModal
                title="This is a specific modal"
                highlightWord="specific"
                bodyContent={<p>This is the body content for the specific modal.</p>}
                onSubmit={handleSubmit}
            />
        </div>
    );
};

export default MainView;
