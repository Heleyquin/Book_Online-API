package com.DocSach.Sach.Controller;

import com.DocSach.Sach.DTO.ReaderRequest;
import com.DocSach.Sach.DTO.ReaderUpdate;
import com.DocSach.Sach.DTO.SachMongMuonRequest;
import com.DocSach.Sach.DTO.SachMongMuonResponse;
import com.DocSach.Sach.Responsitory.SachMongMuonResponsi;
import com.DocSach.Sach.Service.SachMongMuonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor
@Slf4j
public class WishListControler {
    private final SachMongMuonService sachMongMuonService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getWishItem(@PathVariable("id") Long id) {
        return sachMongMuonService.getSachMongMuon(id);
    }
    @GetMapping
    public ResponseEntity<?> getAllWishItem() {
        return sachMongMuonService.getAllSachMongMuon();
    }
    @PostMapping
    public ResponseEntity<?> addWish(@RequestBody SachMongMuonRequest sachMongMuonRequest) {
        return sachMongMuonService.addSachMongMuon(sachMongMuonRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWish(@PathVariable("id") Long id) {
        return sachMongMuonService.deleteSachMongMuon(id);
    }
    @PutMapping
    public ResponseEntity<?> updateWish(@RequestBody SachMongMuonResponse sachMongMuonResponse) {
        return sachMongMuonService.updateSachMongMuon(sachMongMuonResponse);
    }
    @DeleteMapping("/clear/{id}")
    public ResponseEntity<?> clearAll(@PathVariable("id") Long id){
        return sachMongMuonService.deleteAllSachMongMuon(id);
    }
}
