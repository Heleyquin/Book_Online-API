package com.DocSach.Sach.Service;

import com.DocSach.Sach.DTO.ReaderRequest;
import com.DocSach.Sach.DTO.ReaderResponse;
import com.DocSach.Sach.DTO.ReaderUpdate;
import com.DocSach.Sach.Entity.*;
import com.DocSach.Sach.Responsitory.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReaderService {
    private final ReaderResponsive readerResponsive;
    private final AccountResponsi accountResponsi;
    private final LuotDocDocGiaReponsive luotDocDocGiaReponsive;
    private final SachResponsi sachResponsi;
    private final LichSuDocResponsi lichSuDocResponsi;

    public List<LuotDocDocGia> getLuotDocDocGia() {
        List<LuotDocDocGia> luotDocDocGias = luotDocDocGiaReponsive.findAll();
        return luotDocDocGias;
    }

    public ResponseEntity<?> getNguoiDung(long id) {
        Reader nguoiDung = readerResponsive.getReferenceById(id);
        return ResponseEntity.ok(ReaderResponse.builder()
                .id(nguoiDung.getId())
                .ten(nguoiDung.getTen())
                .ho(nguoiDung.getHo())
                .email(nguoiDung.getEmail())
                .gioiTinh(nguoiDung.isGioiTinh())
                .idAccount(nguoiDung.getAccount())
                .sachs(nguoiDung.getSachListFavor())
                .ngayTao(nguoiDung.getNgayTao())
                .lichSuDocs(nguoiDung.getLichSuDocs())
                .build());
    }

    public ResponseEntity<?> getAllNguoiDung() {
        List<Reader> nguoiDungs = readerResponsive.findAll();
        return ResponseEntity.ok(nguoiDungs.stream().map(this::mapToNguoiDungResponse).toList());
    }

    public ReaderResponse mapToNguoiDungResponse(Reader nguoiDung) {
        return ReaderResponse.builder()
                .id(nguoiDung.getId())
                .ten(nguoiDung.getTen())
                .ho(nguoiDung.getHo())
                .email(nguoiDung.getEmail())
                .gioiTinh(nguoiDung.isGioiTinh())
                .ngayTao(nguoiDung.getNgayTao())
                .idAccount(nguoiDung.getAccount())
                .sachs(nguoiDung.getSachListFavor())
                .lichSuDocs(nguoiDung.getLichSuDocs())
                .build();
    }

    public List<Reader> getReaderRencent(){
        LocalDateTime day = LocalDateTime.now().minusDays(7);
        return readerResponsive.findByNgayTaoAfter(day);
    }

    public ResponseEntity<?> getReaderByUsername(String username){
        Account account = accountResponsi.findByUsername(username);
        Reader reader = readerResponsive.findByAccount(account);
        return ResponseEntity.ok(ReaderResponse.builder()
                .id(reader.getId())
                .sachs(reader.getSachListFavor())
                .idAccount(reader.getAccount())
                .ten(reader.getTen())
                .ho(reader.getHo())
                .email(reader.getEmail())
                .gioiTinh(reader.isGioiTinh())
                .ngayTao(reader.getNgayTao())
                .lichSuDocs(reader.getLichSuDocs())
                .build());
    }

    public ResponseEntity<?> addNguoiDung(ReaderRequest readerRequest) {
        Reader nguoiDung = Reader.builder()
                .ten(readerRequest.getTen())
                .ho(readerRequest.getHo())
                .gioiTinh(readerRequest.isGioiTinh())
                .email(readerRequest.getEmail())
                .account(readerRequest.getIdAccount())
                .sachListFavor(readerRequest.getSachs())
                .lichSuDocs(readerRequest.getLichSuDocs())
                .build();
        readerResponsive.save(nguoiDung);
        log.info("{} đã thêm", nguoiDung.getHo() + " " + nguoiDung.getTen());
        return new ResponseEntity<>("Thêm người dùng thành công!!!!!!!", HttpStatus.CREATED);
    }
    public ResponseEntity<?> deleteNguoiDung(long id) {
        try{
            Reader nguoiDung = readerResponsive.getReferenceById(id);
            long idNguoiDung = nguoiDung.getId();
            readerResponsive.delete(nguoiDung);
            log.info("{} đã xoá", idNguoiDung);
            return ResponseEntity.ok("Xoá thành công");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Người dùng không tồn tại");
        }
    }
    public ResponseEntity<?> updateNguoiDung(ReaderUpdate readerResponse) {
        try {
            Reader nguoiDungCu= readerResponsive.getReferenceById(readerResponse.getId());
            Reader nguoiDung = Reader.builder()
                    .id(readerResponse.getId())
                    .ten(readerResponse.getTen())
                    .ho(readerResponse.getHo())
                    .email(readerResponse.getEmail())
                    .gioiTinh(readerResponse.isGioiTinh())
                    .account(readerResponse.getIdAccount())
                    .sachListFavor(convertToSachEntities(readerResponse.getSachs()))
                    .lichSuDocs(convertToLichSuEntities(readerResponse.getLichSuDocs()))
                    .ngayTao(LocalDateTime.parse(readerResponse.getNgayTao()))
                    .build();
            readerResponsive.save(nguoiDung);
            log.info("{} đã cập nhật thành công", nguoiDungCu.getHo() + " " + nguoiDungCu.getTen());
            return ResponseEntity.ok("Cập nhật thành công");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không thành công, kiểm tra lại");
        }
    }

    private Set<Sach> convertToSachEntities(Set<Long> sachIds) {
        Set<Sach> sachSet = new HashSet<>();
        for (Long id : sachIds) {
            sachSet.add(sachResponsi.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sách")));
        }
        return sachSet;
    }
    private Set<LichSuDoc> convertToLichSuEntities(Set<Long> lichSuIds) {
        Set<LichSuDoc> lichSuDocs = new HashSet<>();
        for (Long id : lichSuIds) {
            lichSuDocs.add(lichSuDocResponsi.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy lịch sử")));
        }
        return lichSuDocs;
    }
}
