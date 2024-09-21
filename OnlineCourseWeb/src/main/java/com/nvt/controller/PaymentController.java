package com.nvt.controller;

import com.nvt.config.MyWebSocketHandler;
import com.nvt.dto.PaymentDTO;
import com.nvt.pojo.Enrollment;
import com.nvt.pojo.Enum.EnrollmentStatus;
import com.nvt.pojo.Enum.PaymentStatus;
import com.nvt.pojo.Invoice;
import com.nvt.pojo.User;
import com.nvt.service.CourseService;
import com.nvt.service.EmailService;
import com.nvt.service.EnrollmentService;
import com.nvt.service.InvoiceService;
import com.nvt.service.PaymentService;
import com.nvt.service.UserService;
import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/payments")
@CrossOrigin
public class PaymentController {

    private static final int MOMO_SUCCESS_CODE = 0;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/pay")
    public ResponseEntity<Map<String, String>> pay(@RequestBody PaymentDTO paymentDTO, HttpServletRequest request) {
        try {
            // Gọi dịch vụ thanh toán và trả về phản hồi
            return this.paymentService.payWithMoMo(request, paymentDTO);
        } catch (Exception e) {
            // Xử lý lỗi và trả về thông báo lỗi
            Map<String, String> errorResponse = Map.of("error", "Error processing payment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/ipn-momo")
    public ResponseEntity<String> ipnMomo(@RequestBody Map<String, String> requestData) {
        try {
            if (!requestData.containsKey("resultCode") || !Integer.toString(MOMO_SUCCESS_CODE).equals(requestData.get("resultCode"))) {
                return new ResponseEntity<>("Transaction is not success", HttpStatus.BAD_REQUEST);
            }

            Invoice invoice = this.invoiceService.checkrequestId(requestData.get("requestId"));
            System.out.println("CHeck invoice: " + invoice);
            if (invoice != null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Redirected data is invalid", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/momo")
    @Transactional
    public ResponseEntity<String> returnMomo(HttpServletRequest request) {
        String requestId = request.getParameter("requestId");
        Invoice invoice = this.invoiceService.checkrequestId(requestId);
        try {
            String resultCode = request.getParameter("resultCode");
            String orderInfo = request.getParameter("orderInfo");
            String transId = request.getParameter("transId");
            String extraData = request.getParameter("extraData");

            System.out.println("Check extraData: " + extraData);

            if (invoice == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found transaction");
            }

            if (resultCode == null || !Integer.toString(MOMO_SUCCESS_CODE).equals(resultCode)) {
                invoice.setStatus(PaymentStatus.CANCELED);
                return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
            }
            System.out.println("CHeck invoice: " + invoice);

            if (invoice.getTransactionId() == null) {
                invoice.setTransactionId(transId);
                invoice.setStatus(PaymentStatus.SUCCESS);
                this.invoiceService.addUpInvoice(invoice);

                System.out.println("CHeck info: " + orderInfo);
                String[] stringArray = extraData.split(",");
                int userId = Integer.parseInt(stringArray[0]);

                User u = this.userService.getUserByID(userId);
                System.out.println(u);

                // Duyệt qua từng giá trị trong mảng 
                for (int i = 1; i < stringArray.length; i++) {
                    Enrollment enrollment = new Enrollment();

                    enrollment.setUserId(u);
                    enrollment.setCourseId(this.courseService.getCourseByID(Integer.parseInt(stringArray[i])));
                    enrollment.setInvoiceId(invoice);

                    enrollment.setCreatedDate(new Date());
                    enrollment.setUpdatedDate(new Date());
                    enrollment.setStatus(EnrollmentStatus.IN_PROGRESS);
                    enrollment.setProgress(0);
                    this.enrollmentService.addOrUpEnrollment(enrollment);
                }
             
                this.emailService.sendEnrollmentSuccessMessage(this.userService.getUserByID(userId), request, invoice.getPayerEmail());

            } else {
                invoice.setStatus(PaymentStatus.CANCELED);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Internal server error");
            }

            return new ResponseEntity<>("Paid with Momo successfully", HttpStatus.OK);

        } catch (NumberFormatException e) {
            invoice.setStatus(PaymentStatus.CANCELED);
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
