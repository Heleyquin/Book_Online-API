package com.DocSach.Sach.Service;

import com.DocSach.Sach.DTO.LichSuDocRequest;
import com.DocSach.Sach.DTO.LichSuDocResponse;
import com.DocSach.Sach.DTO.ReaderResponse;
import com.DocSach.Sach.Entity.LichSuDoc;
import com.DocSach.Sach.Entity.Reader;
import com.DocSach.Sach.Responsitory.LichSuDocResponsi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LichSuDocService {
    private final LichSuDocResponsi lichSuDocResponsi;
    public ResponseEntity<?> getLichSuDoc(Long id) {
        LichSuDoc lichSuDoc = lichSuDocResponsi.getReferenceById(id);
        return ResponseEntity.ok(LichSuDocResponse.builder()
                .id(lichSuDoc.getId())
                .sach(lichSuDoc.getSach())
                .thoiGian(lichSuDoc.getThoiGian())
                .idReader(lichSuDoc.getReader())
                .build());
    }
    public ResponseEntity<?> getAllLichSuDoc() {
        List<LichSuDoc> lichSuDocs = lichSuDocResponsi.findAll();
        return ResponseEntity.ok(lichSuDocs.stream().map(this::mapToLichSuDocResponse).toList());
    }

    private LichSuDocResponse mapToLichSuDocResponse(LichSuDoc lichSuDoc) {
        return LichSuDocResponse.builder()
                .idReader(lichSuDoc.getReader())
                .id(lichSuDoc.getId())
                .sach(lichSuDoc.getSach())
                .thoiGian(lichSuDoc.getThoiGian())
                .build();
    }
    public ResponseEntity<?> addLichSuDoc(LichSuDocRequest lichSuDocRequest) {
        LichSuDoc lichSuDoc = LichSuDoc.builder()
                .reader(lichSuDocRequest.getIdReader())
                .sach(lichSuDocRequest.getSach())
                .build();
        lichSuDocResponsi.save(lichSuDoc);
        return new ResponseEntity<>("Được rồi", HttpStatus.CREATED);
    }
    public ResponseEntity<?> deleteLichSuDoc(Long id) {
        try{
            LichSuDoc lichSuDoc = lichSuDocResponsi.getReferenceById(id);
            long idLichSuDoc = lichSuDoc.getId();
            lichSuDocResponsi.delete(lichSuDoc);
            log.info("{} đã xoá", idLichSuDoc);
            return ResponseEntity.ok("Xoá thành công");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không xoá được");
        }
    }public ResponseEntity<?> updateLichSuDoc(LichSuDocResponse lichSuDocResponse) {
        try {
            LichSuDoc lichSuDoc= lichSuDocResponsi.getReferenceById(lichSuDocResponse.getId());
            LichSuDoc lichSuDocMoi = LichSuDoc.builder()
                    .id(lichSuDocResponse.getId())
                    .sach(lichSuDocResponse.getSach())
                    .thoiGian(lichSuDocResponse.getThoiGian())
                    .reader(lichSuDocResponse.getIdReader())
                    .build();
            lichSuDocResponsi.save(lichSuDocMoi);
            log.info("Đã cập nhật thành công");
            return ResponseEntity.ok("Cập nhật thành công");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không cập nhật được");
        }
    }
}
