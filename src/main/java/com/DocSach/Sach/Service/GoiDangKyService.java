package com.DocSach.Sach.Service;

import com.DocSach.Sach.DTO.GoiDangKyRequest;;
import com.DocSach.Sach.DTO.GoiDangKyResponse;
import com.DocSach.Sach.Entity.GoiDangKy;
import com.DocSach.Sach.Responsitory.GoiDangKyResponsi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoiDangKyService {
    private final GoiDangKyResponsi goiDangKyResponsi;

    public ResponseEntity<?> getGoiDangKyResponse(String ma) {
        GoiDangKy goiDangKy = goiDangKyResponsi.getReferenceById(ma);
        return ResponseEntity.ok(GoiDangKyResponse.builder()
                .maGoi(goiDangKy.getMaGoi())
                .chuThich(goiDangKy.getChuThich())
                .giaTien(goiDangKy.getGiaTien())
                .thoiHan(goiDangKy.getThoiHan())
                .ctGoiSet(goiDangKy.getCtGoiSet())
                .active(goiDangKy.isActive())
                .build());
    }

    public ResponseEntity<?> getAllGoiDangKy() {
        List<GoiDangKy> goiDangKies = goiDangKyResponsi.findAll();
        return ResponseEntity.ok(goiDangKies.stream().map(this::mapToGoiDangKyResponse).toList());
    }

    private GoiDangKyResponse mapToGoiDangKyResponse(GoiDangKy goi) {
        return GoiDangKyResponse.builder()
                .maGoi(goi.getMaGoi())
                .chuThich(goi.getChuThich())
                .giaTien(goi.getGiaTien())
                .thoiHan(goi.getThoiHan())
                .active(goi.isActive())
                .ctGoiSet(goi.getCtGoiSet())
                .build();
    }

    public ResponseEntity<?> addGoiDangKy(GoiDangKyRequest goiDangKyRequest) {
        GoiDangKy goiDangKy = GoiDangKy.builder()
                .maGoi(goiDangKyRequest.getMaGoi())
                .chuThich(goiDangKyRequest.getChuThich())
                .giaTien(goiDangKyRequest.getGiaTien())
                .thoiHan(goiDangKyRequest.getThoiHan())
                .ctGoiSet(goiDangKyRequest.getCtGoiSet())
                .active(true)
                .build();
        goiDangKyResponsi.save(goiDangKy);
        log.info("Thêm thanh công!!!");
        return new ResponseEntity<>("Thêm thành công gói", HttpStatus.CREATED);
    }
    public ResponseEntity<?> updateGoiDangKy(GoiDangKyResponse goiDangKyResponse) {
        try{
            GoiDangKy goiDangKycu = goiDangKyResponsi.getReferenceById(goiDangKyResponse.getMaGoi());
            String idGoi = goiDangKycu.getMaGoi();
            GoiDangKy goiDangKy = GoiDangKy.builder()
                    .maGoi(goiDangKyResponse.getMaGoi())
                    .chuThich(goiDangKyResponse.getChuThich())
                    .giaTien(goiDangKyResponse.getGiaTien())
                    .thoiHan(goiDangKyResponse.getThoiHan())
                    .ctGoiSet(goiDangKyResponse.getCtGoiSet())
                    .active(goiDangKycu.isActive())
                    .build();
            goiDangKyResponsi.save(goiDangKy);
            log.info("{} đã cập nhật", idGoi);
            return ResponseEntity.ok("Cập nhật thành công!!");
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> deleteGoi(String id) {
        try{
            GoiDangKy goiDangKy = goiDangKyResponsi.getReferenceById(id);
            String idGoi = goiDangKy.getMaGoi();
            goiDangKyResponsi.delete(goiDangKy);
            log.info("{} đã xoá", idGoi);
            return ResponseEntity.ok("Xoá thành công");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
