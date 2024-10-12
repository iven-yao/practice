import { useState } from "react"
import { DropdownContext } from "./DropdownContext";
import Button from "./Button";
import Items from "./Items";
import Item from "./Item";

const DropdownMenu = ({children, className}) => {
    const [isOpen, setIsOpen] = useState(false);

    return (
        <DropdownContext.Provider value={{isOpen, setIsOpen}}>
            {isOpen &&
                <div className="absolute top-0 left-0 right-0 bottom-0 bg-black bg-opacity-15" onClick={() => setIsOpen(false)}>

                </div>
            }
            <div className="relative inline-block">
                {children}
            </div>
        </DropdownContext.Provider>
    );
}

DropdownMenu.Button = Button;
DropdownMenu.Items = Items;
DropdownMenu.Item = Item;

export default DropdownMenu;