import { useEffect, useState } from "react";
import { authAPIs, endpoints } from "../config/APIs";
import { useParams } from "react-router";
import { Alert, Spinner, Button } from "react-bootstrap";
import '../css/InstructorCourseEnrollment.css';


const InstructorCourseEnrollment = () => {
    const [enrollments, setEnrollments] = useState([]);
    const [loading, setLoading] = useState(true);
    const [countContent, setCountContent] = useState(0);

    const { id } = useParams();

    useEffect(() => {
        setLoading(true);
        const fetchEnrollments = async () => {
            try {
                const response = await authAPIs().get(endpoints['course-enrollments'](id));
                setEnrollments(response.data);
            } catch (error) {
                console.error("Error fetching enrollments:", error);
            } finally {
                setLoading(false);
            }
        };
        fetchEnrollments();
    }, [id]);

    useEffect(() => {
        const fetchContents = async () => {
            try {
                setLoading(true);
                const response = await authAPIs().get(endpoints['content'](id));
                setCountContent(response.data.length);

            } catch (error) {
                console.error("Error fetching course content:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchContents();
    }, [id]);

    // Hàm gửi thông báo cho người học
    const sendNotification = async (userId) => {
        try {
            await authAPIs().post(endpoints['send-notification'], {
                userId: userId,
                message: `Bạn đã hoàn thành ${countContent} nội dung của khóa học!`
            });
            alert(`Thông báo đã được gửi đến người học ID: ${userId}`);
        } catch (error) {
            console.error("Error sending notification:", error);
        }
    };

    // Hàm con để render một thẻ Enrollment
    const renderEnrollmentCard = (enrollment) => {
        return (
            <div key={enrollment.id} className="enrollment-card">
                <img
                    src={enrollment.userId.avatar}
                    alt={enrollment.fullName}
                    className="avatar"
                />
                <div className="enrollment-details">
                    <h2>{enrollment.fullName}</h2>
                    <p><strong>Email:</strong> {enrollment.userId.email}</p>
                    <p><strong>Phone:</strong> {enrollment.userId.phone}</p>
                    <p><strong>Progress:</strong> {enrollment.progress} / {countContent} nội dung</p>
                    <p><strong>Course:</strong> {enrollment.courseId.title}</p>
                    <p><strong>Status:</strong> {enrollment.courseId.status}</p>

                    {/* Nếu số nội dung đã hoàn thành bằng với tổng số nội dung của khóa học, hiển thị nút thông báo */}
                    {enrollment.progress === countContent && countContent > 0 && (
                        <Button
                            variant="success"
                            onClick={() => sendNotification(enrollment.userId.id)}
                        >
                            Gửi thông báo hoàn thành
                        </Button>
                    )}
                    
                </div>
            </div>
        );
    };

    if (loading) {
        return (
            <div className="spinner-container">
                <Spinner animation="grow" variant="primary" className="spinner-lg" />
            </div>
        );
    }

    return (
        <div>
            <div className="enrollments-list">
                {enrollments.length > 0 ? (
                    enrollments.map((enrollment) => renderEnrollmentCard(enrollment))
                ) : (
                    <Alert variant="warning">KHÔNG có học viên đăng ký!</Alert>
                )}
            </div>
        </div>
    );
};

export default InstructorCourseEnrollment;
