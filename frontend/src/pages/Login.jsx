import React, { useState } from "react";
import Button from "../components/Button";
import InputField from "../components/InputField";
import { loginUser } from "../api/api";

export default function Login(){

    const [mobile,setMobile] = useState("");
    const [password,setPassword] = useState("");
    const [success,setSuccess] = useState("");
    const [error,setError] = useState("");
    const [user,setUser] = useState("");

    const handleSubmit = async (e) => {
    
    e.preventDefault();
    setError();
    setSuccess();

    try{
        const data = await loginUser(mobile,password);
        localStorage.setItem("token",data.token);
        setUser({id:data.id, username:data.username, mobile:data.mobile});
        setSuccess("Login successfull: "+JSON.stringify(data));
        console.log("Backend Response: ",data);
    }
    catch(err){
        setError(err.message || "Login Failed");
        console.error(err);
    }
    };

    return(
        <div className = "flex flex-col justify-center items-center h-screen">
        <div className = "flex flex-col justify-evenly items-center  bg-blue-500 h-80 w-[400px] rounded-[30px]">
            <h2 className="font-bold text-white text-2xl">Login</h2>
            <form onSubmit={handleSubmit} className = "flex flex-col justify-center items-center">
                <div>
                <InputField value={mobile} onChange={(e) => setMobile(e.target.value)} type="text"  placeholder="   Mobile Number"></InputField>
                <InputField value={password} onChange={(e) => setPassword(e.target.value)} type="text"  placeholder="   ********"></InputField>
                </div>
                <Button type="submit" className = "h-[40px] w-[100px] bg-white font-bold rounded-lg font-sans">Login</Button>
                
            </form>
            <p className="font-sans text-white">Create an account <a href="/Register" className="text-white-500 underline">Register</a></p>
        </div>
        </div>
    );
}