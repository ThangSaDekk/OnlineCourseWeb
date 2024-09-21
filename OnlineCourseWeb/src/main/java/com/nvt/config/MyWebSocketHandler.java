package com.nvt.config;

import com.nvt.pojo.Invoice;
import com.nvt.service.InvoiceService;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private final InvoiceService invoiceService;

    @Autowired
    public MyWebSocketHandler(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);
        String requestId = payload;
        Invoice invoice = invoiceService.checkrequestId(requestId);
        switch (invoice.getStatus()) {
            case CANCELED:
                session.sendMessage(new TextMessage("Transaction Canceled"));
                break;
            case SUCCESS:
                session.sendMessage(new TextMessage("Transaction Success"));
                break;
            case PENDING:
                session.sendMessage(new TextMessage("Transaction Pending"));
                break;
            default:
                session.sendMessage(new TextMessage("Transaction Pending"));
                break;
        }
    }
}
