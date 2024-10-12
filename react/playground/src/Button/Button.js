const Button = ({children, onClick}) => {
    return (
        <button 
            onClick={() => onClick()}
            className="px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-100 focus:ring-indigo-500"
        >
            {children}
        </button>
    );
}

export default Button;