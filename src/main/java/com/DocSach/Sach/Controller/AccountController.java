package com.DocSach.Sach.Controller;

import com.DocSach.Sach.DTO.AccountRequest;
import com.DocSach.Sach.DTO.AccountResponse;
import com.DocSach.Sach.Entity.Account;
import com.DocSach.Sach.Service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountRequest accountRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(accountRequest.getTk(), accountRequest.getMk())
            );

            if (authentication.isAuthenticated()) {
                return accountService.generateToken(accountRequest.getTk());
            } else {
                return new ResponseEntity<>("Tên đăng nhập hoặc mật khẩu không đúng", HttpStatus.UNAUTHORIZED);
            }
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Tên đăng nhập hoặc mật khẩu không đúng", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Đã xảy ra lỗi", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/validate")
    public String validate(@RequestParam("token") String token) {
        accountService.validateToken(token);
        return "Token is valid";
    }
    @GetMapping("/extract")
    public ResponseEntity<?>username(@RequestParam("token") String token) {
        return ResponseEntity.ok(accountService.extractUsername(token));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable("id") Long id) {
        Object acc = accountService.getAccount(id);
        if (acc!= null){
            return ResponseEntity.ok(acc);
        }
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Không có quyền xem");
    }
    @GetMapping
    public ResponseEntity<?> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getAccountByUsername(@PathVariable("username") String username) {
        Object acc = accountService.getAccountByUsername(username);
        if (acc!= null){
            return ResponseEntity.ok(acc);
        }
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Không có quyền xem");
    }
    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.addAccount(accountRequest);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.registerAccount(accountRequest);
    }
    @DeleteMapping("/{tk}")
    public ResponseEntity<?> deleteAccount(@PathVariable("tk") String tk) {
        return accountService.deleteAccount(tk);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateAccount(@RequestBody AccountResponse accountResponse) {
        return accountService.updateAccount(accountResponse);
    }
    @PutMapping("/update-pass")
    public ResponseEntity<?> updatePass(@RequestBody AccountResponse accountResponse) {
        return accountService.updatePass(accountResponse);
    }
    // Theo lượt đọc (Noi bat), Moi ra mat, tim chua - k phan biet kieu chu - đinh dang - k phan biet dau, Danh mục, Excel.
}
