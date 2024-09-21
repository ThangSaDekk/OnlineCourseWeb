import React, { useState, useEffect } from 'react';
import { Container, Card, Form, Button, Spinner } from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';
import { authAPIs, endpoints } from '../config/APIs';
import '../css/TestCourse.css';
import '../css/Spinner.css';


const TestCourse = () => {
    const { id } = useParams(); // Lấy id từ URL
    const [course, setCourse] = useState(null); // Biến để lưu thông tin khóa học
    const [testCode, setTestCode] = useState(''); // Biến để lưu mã bài kiểm tra
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCourse = async () => {
            try {
                setLoading(true);
                const response = await authAPIs().get(endpoints['course-details'](id));
                setCourse(response.data); // Lưu thông tin khóa học vào state
            } catch (err) {

            }
            finally{
                setLoading(false);
            }
        };

        fetchCourse();
    }, [id]);

    const handleTestSubmit = () => {
        if (testCode === '') {
            alert('Vui lòng nhập mã bài kiểm tra!');
            return;
        }

        // Giả lập xác nhận mã bài kiểm tra (có thể thay thế bằng API thật nếu cần)
        if (testCode === 'VALID_CODE') { // Thay 'VALID_CODE' bằng mã kiểm tra thực tế
            alert('Mã hợp lệ. Điều hướng tới bài kiểm tra...');
        } else {
            alert('Mã bài kiểm tra không hợp lệ!');
        }
    };

    
  if (loading) {
    return (
      <div className="spinner-container">
        <Spinner animation="grow" variant="primary" className="spinner-lg" />
      </div>
    );
  }


    return (
        <Container>
            <Card className="my-4">
                <Card.Body className="bg-info text-white text-center">
                    <h3>Bài kiểm tra kiến thức kết thúc khóa học</h3>
                </Card.Body>
            </Card>

            <Card className="mb-4">
                <Card.Body>
                    <p><strong>Thực hiện bài kiểm tra kết thúc khóa học {course.title}</strong></p>
                    <p><strong>Nội dung bài kiểm tra bao gồm:</strong></p>
                    <ul>
                        <li>Bài test trong vòng 45 phút</li>
                        <li>Phần vấn đáp với người hướng dẫn trong 15 phút</li>
                    </ul>
                </Card.Body>
            </Card>

            <Card className="mb-4">
                <Card.Body>
                    <p><strong>Lưu ý:</strong></p>
                    <ul>
                        <li>Hãy liên hệ với người hướng dẫn để nhận được mật khẩu</li>
                        <li>Hãy chắc chắn rằng bạn thực hiện bài kiểm tra dưới sự giám sát của người hướng dẫn</li>
                        <li>Hãy chắc chắn rằng camera của bạn liên tục hoạt động</li>
                        <li>Mọi hành vi gian lận sẽ bị đánh giá "Không hoàn thành"</li>
                        <li>Hãy có thái độ tôn trọng với người hướng dẫn trong phần giao tiếp</li>
                    </ul>
                </Card.Body>
            </Card>

            <Form>
                <Form.Group className="mb-3 bg-info" controlId="testCode" style={{padding: '2%', borderRadius: '30px', width:'50%', marginLeft:'25%'}}>
                <Form.Label className="test-course-form-label">Nhập mã bài kiểm tra:</Form.Label>
                    <Form.Control
                        type="password"
                        value={testCode}
                        onChange={(e) => setTestCode(e.target.value)}
                        placeholder="Nhập mã bài kiểm tra"
                    />
                    <Button variant="success" onClick={handleTestSubmit} className="mt-3" >
                        Xác nhận
                    </Button>
                </Form.Group>

            </Form>
        </Container>
    );
};

export default TestCourse;
