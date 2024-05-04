import { useState } from 'react';

const useModal = () => {
    const [isShowing, setIsShowing] = useState(false);
    function toggle() {
        setIsShowing(!isShowing);
    }
}

export default useModal;
