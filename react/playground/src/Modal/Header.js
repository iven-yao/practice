import { useContext } from "react"
import ModalContext from "./ModalContext"

const Header = ({children}) => {
    const {onClose} = useContext(ModalContext);
    
    return (
        <div className="flex justify-between border-b p-3">
            <div className="text-xl font-bold">
                {children}
            </div>
            <div className="cursor-pointer text-gray-500" onClick={onClose}>
                X
            </div>
        </div>
    );
}

export default Header;