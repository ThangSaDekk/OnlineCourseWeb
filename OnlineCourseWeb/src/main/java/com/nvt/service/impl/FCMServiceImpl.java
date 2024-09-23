package com.nvt.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.auth.oauth2.AccessToken;
import com.nvt.service.FCMService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@Service
public class FCMServiceImpl implements FCMService {

    @Override
    public String getAccessToken() {
        try {
            // Sử dụng ClassLoader để đọc file từ resources
            InputStream serviceAccountStream = getClass().getClassLoader().getResourceAsStream("coursewebonline-f529a-fda46ccec94d.json");

            // Kiểm tra nếu không tìm thấy file
            if (serviceAccountStream == null) {
                throw new IOException("File không tồn tại trong thư mục resources");
            }

            // Lấy GoogleCredentials từ file service account
            GoogleCredentials credentials = ServiceAccountCredentials.fromStream(serviceAccountStream)
                    .createScoped(Collections.singleton("https://www.googleapis.com/auth/firebase.messaging"));

            // Làm mới token để lấy access token mới nhất
            credentials.refreshIfExpired();
            AccessToken token = credentials.getAccessToken();

            // Trả về access token dưới dạng chuỗi
            return token.getTokenValue();
        } catch (IOException e) {
            return null;
        }
    }
}
