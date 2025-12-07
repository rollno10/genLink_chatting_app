import React, {useState,useEffect} from "react";
import axios from "axios";
import ChatListItem from "./ChatListItem";

export  default function ChatList(){

  const [chats,setChats] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/chats')
    .then(response => {
      setChats(response.data);
    })
    .catch(error => {
      console.error("Error fetching chats:",error);
    }
  },[]);

   return(
       <div className="w-full mx-auto bg-white shadow rounded-lg">
         {chats.map((chat) =>(
             <ChatListItem 
               key={chat.id} 
               Name={chat.Name} 
               Avatar={chat.Avatar} 
               lastMessage={chat.lastMessage} />))}
       </div>
   );
}