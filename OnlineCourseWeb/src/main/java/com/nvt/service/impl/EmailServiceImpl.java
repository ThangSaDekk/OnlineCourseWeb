/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.service.impl;

import com.nvt.pojo.User;
import com.nvt.service.EmailService;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author thang
 */
@Service
@PropertySource("classpath:configs.properties")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @Override
    public void sendEnrollmentSuccessMessage(User u, HttpServletRequest request, String toMail) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            String orderInfo = request.getParameter("orderInfo");
            String transId = request.getParameter("transId");
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // Đặt địa chỉ email người gửi
            helper.setFrom(environment.getProperty("spring.mail.username"));

            // Đặt email người nhận
            helper.setTo(toMail);

            // Tiêu đề email
            helper.setSubject("Thông báo quan trọng từ Online Course Website");

            // Nội dung email với định dạng HTML
            String content = "<div style='font-family: Arial, sans-serif; color: #333;'>"
                    + "<h1 style='color: #05834E;'>Kính gửi " + u.getFullName() + ",</h1>"
                    + "<p>Chúng tôi xin thông báo rằng bạn đã đăng ký và thanh toán thành công cho khóa học <strong>" + orderInfo + "</strong>.</p>"
                    + "<p>Bạn có thể kiểm tra thông tin giao dịch của mình bằng mã giao dịch: "
                    + "<strong style='color: #d9534f;'>" + transId + "</strong> thông qua MoMo.</p>"
                    + "<p>Cảm ơn bạn đã tin tưởng và lựa chọn hệ thống của chúng tôi.</p>"
                    + "<br>"
                    + "<p>Trân trọng,</p>"
                    + "<p><strong style='color: #05834E;'>Đội ngũ hỗ trợ Online Course Website</strong></p>"
                    + "<hr style='border: 1px solid #05834E;'>"
                    + "<footer style='font-size: 12px; color: #777;'>"
                    + "<p>Nếu bạn có bất kỳ câu hỏi nào, vui lòng liên hệ với chúng tôi qua email: onlinecouse@hotro.vn</p>"
                    + "</footer>"
                    + "</div>";

            // Thiết lập nội dung là HTML
            helper.setText(content, true); // 'true' để kích hoạt HTML

            // Gửi email
            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace(); // Xử lý lỗi nếu không thể gửi email
        }
    }

}
