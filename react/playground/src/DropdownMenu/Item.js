const Item = ({children, onClick}) => {
    return (
        <a
          href="./#"
          className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900"
          role="menuitem"
          onClick={(e) => {
            e.preventDefault();
            onClick();
          }}
        >
          {children}
        </a>
      );
}

export default Item;
