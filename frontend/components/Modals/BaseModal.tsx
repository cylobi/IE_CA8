import * as React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

interface BaseModalProps {
    title: string;
    highlightWord: string;
    bodyContent: React.ReactNode;
    onSubmit: () => void;
}

const BaseModal: React.FC<BaseModalProps> = ({ title, highlightWord, bodyContent, onSubmit }) => {
    const highlightedTitle = title.split(' ').map(word => word === highlightWord ? <span className="text-danger">{word}</span> : word).join(' ');

    // Adjust the type of the handleSubmit function to match MouseEventHandler<HTMLButtonElement>
    const handleSubmit: React.MouseEventHandler<HTMLButtonElement> = (e) => {
        e.preventDefault(); // This line is unnecessary for a button click handler and can be removed
        onSubmit();
    };

    return (
        <div className="modal fade" id="baseModal" tabindex="-1" aria-labelledby="baseModalLabel" aria-hidden="true">
            <div className="modal-dialog modal-dialog-centered">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title" id="baseModalLabel">{highlightedTitle}</h5>
                        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div className="modal-body">
                        {bodyContent}
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" className="btn btn-primary" onClick={handleSubmit}>Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default BaseModal;
