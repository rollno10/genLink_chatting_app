import React from "react";

export default  function ChatListItem({Name,Avatar}){

  return(
    <div className = "flex justify-center p-3">
      {/* Avatar */}
      <div className = "bg-blue-500 h-12 w-12 rounded-full">
        <img src={Avatar} alt={Name}  className = "object-cover"/>
      </div>
      {/* Name and Last Message */}
      <div className = "flex-1">
        <div className = "flex justify-between">
        <h2 className="font-semibold text-gray-800">{Name}</h2>
        </div>
        <p className="text-sm text-gray-600">{lastMessage}</p>
      </div>
      
    </div> 
  );
}