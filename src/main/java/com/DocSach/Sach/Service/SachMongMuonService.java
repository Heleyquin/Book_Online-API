package com.DocSach.Sach.Service;

import com.DocSach.Sach.DTO.SachMongMuonRequest;
import com.DocSach.Sach.DTO.SachMongMuonResponse;
import com.DocSach.Sach.DTO.TacGiaRequest;
import com.DocSach.Sach.DTO.TacGiaResponse;
import com.DocSach.Sach.Entity.Sach_Mong_Muon;
import com.DocSach.Sach.Entity.TacGia;
import com.DocSach.Sach.Responsitory.SachMongMuonResponsi;
import com.DocSach.Sach.Responsitory.Sach_LSD_Responsive;
import com.DocSach.Sach.Responsitory.TacGiaResponsi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SachMongMuonService {
    final private SachMongMuonResponsi sachMongMuonResponsi;

    public ResponseEntity<?> getSachMongMuon(long id) {
        Sach_Mong_Muon sachMongMuon = sachMongMuonResponsi.getReferenceById(id);
        return ResponseEntity.ok(SachMongMuonResponse.builder()
                .id(sachMongMuon.getId())
                .sachWish(sachMongMuon.getSachWish())
                .readerWish(sachMongMuon.getReaderWish())
                .build());
    }

    public ResponseEntity<?> getAllSachMongMuon() {
        List<Sach_Mong_Muon> sachMongMuons = sachMongMuonResponsi.findAll();
        return ResponseEntity.ok(sachMongMuons.stream().map(this::mapToSachMongMuonResponse).toList());
    }

    private SachMongMuonResponse mapToSachMongMuonResponse(Sach_Mong_Muon sachMongMuon) {
        return SachMongMuonResponse.builder()
                .id(sachMongMuon.getId())
                .sachWish(sachMongMuon.getSachWish())
                .readerWish(sachMongMuon.getReaderWish())
                .build();
    }

    public ResponseEntity<?> addSachMongMuon(SachMongMuonRequest sachMongMuonRequest) {
        Sach_Mong_Muon sachMongMuon = Sach_Mong_Muon.builder()
                .sachWish(sachMongMuonRequest.getSachWish())
                .readerWish(sachMongMuonRequest.getReaderWish())
                .build();
        sachMongMuonResponsi.save(sachMongMuon);
        log.info("Thêm thanh công!!!");
        return new ResponseEntity<>("Thêm thành công wishbook", HttpStatus.CREATED);
    }
    public ResponseEntity<?> updateSachMongMuon(SachMongMuonResponse sachMongMuonResponse) {
        try{
            Sach_Mong_Muon sachMongMuonCu = sachMongMuonResponsi.getReferenceById(sachMongMuonResponse.getId());
            long idS = sachMongMuonCu.getId();
            Sach_Mong_Muon sachMongMuon = Sach_Mong_Muon.builder()
                    .id(sachMongMuonCu.getId())
                    .sachWish(sachMongMuonResponse.getSachWish())
                    .readerWish(sachMongMuonResponse.getReaderWish())
                    .build();
            sachMongMuonResponsi.save(sachMongMuon);
            log.info("{} đã cập nhật", idS);
            return ResponseEntity.ok("Cập nhật thành công!!");
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> deleteSachMongMuon(long id) {
        try{
            Sach_Mong_Muon sachMongMuon = sachMongMuonResponsi.getReferenceById(id);
            long idS = sachMongMuon.getId();
            sachMongMuonResponsi.delete(sachMongMuon);
            log.info("{} đã xoá", idS);
            return ResponseEntity.ok("Xoá thành công");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> deleteAllSachMongMuon(long id) {
        try{
            List<Sach_Mong_Muon> sachMongMuons = sachMongMuonResponsi.findAllByReaderWishId(id);
            sachMongMuonResponsi.deleteAll(sachMongMuons);
            log.info("{} đã xoá hết");
            return ResponseEntity.ok("Xoá thành công");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
