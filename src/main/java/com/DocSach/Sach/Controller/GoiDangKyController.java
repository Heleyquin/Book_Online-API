package com.DocSach.Sach.Controller;

import com.DocSach.Sach.DTO.GoiDangKyRequest;
import com.DocSach.Sach.DTO.GoiDangKyResponse;
import com.DocSach.Sach.DTO.ReaderRequest;
import com.DocSach.Sach.DTO.ReaderUpdate;
import com.DocSach.Sach.Responsitory.GoiDangKyResponsi;
import com.DocSach.Sach.Service.GoiDangKyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dang-ki-goi")
@RequiredArgsConstructor
@Slf4j
public class GoiDangKyController {
    private final GoiDangKyService goiDangKyService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getGoi(@PathVariable("id") String id) {
        return goiDangKyService.getGoiDangKyResponse(id);
    }
    @GetMapping
    public ResponseEntity<?> getAllGoi() {
        return goiDangKyService.getAllGoiDangKy();
    }
    @PostMapping
    public ResponseEntity<?> addGoi(@RequestBody GoiDangKyRequest goiDangKyRequest) {
        return goiDangKyService.addGoiDangKy(goiDangKyRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGoi(@PathVariable("id") String id) {
        return goiDangKyService.deleteGoi(id);
    }
    @PutMapping
    public ResponseEntity<?> updateGoi(@RequestBody GoiDangKyResponse goiDangKyResponse) {
        return goiDangKyService.updateGoiDangKy(goiDangKyResponse);
    }
}
