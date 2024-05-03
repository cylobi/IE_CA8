import * as React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

interface BaseModalProps {
    title: string;
    highLightedTitle: string;
}

const BaseModal: React.FC<React.PropsWithChildren<BaseModalProps>> = (
        {title,
        highLightedTitle,
        children }) => {

    return (
        <div className="d-flex modal" >
            <div className="modal-dialog modal-dialog-centered">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">{title} <span style={{color: '#ED3545'}}> {highLightedTitle} </span> </h5>
                        <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div className="modal-body">
                        {children}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default BaseModal;
