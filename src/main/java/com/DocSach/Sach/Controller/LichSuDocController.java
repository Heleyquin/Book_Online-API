package com.DocSach.Sach.Controller;

import com.DocSach.Sach.DTO.LichSuDocRequest;
import com.DocSach.Sach.DTO.LichSuDocResponse;
import com.DocSach.Sach.Entity.LichSuDoc;
import com.DocSach.Sach.Service.LichSuDocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lichSuDoc")
@RequiredArgsConstructor
@Slf4j
public class LichSuDocController {
    private final LichSuDocService lichSuDocService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getLichSuDocById(@PathVariable("id") Long id) {
        return lichSuDocService.getLichSuDoc(id);
    }
    @GetMapping
    public ResponseEntity<?> getAllLichSuDoc() {
        return lichSuDocService.getAllLichSuDoc();
    }
    @PostMapping
    public ResponseEntity<?> addLichSuDoc(@RequestBody LichSuDocRequest lichSuDocRequest) {
        return lichSuDocService.addLichSuDoc(lichSuDocRequest);
    }
    @PutMapping
    public ResponseEntity<?> updateLichSuDoc(@RequestBody LichSuDocResponse lichSuDocResponse) {
        return lichSuDocService.updateLichSuDoc(lichSuDocResponse);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteLichSuDoc(@PathVariable("id") Long id) {
        return lichSuDocService.deleteLichSuDoc(id);
    }
}
