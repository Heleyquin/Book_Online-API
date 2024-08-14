package com.DocSach.Sach.Controller;

import com.DocSach.Sach.DTO.ReaderRequest;
import com.DocSach.Sach.DTO.ReaderResponse;
import com.DocSach.Sach.DTO.ReaderUpdate;
import com.DocSach.Sach.Entity.LuotDocDocGia;
import com.DocSach.Sach.Entity.Reader;
import com.DocSach.Sach.Service.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
@RequiredArgsConstructor
@Slf4j
public class ReaderController {
    private final ReaderService readerService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getReader(@PathVariable("id") Long id) {
        return readerService.getNguoiDung(id);
    }
    @GetMapping
    public ResponseEntity<?> getAllReader() {
        return readerService.getAllNguoiDung();
    }
    @PostMapping
    public ResponseEntity<?> addReader(@RequestBody ReaderRequest readerRequest) {
        return readerService.addNguoiDung(readerRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReader(@PathVariable("id") Long id) {
        return readerService.deleteNguoiDung(id);
    }
    @PutMapping
    public ResponseEntity<?> updateReader(@RequestBody ReaderUpdate readerResponse) {
        return readerService.updateNguoiDung(readerResponse);
    }
    @GetMapping("username/{username}")
    public ResponseEntity<?> getReaderByUsername(@PathVariable("username") String username) {
        return readerService.getReaderByUsername(username);
    }
    @GetMapping("/recent-created")
    public List<Reader> getRecentCreated() {
        return readerService.getReaderRencent();
    }
    @GetMapping("/luot-doc")
    public List<LuotDocDocGia> getLuotDocGia() {
        return readerService.getLuotDocDocGia();
    }
}
