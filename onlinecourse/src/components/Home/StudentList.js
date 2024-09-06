import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { authAPIs, endpoints } from "../../configs/APIs";

// Component hiển thị danh sách học sinh
const StudentList = () => {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(true);
  const { id } = useParams(); // Lấy courseId từ URL

  useEffect(() => {
    // Fetch danh sách học sinh đăng ký vào khóa học với courseId
    authAPIs().get(endpoints['enrollments'](id))
      .then((response) => {
        setStudents(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Có lỗi xảy ra khi tải dữ liệu", error);
        setLoading(false);
      });
  }, [id]);

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <div style={styles.gridContainer} className="bg-white">
      <div style={styles.gridHeader}>
        <span style={styles.gridItem}>Tên người đăng ký</span>
        <span style={styles.gridItem}>Số nội dung đã thực hiện</span>
      </div>
      {students.map((student) => (
        <div key={student.id} style={styles.gridRow}>
          <span style={styles.gridItem}>{student.fullName}</span>
          <span style={styles.gridItem}>{student.progress} Nội dung</span>
        </div>
      ))}
    </div>
  );
};

// CSS styles cho grid
const styles = {
  gridContainer: {
    display: "grid",
    gridTemplateColumns: "1fr 1fr",
    gap: "10px",
    padding: "20px",
    border: "1px solid #ccc",
  },
  gridHeader: {
    display: "contents",
    fontWeight: "bold",
    borderBottom: "2px solid #333",
  },
  gridRow: {
    display: "contents",
  },
  gridItem: {
    padding: "10px",
    border: "1px solid #ccc",
  },
};

export default StudentList;
