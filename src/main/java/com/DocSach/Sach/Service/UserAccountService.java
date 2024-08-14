package com.DocSach.Sach.Service;

import com.DocSach.Sach.DTO.UserAccountRequest;
import com.DocSach.Sach.Entity.Account;
import com.DocSach.Sach.Entity.QuanLy;
import com.DocSach.Sach.Entity.Reader;
import com.DocSach.Sach.Responsitory.AccountResponsi;
import com.DocSach.Sach.Responsitory.QuanLyResponsi;
import com.DocSach.Sach.Responsitory.ReaderResponsive;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAccountService {
    private final PasswordEncoder passwordEncoder;
    private final AccountResponsi accountResponsi;
    private final QuanLyResponsi quanLyResponsi;
    private final ReaderResponsive readerResponsive;
    private final JWTService jwtService;

    public ResponseEntity<?> generateToken(String tk){
        return new ResponseEntity<>(jwtService.generateToken(tk), HttpStatus.OK);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }

    public String extractUsername(String token){
        return jwtService.extractUsername(token);
    }

    @Transactional
    public ResponseEntity<?> addAccUser(UserAccountRequest userAccountRequest){
        if(accountResponsi.existsByUsername(userAccountRequest.getTk())){
            return new ResponseEntity<>("Username đã có rồi!!!!!", HttpStatus.BAD_REQUEST);
        }
        Account account = Account.builder()
                .username(userAccountRequest.getTk())
                .password(passwordEncoder.encode("1"))
                .role(userAccountRequest.getRole())
                .active(true)
                .build();
        accountResponsi.save(account);
        log.info("{} đã tạo", account.getUsername());
        if(userAccountRequest.getRole() == 1){
            Reader nguoiDung = Reader.builder()
                    .ten(userAccountRequest.getTen())
                    .ho(userAccountRequest.getHo())
                    .gioiTinh(userAccountRequest.isGioiTinh())
                    .email(userAccountRequest.getEmail())
                    .account(accountResponsi.getReferenceByUsername(userAccountRequest.getTk()))
                    .build();
            readerResponsive.save(nguoiDung);
        } else if (userAccountRequest.getRole() == 0) {
            QuanLy quanLy = QuanLy.builder()
                    .ten(userAccountRequest.getTen())
                    .ho(userAccountRequest.getHo())
                    .gioiTinh(userAccountRequest.isGioiTinh())
                    .email(userAccountRequest.getEmail())
                    .account(accountResponsi.getReferenceByUsername(userAccountRequest.getTk()))
                    .build();
            quanLyResponsi.save(quanLy);
        }
        return new ResponseEntity<>("Thêm xong", HttpStatus.OK);
    }
}
