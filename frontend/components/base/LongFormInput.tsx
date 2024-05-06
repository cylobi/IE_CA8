import * as React from 'react';
import styled from 'styled-components';

interface Props {
    label : string;
    value: string;
    onChange : (event: React.ChangeEvent<HTMLInputElement>, field: string) => void;
}

const LongInputBox = styled.input`
        border : none;
        border-radius : 12px;
        background-color: #F5F5F5;
        :focus {
            decoration : none;
        }
    `

const LongFormInput : React.FC<Props> = ({label, value, onChange} : Props) => {

    return (
        <div className="d-flex flex-column">
            <label> {label} </label>
            <LongInputBox className=" justify-content-center" type="text" value={value} onChange={onChange}/>
        </div>
    )
};

export default LongFormInput;