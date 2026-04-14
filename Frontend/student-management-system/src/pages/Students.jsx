import React, { useEffect, useState } from "react";
import API from "../api/axios";

const Students = () => {
    const [students, setStudents] = useState([]);

    useEffect(() => {
        fetchStudents();
    }, []);

    const fetchStudents = async () => {
        const res = await API.get("/api/students");
        setStudents(res.data.students);
    };

    return (
        <div>
            <h2>Students</h2>
            {students.map((s) => (
                <div className="card" key={s.id}>
                    <h4>{s.name}</h4>
                    <p>{s.email}</p>
                </div>
            ))}
        </div>
    );
};

export default Students;