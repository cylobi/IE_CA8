import * as React from 'react'
import { ReactNode } from 'react'

interface ModalHeaderProps {
    children: ReactNode;
}

const ModalHeader: React.FC<ModalHeaderProps> = ({ children }) => {
    return (
        <div className="modal-header">
            { children }
        </div>
    );
};

export default ModalHeader;
