import * as React from 'react';
import * as ReactDom from 'react-dom';
import {ReactNode} from "react";
import "./Modal.css"


interface ModalProps {
    show: boolean;
    onCloseButtonClick: () => void;
    children : ReactNode
}

const Modal: React.FC<ModalProps> = ({  show,
                                        onCloseButtonClick,
                                        children}) => {
    if (!show) {
        return null;
    }

    return (
        <div className="modal-wrapper">
            <div className="modal">
                {children}
            </div>
        </div>
    );
};

export default Modal;
