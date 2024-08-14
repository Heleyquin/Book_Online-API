package com.DocSach.Sach.Service;


import com.DocSach.Sach.DTO.AccountRequest;
import com.DocSach.Sach.DTO.AccountResponse;
import com.DocSach.Sach.Entity.Account;
import com.DocSach.Sach.Responsitory.AccountResponsi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final PasswordEncoder passwordEncoder;
    private final AccountResponsi accountResponsi;
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

    public AccountResponse getAccount(long id){
        Account account = accountResponsi.getReferenceById(id);
        return AccountResponse.builder()
                .tk(account.getUsername())
                .mk(account.getPassword())
                .role(account.getRole())
                .active(account.isActive())
                .build();
    }

    public List<AccountResponse> getAccounts(){
        List<Account> accounts = accountResponsi.findAll();
        return accounts.stream().map(this::mapToAccountResponse).toList();
    }

    //account Sang AccountResponse
    private AccountResponse mapToAccountResponse(Account account){
        return AccountResponse.builder()
                .tk(account.getUsername())
                .mk(account.getPassword())
                .active(account.isActive())
                .role(account.getRole())
                .build();
    }

    // Thêm admin
    public ResponseEntity<?> addAccount(AccountRequest accountRequest){
        if(accountResponsi.existsByUsername(accountRequest.getTk())){
            return new ResponseEntity<>("Username đã có rồi!!!!!", HttpStatus.BAD_REQUEST);
        }
        Account account = Account.builder()
                .username(accountRequest.getTk())
                .password(passwordEncoder.encode(accountRequest.getMk()))
                .role(0)
                .active(true)
                .build();
        accountResponsi.save(account);
        log.info("{} đã tạo", account.getUsername());
        return new ResponseEntity<>("Thêm tài khoản thành công!!!!!", HttpStatus.CREATED);
    }

    //Thêm reader
    public ResponseEntity<?> registerAccount(AccountRequest accountRequest){
        if(accountResponsi.existsByUsername(accountRequest.getTk())){
            return new ResponseEntity<>("Username đã có rồi!!!!!", HttpStatus.BAD_REQUEST);
        }
        Account account = Account.builder()
                .username(accountRequest.getTk())
                .password(passwordEncoder.encode(accountRequest.getMk()))
                .role(1)
                .active(true)
                .build();
        accountResponsi.save(account);
        log.info("{} đã tạo", account.getUsername());
        return new ResponseEntity<>("Tạo tài khoản thành công!!!!!", HttpStatus.CREATED);
    }

    public AccountResponse getAccountByUsername(String username){
        Account account = accountResponsi.findByUsername(username);
        return AccountResponse.builder()
                .tk(account.getUsername())
                .mk(account.getPassword())
                .role(account.getRole())
                .active(account.isActive())
                .build();
    }

    public ResponseEntity<?> deleteAccount(String id){
        Account account = accountResponsi.findByUsername(id);
        String accountUsername = account.getUsername();
        accountResponsi.delete(account);
        log.info("{} đã xoá", accountUsername);
        return ResponseEntity.ok("Xóa thành công");
    }

    public ResponseEntity<?> updatePass(AccountResponse accountResponse){
        Account account = Account.builder()
                .username(accountResponse.getTk())
                .password(passwordEncoder.encode(accountResponse.getMk()))
                .role(accountResponse.getRole())
                .active(accountResponse.isActive())
                .build();
        try {
            accountResponsi.getReferenceByUsername(accountResponse.getTk());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tài khoản không tồn tại");
        }
        accountResponsi.save(account);
        log.info("{} đã cập nhật", account.getUsername());
        return ResponseEntity.ok("Cập nhật thành công");

    }

    public ResponseEntity<?> updateAccount(AccountResponse accountResponse){
        Account account = Account.builder()
                .username(accountResponse.getTk())
                .password(accountResponse.getMk())
                .role(accountResponse.getRole())
                .active(accountResponse.isActive())
                .build();
        try {
            accountResponsi.getReferenceByUsername(accountResponse.getTk());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tài khoản không tồn tại");
        }
        accountResponsi.save(account);
        log.info("{} đã cập nhật", account.getUsername());
        return ResponseEntity.ok("Cập nhật thành công");
    }

}
