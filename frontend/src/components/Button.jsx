import React from "react"

export default function Button({children,type = "button"}){
    return(
        <button className = "h-[40px] w-[100px] bg-white font-bold rounded-lg font-sans"
        type = {type}
        >
        {children}
        </button>
    );
}