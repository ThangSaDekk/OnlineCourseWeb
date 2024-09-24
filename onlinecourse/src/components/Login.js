import { useContext, useState } from "react";
import { Button, Form, Container, Row, Col, Spinner, Alert } from "react-bootstrap";
import cookie from "react-cookies";
import { Navigate, Link, useNavigate } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../App";
import APIs, { authAPIs, endpoints } from "../config/APIs";
import "../css/Login.css"; // Đảm bảo tạo và thêm file CSS này
import '../css/Spinner.css';

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [rememberMe, setRememberMe] = useState(false); // Checkbox nhớ mật khẩu
    const [loading, setLoading] = useState(false); // State quản lý trạng thái tải
    const [errorMessage, setErrorMessage] = useState(""); // State quản lý thông báo lỗi
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);
    const navigate = useNavigate();

    const saveFCMToken =  async(u) =>{
        try{
            await APIs.post(endpoints['save-fcm-token'],{
                "userId": u,
                "fcmtoken": cookie.load("FCM-token")
            })

        }catch (ex) {
            console.error(ex);
            setErrorMessage("Đã xảy ra lỗi khi xử lý FCMToken. Vui lòng thử lại sau.");
        } 
    }


    const login = async (e) => {
        e.preventDefault();
        setLoading(true); // Bắt đầu trạng thái tải
        setErrorMessage(""); // Reset thông báo lỗi

        try {
            let res = await APIs.post(endpoints['login'], {
                "username": username,
                "password": password
            });

            cookie.save("access-token", res.data, { path: "/", maxAge: rememberMe ? 604800 : null }); // maxAge 7 ngày nếu nhớ mật khẩu

            let user = await authAPIs().get(endpoints['current-user']);
            
            if (user.data.active) {
                cookie.save("user", user.data, { path: "/" });
                dispatch({
                    "type": "login",
                    "payload": user.data
                });
                if (user.data.userRole === "ROLE_INSTRUCTOR") {
                    navigate("/instructor-dashboard");
                } else if (user.data.userRole === "ROLE_STUDENT") {
                    navigate("/");
                }
                saveFCMToken(user.data.id);
            } else {
                dispatch({ type: "logout" });
                setErrorMessage("Tài khoản này đã bị khóa. Liên hệ đến onlinecourse@hotro.vn để được hỗ trợ");
            }
        } catch (ex) {
            console.error(ex);
            setErrorMessage("Đã xảy ra lỗi. Vui lòng thử lại sau.");
        } finally {
            setLoading(false); // Kết thúc trạng thái tải
        }
    }

    if (user !== null) {
        return <Navigate to="/" />;
    }

    if (loading) {
        return (
            <div className="spinner-container">
                <Spinner animation="grow" variant="primary" className="spinner-lg" />
            </div>
        );
    }

    return (
        <div className="login-wrapper">
            <Container className="login-container">
                <Row className="justify-content-center">
                    <h1 className="text-center text-dark mb-4">ĐĂNG NHẬP</h1>
                    <Col md={6} className="login-form">
                        {errorMessage && (
                            <Alert variant="danger">
                                {errorMessage}
                            </Alert>
                        )}
                        <Form method="post" onSubmit={login}>
                            <Form.Group className="mb-3" controlId="formBasicUsername">
                                <Form.Label className="text-light">Tên đăng nhập</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Nhập tên đăng nhập..."
                                    value={username}
                                    onChange={e => setUsername(e.target.value)}
                                />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicPassword">
                                <Form.Label className="text-light">Mật khẩu</Form.Label>
                                <Form.Control
                                    type="password"
                                    placeholder="Nhập mật khẩu..."
                                    value={password}
                                    onChange={e => setPassword(e.target.value)}
                                />
                            </Form.Group>

                            {/* Checkbox nhớ mật khẩu */}
                            <Form.Group className="mb-3" controlId="formBasicCheckbox">
                                <Form.Check
                                    type="checkbox"
                                    label="Nhớ mật khẩu"
                                    checked={rememberMe}
                                    onChange={e => setRememberMe(e.target.checked)}
                                    className="text-light"
                                />
                            </Form.Group>

                            <Form.Group className="text-center">
                                <Button type="submit" variant="primary" className="btn-block">Đăng nhập</Button>
                            </Form.Group>

                            {/* Nút Đăng ký */}
                            <Form.Group className="text-center mt-3">
                                <Link to="/register" className="text-light">Chưa có tài khoản? Đăng ký</Link>
                            </Form.Group>
                        </Form>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default Login;
