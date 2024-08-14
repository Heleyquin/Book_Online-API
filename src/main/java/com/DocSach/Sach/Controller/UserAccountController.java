package com.DocSach.Sach.Controller;

import com.DocSach.Sach.DTO.UserAccountRequest;
import com.DocSach.Sach.Service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-account")
@RequiredArgsConstructor
@Slf4j
public class UserAccountController {
    private final UserAccountService userAccountService;
    @PostMapping()
    public ResponseEntity<?> addUserAccount(@RequestBody UserAccountRequest userAccountRequest) {
        return userAccountService.addAccUser(userAccountRequest);
    }
}
