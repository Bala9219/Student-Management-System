import React, { useState } from "react";
import API from "../api/axios";

const Register = () => {
    const [form, setForm] = useState({
        username: "",
        password: "",
        role: "USER"
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleRegister = async () => {
        await API.post("/auth/register", form);
        alert("User Registered!");
    };

    return (
        <div className="container">
            <h2>Register</h2>
            <input name="username" placeholder="Username" onChange={handleChange} />
            <input name="password" type="password" placeholder="Password" onChange={handleChange} />
            <select name="role" onChange={handleChange}>
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>
            <button onClick={handleRegister}>Register</button>
        </div>
    );
};

export default Register;