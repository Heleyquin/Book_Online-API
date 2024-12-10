package com.DocSach.Sach.Controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
@Slf4j
public class ServerController {
    @GetMapping("/server-url")
    public Map<String, String> getServerInfo(HttpServletRequest request) {
        String serverIp = request.getLocalAddr(); // Lấy địa chỉ IP của server
        int serverPort = request.getLocalPort(); // Lấy port của server
        String baseUrl = "http://" + serverIp + ":" + serverPort; // Xây dựng URL cơ sở

        Map<String, String> response = new HashMap<>();
        response.put("baseUrl", baseUrl);
        return response;
    }
}
