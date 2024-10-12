import { useState } from 'react';
import './App.css';
import DropdownMenu from './DropdownMenu/DropdownMenu';
import Modal from './Modal/Modal';
import Button from './Button/Button';

function App() {

  const [showModal, setShowModal] = useState(false);
  const handleModalClose = () => {
    setShowModal(false);
  }

  const handleModalShow = () => {
    setShowModal(true);
  }

  return (
    <div className='flex p-12 justify-start gap-2'>
      <DropdownMenu>
        <DropdownMenu.Button>Dropdown Menu</DropdownMenu.Button>
        <DropdownMenu.Items>
          <DropdownMenu.Item onClick={() => console.log('Edit')}>Edit</DropdownMenu.Item>
          <DropdownMenu.Item onClick={() => console.log('Duplicate')}>Duplicate</DropdownMenu.Item>
          <DropdownMenu.Item onClick={() => console.log('Delete')}>Delete</DropdownMenu.Item>
        </DropdownMenu.Items>
      </DropdownMenu>

      <Button onClick={handleModalShow}>Show Modal</Button>
      <Modal isOpen={showModal} onClose={handleModalClose} maxHeight={'500px'}>
        <Modal.Header>Modal Title</Modal.Header>
        <Modal.Body>Modal Content</Modal.Body>
        <Modal.Footer>
          <Button onClick={handleModalClose}>Close</Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default App;
