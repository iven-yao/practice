import { useContext } from "react";
import { DropdownContext } from "./DropdownContext"

const Items = ({children}) => {
    const {isOpen} = useContext(DropdownContext);

    return (
        isOpen?
        <div className="absolute left-0 mt-2 w-56 bg-white rounded-md shadow-lg ring-1 ring-black ring-opacity-5">
            <div className="py-1" role="menu" aria-orientation="vertical" aria-labelledby="options-menu">
                {children}
            </div>
        </div>
        :
        null
    )
}

export default Items;