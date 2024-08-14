package com.DocSach.Sach.Controller;

import com.DocSach.Sach.DTO.TheLoaiRequest;
import com.DocSach.Sach.DTO.TheLoaiResponse;
import com.DocSach.Sach.Entity.TheLoai;
import com.DocSach.Sach.Service.TheLoaiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theLoai")
@RequiredArgsConstructor
@Slf4j
public class TheLoaiController {
    private final TheLoaiService theLoaiService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getTheLoai(@PathVariable("id") Long id) {
        return theLoaiService.getTheLoai(id);
    }
    @GetMapping
    public ResponseEntity<?> getAllTheLoai() {
        return theLoaiService.getAllTheLoai();
    }
    @PostMapping
    public ResponseEntity<?> addTheLoai(@RequestBody TheLoaiRequest theLoaiRequest) {
        return theLoaiService.addTheLoai(theLoaiRequest);
    }
    @PutMapping
    public ResponseEntity<?> updateTheLoai(@RequestBody TheLoaiResponse theLoaiResponse) {
        return theLoaiService.updateTheLoai(theLoaiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheLoai(@PathVariable("id") Long id) {
        return theLoaiService.deleteTheLoai(id);
    }
}
