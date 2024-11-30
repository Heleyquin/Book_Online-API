package com.DocSach.Sach.Controller;

import com.DocSach.Sach.DTO.*;
import com.DocSach.Sach.Embeddable.Key_DanhGia;
import com.DocSach.Sach.Entity.*;
import com.DocSach.Sach.Service.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/lich-su-mua/{id}")
    public ResponseEntity<?> getLichSuMua(@PathVariable("id") Long id){
        return readerService.getLichSuMua(id);
    }
    @GetMapping("/lich-su-nap/{id}")
    public ResponseEntity<?> getLichSuNap(@PathVariable("id") Long id){
        return readerService.getLichSuNap(id);
    }
    @PostMapping("/nap-tien")
    public ResponseEntity<?> napTien(@RequestBody LichSuNapRequest lichSuNapRequest){
        return readerService.napTien(lichSuNapRequest);
    }
    @PostMapping("/mua-sach")
    public ResponseEntity<?> muaSach(@RequestBody LichSuMuaRequest lichSuMuaRequest){
        return readerService.muaSach(lichSuMuaRequest);
    }
    @GetMapping("/sach-mong-muon/{id}")
    public ResponseEntity<?> getWishList(@PathVariable("id") Long id){
        return readerService.wishList(id);
    }
    @GetMapping("/amount/{id}")
    public ResponseEntity<?> getAmount(@PathVariable("id") Long id){
        Double amount = readerService.getAmount(id);
        DecimalFormat df = new DecimalFormat("#.##");
        return ResponseEntity.ok(df.format(amount));
    }
    @DeleteMapping("/empty")
    public ResponseEntity<?> emptyWish(@RequestBody List<Sach_Mong_Muon> sachMongMuons){
        List<Long> idWishList = sachMongMuons.stream().map(Sach_Mong_Muon::getId).toList();
        return readerService.delAll(idWishList);
    }
    @PostMapping("/buy-all")
    public ResponseEntity<?> addBuyHistory(@RequestBody List<LichSuMuaRequest> lichSuMuaRequests) {
        return readerService.buyAll(lichSuMuaRequests);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody PurchaseRequest purchaseRequest){
        return readerService.purchase(purchaseRequest.getLichSuMuaRequests(), purchaseRequest.getWishIds());
    }
    @PostMapping("/purchaseSingle")
    public ResponseEntity<?> purchaseSingleBook(@RequestBody LichSuMuaRequest lichSuMuaRequest){
        return readerService.purchaseOne(lichSuMuaRequest);
    }

    @GetMapping("/pack-on-use/{id}")
    public ResponseEntity<?> packOnUse(@PathVariable("id") Long id){
        return ResponseEntity.ok(readerService.callSP(id));
    }
    @PostMapping("/purchase-pack")
    public ResponseEntity<?> packPurchase(@RequestBody CT_DangKyRequest ctDangKyRequest){
        return readerService.purchasePack(ctDangKyRequest);
    }
    @PostMapping("/comment")
    public  ResponseEntity<?> readerCmt(@RequestBody CmtRequest cmtRequest){
        return readerService.comment(cmtRequest);
    }
    @GetMapping("pack-purchased/{id}")
    public ResponseEntity<?> allPack(@PathVariable("id") Long id){
        return readerService.allPackPurchase(id);
    }
    @PostMapping("/rate")
    public ResponseEntity<?> rateBook(@RequestBody DanhGiaRequest danhGiaRequest){
        return readerService.danhGia(danhGiaRequest);
    }
    @PostMapping("/cmt-and-rate")
    public ResponseEntity<?> rateACmt(@RequestBody RateAndCmt rateAndCmt){
        return readerService.rateWithComment(rateAndCmt);
    }
    @GetMapping("/rate-by-book-reader")
    public ResponseEntity<?> getRateByBookAndReader(@RequestParam Long idSach, @RequestParam Long idDocGia){
        Key_DanhGia keyDanhGia = new Key_DanhGia(idSach, idDocGia);
        return readerService.getRateByUserAndBook(keyDanhGia);
    }

}
