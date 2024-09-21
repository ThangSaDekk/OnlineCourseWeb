import React, { useEffect, useRef, useState } from 'react';
import '../css/Certificate.css';
import '../css/Spinner.css';
import { Button, Card, Spinner } from 'react-bootstrap';
import html2canvas from 'html2canvas';
import { useParams } from 'react-router';
import { authAPIs, endpoints } from '../config/APIs';

const Certificate = () => {
    const certificateRef = useRef();
    const { id } = useParams(); // Lấy id enrollment từ URL
    const [loading, setLoading] = useState(true);
    const [fullName, setFullName] = useState("");
    const [title, setTitle] = useState("");
    const [grade, setGrade] = useState("");
    const [status, setStatus] = useState("");

    useEffect(() => {
        const fetchEnrollment = async () => {
            try {
                setLoading(true);
                const response = await authAPIs().get(endpoints['enrollment'](id));
                setFullName(response.data.userId.lastName + " " + response.data.userId.firstName);
                setTitle(response.data.courseId.title);  
                setGrade(response.data.grade);
                setStatus(response.data.status);
            } catch (error) {
                console.error('Error fetching enrollment data:', error);
            }finally{
                setLoading(false);
            }
        };

        fetchEnrollment();
    }, [id]);

    const clickPrint = () => {
        if (status !== "COMPLETED") {
            alert('Chứng chỉ chưa hoàn thành. Không thể in.');
            return;
        };

        html2canvas(certificateRef.current, {
            backgroundColor: '#ffffff',
            useCORS: true,
            logging: true
        }).then(canvas => {
            const imgData = canvas.toDataURL('image/png');

            // Tạo một link element để tải file
            const link = document.createElement('a');
            link.href = imgData;
            link.download = 'certificate.png';
            link.click();
        }).catch(error => {
            console.error('Error generating PNG:', error);
        });
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
            <Card className="mb-4">
                <Card.Body className="bg-info text-white text-center">
                    <h3 className="my-2">NHẬN LẤY THÀNH QUẢ CỦA BẢN THÂN</h3>
                </Card.Body>
            </Card>

            <div className='certificateContainer' ref={certificateRef}>
                <img src='https://res.cloudinary.com/dh1irfap0/image/upload/v1725455598/Untitled_design_jidaet.png' height={'auto'} width={'80%'} alt="Certificate Background" />
                <div className='contentCertificate'>
                    <h1>{fullName}</h1>
                </div>
                <div className='contentCertificate'>
                    <h2>{title}</h2>
                </div>
                <div className='contentCertificate'>
                    <h3>{grade}</h3>
                </div>
            </div>

            <Button className='buttonPrint' onClick={clickPrint}>
                Tải chứng chỉ
            </Button>
        </>
    );
};

export default Certificate;
