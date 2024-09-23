import React from 'react';
import { Container, Button } from 'react-bootstrap';
import cookie from "react-cookies"; // Đừng quên import cookie
import { authFCMTOkens } from '../config/APIs'; // Import API

const About = () => {
  const postNotifications = async () => {
    try {
      const requestBody = {
        message: {
          token: cookie.load("FCM-token"),
          notification: {
            title: "Online Course Web",
            body: "Chào mừng bạn đến với trang Về chúng tôi"
          },
          webpush: {
            fcm_options: {
              link: "https://www.youtube.com/watch?v=IK8x7qc9ZsA"
            }
          }
        }
      };

      const res = await authFCMTOkens().post('', requestBody); // Gửi request với request body
      console.log(res.data);
    } catch (error) {
      console.error('Error sending notification:', error);
    }
  }

  return (
    <Container>
      <h1>Giới Thiệu</h1>
      <p>Thông tin về tổ chức, sứ mệnh và các giá trị của chúng tôi.</p>
      <Button variant="primary" onClick={postNotifications}>
        Gửi Thông Báo
      </Button>
    </Container>
  );
};

export default About;
