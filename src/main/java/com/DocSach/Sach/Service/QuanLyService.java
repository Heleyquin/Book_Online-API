package com.DocSach.Sach.Service;

import com.DocSach.Sach.DTO.QuanLyRequest;
import com.DocSach.Sach.DTO.QuanLyResponse;
import com.DocSach.Sach.DTO.QuanLyUpdate;
import com.DocSach.Sach.Entity.Account;
import com.DocSach.Sach.Entity.QuanLy;
import com.DocSach.Sach.Responsitory.AccountResponsi;
import com.DocSach.Sach.Responsitory.QuanLyResponsi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuanLyService {
    private final QuanLyResponsi quanLyResponsi;
    private final AccountResponsi accountResponsi;

    public ResponseEntity<?> getQuanLy(long id) {
        QuanLy quanLy = quanLyResponsi.getReferenceById(id);
        return ResponseEntity.ok(QuanLyResponse.builder()
                .id(quanLy.getId())
                .email(quanLy.getEmail())
                .ten(quanLy.getTen())
                .ho(quanLy.getHo())
                .idAccount(quanLy.getAccount())
                .gioiTinh(quanLy.isGioiTinh())
                .ngayTao(quanLy.getNgayTao())
                .build());
    }

    public ResponseEntity<?> getAllQuanLy() {
        List<QuanLy> quanLIES = quanLyResponsi.findAll();
        return ResponseEntity.ok(quanLIES.stream().map(this::mapToQuanLyResponse).toList());
    }

    public QuanLyResponse mapToQuanLyResponse(QuanLy quanLy) {
        return QuanLyResponse.builder()
                .id(quanLy.getId())
                .email(quanLy.getEmail())
                .ten(quanLy.getTen())
                .ho(quanLy.getHo())
                .idAccount(quanLy.getAccount())
                .gioiTinh(quanLy.isGioiTinh())
                .ngayTao(quanLy.getNgayTao())
                .build();
    }

    public ResponseEntity<?> getQuanLyByUsername(String username) {
        Account account = accountResponsi.findByUsername(username);
        QuanLy quanLy = quanLyResponsi.findByAccount(account);
        return ResponseEntity.ok(QuanLyResponse.builder()
                .id(quanLy.getId())
                .ten(quanLy.getTen())
                .ho(quanLy.getHo())
                .email(quanLy.getEmail())
                .gioiTinh(quanLy.isGioiTinh())
                .idAccount(quanLy.getAccount())
                .ngayTao(quanLy.getNgayTao())
                .build());
    }

    public ResponseEntity<?> addQuanLy(QuanLyRequest quanLyRequest) {
        QuanLy quanLy = QuanLy.builder()
                .ten(quanLyRequest.getTen())
                .ho(quanLyRequest.getHo())
                .gioiTinh(quanLyRequest.isGioiTinh())
                .email(quanLyRequest.getEmail())
                .account(quanLyRequest.getIdAccount())
                .build();
        quanLyResponsi.save(quanLy);
        log.info("{} đã thêm", quanLy.getHo() + " " + quanLy.getTen());
        return new ResponseEntity<>("Thêm người dùng thành công!!!!!!!", HttpStatus.CREATED);
    }
    public ResponseEntity<?> deleteQuanLy(long id) {
        try{
            QuanLy nguoiDung = quanLyResponsi.getReferenceById(id);
            long idNguoiDung = nguoiDung.getId();
            quanLyResponsi.delete(nguoiDung);
            log.info("{} đã xoá", idNguoiDung);
            return ResponseEntity.ok("Xoá thành công");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Người dùng không tồn tại");
        }
    }
    public ResponseEntity<?> updateQuanLy(QuanLyUpdate quanLyResponse) {
        try {
            QuanLy nguoiDungCu= quanLyResponsi.getReferenceById(quanLyResponse.getId());
            QuanLy nguoiDung = QuanLy.builder()
                    .id(quanLyResponse.getId())
                    .ten(quanLyResponse.getTen())
                    .ho(quanLyResponse.getHo())
                    .gioiTinh(quanLyResponse.isGioiTinh())
                    .email(quanLyResponse.getEmail())
                    .account(quanLyResponse.getIdAccount())
                    .ngayTao(LocalDateTime.parse(quanLyResponse.getNgayTao()))
                    .build();
            quanLyResponsi.save(nguoiDung);
            log.info("{} đã cập nhật thành {}", nguoiDungCu.getHo(), nguoiDung.getTen());
            return ResponseEntity.ok("Cập nhật thành công");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Người dùng không tồn tại");
        }
    }
}
