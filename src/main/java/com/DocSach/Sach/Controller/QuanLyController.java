package com.DocSach.Sach.Controller;

import com.DocSach.Sach.DTO.QuanLyRequest;
import com.DocSach.Sach.DTO.QuanLyResponse;
import com.DocSach.Sach.DTO.QuanLyUpdate;
import com.DocSach.Sach.Service.QuanLyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
@Slf4j
public class QuanLyController {
    private final QuanLyService quanLyService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdmin(@PathVariable("id") Long id) {
        return quanLyService.getQuanLy(id);
    }
    @GetMapping
    public ResponseEntity<?> getAllAdmin() {
        return quanLyService.getAllQuanLy();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addAdmin(@RequestBody QuanLyRequest quanLyRequest) {
        return quanLyService.addQuanLy(quanLyRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id) {
        return quanLyService.deleteQuanLy(id);
    }
    @PutMapping
    public ResponseEntity<?> updateAdmin(@RequestBody QuanLyUpdate quanLyResponse) {
        return quanLyService.updateQuanLy(quanLyResponse);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getAdminByUsername(@PathVariable("username") String username) {
        return quanLyService.getQuanLyByUsername(username);
    }
}
