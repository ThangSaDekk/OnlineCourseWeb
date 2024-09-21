import { useContext, useEffect, useState } from "react";
import { Alert, Button, Card, Col, Row, Spinner, Toast, ToastContainer } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { MyUserContext } from "../App";
import { authAPIs, endpoints } from "../config/APIs";
import { FaCog } from 'react-icons/fa';
import '../css/Course.css';
import '../css/Spinner.css';
import '../css/ToastContainer.css';

const DEFAULT_IMAGE = "https://res.cloudinary.com/dh1irfap0/image/upload/v1725264150/NANG-CAP-BAN-THAN_pdmrgk.png";

const InstructorDashboard = () => {
  const [courses, setCourses] = useState([]);
  const [showToast, setShowToast] = useState(false);
  const [toastMessage, setToastMessage] = useState("");
  const [toastBgColor, setToastBgColor] = useState("");
  const [loading, setLoading] = useState(true);

  const user = useContext(MyUserContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user || user.userRole !== 'ROLE_INSTRUCTOR') {
      setToastMessage("Truy cập bị từ chối. Vui lòng đăng nhập.");
      setToastBgColor("bg-danger");
      setShowToast(true);
      setTimeout(() => {
        navigate("/login");
      }, 2000);
      return;
    }

    const loadCourses = async () => {
      try {
        const res = await authAPIs().get(endpoints['instructorCourses']);
        setCourses(res.data);
        setLoading(false);
      } catch (ex) {
        console.error(ex);
        setToastMessage("Không thể tải khóa học. Vui lòng thử lại sau.");
        setToastBgColor("bg-danger");
        setShowToast(true);
        setLoading(false);
      }
    };

    setLoading(true);
    loadCourses();
  }, [user, navigate]);

  const manageCourse = (courseId) => {
    navigate(`/instructor-dashboard/courses/${courseId}`);
  };

  if (loading) {
    return (
      <div className="spinner-container">
        <Spinner animation="grow" variant="primary" className="spinner-lg" />
      </div>
    );
  }

  return (
    <>
      <Row className="p-5">
        {courses.length > 0 ? (
          courses.map(c => {
            const imgSrc = c.img || DEFAULT_IMAGE;
            return (
              <Col className="p-2" key={c.id} md={4} xs={12}>
                <Card className="course-card">
                  <Card.Img
                    variant="top"
                    src={imgSrc}
                    style={{ width: "100%", height: "auto", objectFit: "contain" }} 
                    alt={c.title}
                  />
                  <Card.Body>
                    <Card.Title>{c.title}</Card.Title>
                    <Card.Text className="course-description">{c.description}</Card.Text>
                    <Button variant="primary" onClick={() => manageCourse(c.id)}>
                      Quản lý <FaCog className="icon" />
                    </Button>
                  </Card.Body>
                </Card>
              </Col>
            );
          })
        ) : (
          <Col xs={12}>
            <Alert variant="warning">Hiện bạn không quản lý khóa học nào !!</Alert>;
          </Col>
        )}
      </Row>

      <ToastContainer className="custom-toast position-fixed top-0 start-50 translate-middle-x">
        <Toast
          show={showToast}
          onClose={() => setShowToast(false)}
          delay={2000}
          autohide
          className={toastBgColor}
        >
          <Toast.Body>{toastMessage}</Toast.Body>
        </Toast>
      </ToastContainer>
    </>
  );
};

export default InstructorDashboard;
