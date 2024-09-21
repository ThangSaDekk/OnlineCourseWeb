import React from 'react';
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom'; // Import useNavigate from react-router-dom
import '../css/Failure.css'; // Import custom CSS file for additional styles

const Failure = () => {
    console.log('Failure component rerendered'); // Log when component rerenders

    const navigate = useNavigate(); // Initialize the navigate hook

    const handleNavigateHome = () => {
        navigate('/'); // Use navigate instead of href to go to the home page
    };

    return (
        <Container fluid className="failure-container">
            <Row className="justify-content-center align-items-center h-100">
                <Col md={20} lg={15}>
                    <Card className="text-center failure-card">
                        <Card.Body>
                            <h1 className="text-danger">Thanh toán thất bại!</h1>
                            <p className="lead">Rất tiếc, giao dịch của bạn không thành công. Vui lòng thử lại hoặc liên hệ với bộ phận hỗ trợ của chúng tôi.</p>
                            <Button variant="danger" onClick={handleNavigateHome} className="mt-3">Trở về trang chủ</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};

export default Failure;
