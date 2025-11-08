import React, { useState } from "react";
import Button from "../components/Button";
import InputField from "../components/InputField";
import { registerUser } from "../api/api";

export default function Register(){

    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");
    const [mobile,setMobile] = useState("");
    const [success,setSuccess] = useState("");
    const [error,setError] = useState("");

    const handleSubmit = async (e) =>{
        e.preventDefault();
        setSuccess();
        setError();

        try{
            const data = await registerUser(username,mobile,password);
            setSuccess("Register successfully: "+JSON.stringify(data));
            console.log("Backend Respose ",data);
        }
        catch(err){
            setError(err.message || "Register Failed");
            console.log(err);
        }
    };

    return(
        <div className = "flex flex-col justify-center items-center h-screen">
        <div className = "flex flex-col justify-evenly items-center  bg-blue-400 h-[400px] w-[330px] rounded-[30px]">
            <h2 className="font-bold text-white text-2xl">Register</h2>
            <form onSubmit={handleSubmit} className = "flex flex-col justify-center items-center">
                <div>
                <InputField value={username} onChange={(e) => setUsername(e.target.value)} type="text"  placeholder="Username" ></InputField>
                <InputField value={mobile} onChange={(e) => setMobile(e.target.value)} type="text"  placeholder="Mobile Number"></InputField>
                <InputField value={password} onChange={(e) => setPassword(e.target.value)} type="text"  placeholder="Password"></InputField>
                </div><br/>
                <Button type="submit">Register</Button>
            </form>
            <p className=" font-sans text-white">Already have an account <a href="/login" className="text-white-500 underline">login</a></p>
        </div>
        </div>
    );
}