package com.group8.controller;

import com.group8.dto.PaymentDTO;
import com.group8.pojo.Invoice;
import com.group8.service.InvoiceService;
import com.group8.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/payments")
@CrossOrigin
public class PaymentController {

    private static final int MOMO_SUCCESS_CODE = 0;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/pay")
    public ResponseEntity<Map<String, String>> pay(@RequestBody PaymentDTO paymentDTO, HttpServletRequest request) {
        try {
            // Gọi dịch vụ thanh toán và trả về phản hồi
            return paymentService.payWithMoMo(request, paymentDTO);
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
    public ResponseEntity<String> returnMomo(HttpServletRequest request) {
        try {
            String resultCode = request.getParameter("resultCode");
            String requestId = request.getParameter("requestId");
            String orderInfo = request.getParameter("orderInfo");
            String transId = request.getParameter("transId");

            if (resultCode == null || !Integer.toString(MOMO_SUCCESS_CODE).equals(resultCode)) {
                return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
            }
            Invoice invoice = this.invoiceService.checkrequestId(requestId);
              System.out.println("CHeck invoice: " + invoice);
            if (invoice == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found transaction");
            }

            if (invoice.getTransactionId() == null) {
                invoice.setTransactionId(transId);
                invoice.setStatus(Boolean.TRUE);
                this.invoiceService.addUpInvoice(invoice);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Internal server error");
            }
            
            System.out.println("CHeck info: " + orderInfo);

            return new ResponseEntity<>("Paid with Momo successfully", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
