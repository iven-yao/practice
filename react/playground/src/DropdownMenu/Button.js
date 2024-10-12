import { useContext } from "react";
import { DropdownContext } from "./DropdownContext";

const Button = ({children}) => {
    const {isOpen, setIsOpen} = useContext(DropdownContext);

    return (
        <button 
            onClick={() => setIsOpen(!isOpen)}
            className="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-100 focus:ring-indigo-500"
        >
            {children}
        </button>
    );
}

export default Button;