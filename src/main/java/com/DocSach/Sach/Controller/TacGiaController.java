package com.DocSach.Sach.Controller;

import com.DocSach.Sach.DTO.TacGiaRequest;
import com.DocSach.Sach.DTO.TacGiaResponse;
import com.DocSach.Sach.Entity.TacGia;
import com.DocSach.Sach.Service.TacGiaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tacGia")
@RequiredArgsConstructor
@Slf4j
public class TacGiaController {
    private final TacGiaService tacGiaService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return tacGiaService.getTacGiaResponse(id);
    }
    @GetMapping
    public ResponseEntity<?> getAllTacGia() {
        return tacGiaService.getAllTacGia();
    }
    @PostMapping
    public ResponseEntity<?> addTacGia(@RequestBody TacGiaRequest tacGiaRequest) {
        return  tacGiaService.addTacGia(tacGiaRequest);
    }
    @PutMapping
    public ResponseEntity<?> updateTacGia(@RequestBody TacGiaResponse tacGiaResponse) {
        return tacGiaService.updateTacGia(tacGiaResponse);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteTacGia(@PathVariable("id") Long id) {
        return tacGiaService.deleteTacGia(id);
    }
}
