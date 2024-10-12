import Body from "./Body";
import Footer from "./Footer";
import Header from "./Header";
import ModalContext from "./ModalContext";
import { createPortal } from "react-dom";
import "./Modal.css";

const Modal = ({children, isOpen, onClose, maxHeight}) => {

    return (
        <ModalContext.Provider value={{isOpen, onClose}}>
            {isOpen && createPortal((
                <>
                    <div className={isOpen ? "modal-overlay":"modal-overlay-hidden"}></div>
                    <div className="absolute top-0 left-0 bottom-0 right-0 flex justify-center items-center p-20">
                        <div className="flex flex-col w-6/12 rounded-md shadow-md bg-white" style={{minHeight:"250px",maxHeight:maxHeight}}>
                            {children}
                        </div>
                    </div>
                </>
            ), document.body)}
        </ModalContext.Provider>
    );
}

Modal.Header = Header;
Modal.Body = Body;
Modal.Footer = Footer;

export default Modal;