import React from "react"

export default function InputField({value,onChange,type,placeholder}){
    return(
            <input className = "block h-[40px] w-[280px] rounded-[10px] mb-3 font-sans" 
            value={value}
            onChange={onChange}
            type={type}
            placeholder={placeholder}
            />
    );
}