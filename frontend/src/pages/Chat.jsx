import React from "react";

export default function  Chat(){

  return(
    <div className = "grid grid-rows-[60px_1fr] grid-cols-2 h-screen">

      <div className = "text-blue-500 font-bole text-lg">
        <h1>GENLINK</h1>
      </div>

      <div className = "bg-gray-100 text-gray-800 font-semibold">
        <h1>USER</h1>
      </div>
      
      <div className = "bg-blue-500 row-span-2 col-span-1">
        <ChatList/>
      </div>

      <div className = "bg-gray-100 row-span-2 col-span-1">
        <h1>CHAT MESSAGE</h1>
      </div>
      
    </div>);
}