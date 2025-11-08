import axios from "axios"

const api = axios.create(
    {
        baseURL:"http://localhost:8080",
        headers:{
            "Content-Type":"application/json",
        },
    }
);

export const loginUser = async (mobile,password) =>{
    try{
        const response = await api.post('/api/auth/login',{mobile,password});
        return response.data;
    }
    catch(error){
        throw error.response?.data || {message:error.message};
    }

};

export const registerUser = async (username,mobile,password) =>{
    try{
        const response = await api.post('/api/auth/register',{username,mobile,password});
        return response.data;
    }
    catch(error){
        throw error.response?.data || {message:error.message};
    }

};

export default api;