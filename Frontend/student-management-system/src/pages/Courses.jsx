import React, { useEffect, useState } from "react";
import API from "../api/axios";

const Courses = () => {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        fetchCourses();
    }, []);

    const fetchCourses = async () => {
        const res = await API.get("/api/courses");
        setCourses(res.data.courses);
    };

    return (
        <div>
            <h2>Courses</h2>
            {courses.map((c) => (
                <div className="card" key={c.id}>
                    <h4>{c.title}</h4>
                    <p>{c.duration} - ₹{c.fee}</p>
                </div>
            ))}
        </div>
    );
};

export default Courses;