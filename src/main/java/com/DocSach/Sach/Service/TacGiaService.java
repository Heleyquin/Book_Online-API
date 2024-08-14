package com.DocSach.Sach.Service;

import com.DocSach.Sach.DTO.TacGiaRequest;
import com.DocSach.Sach.DTO.TacGiaResponse;
import com.DocSach.Sach.Entity.TacGia;
import com.DocSach.Sach.Responsitory.TacGiaResponsi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TacGiaService {
    final private TacGiaResponsi tacGiaResponsi;

    public ResponseEntity<?> getTacGiaResponse(long id) {
        TacGia tacGia = tacGiaResponsi.getReferenceById(id);
        return ResponseEntity.ok(TacGiaResponse.builder()
                .id(tacGia.getId())
                .tenTG(tacGia.getTen())
                .hoTG(tacGia.getHo())
                .gioiTinh(tacGia.isGioiTinh())
                .diaChi(tacGia.getDiaChi())
                .ngaySinh(tacGia.getNgaySinh())
                .sachs(tacGia.getSachListTG())
                .build());
    }

    public ResponseEntity<?> getAllTacGia() {
        List<TacGia> tacGias = tacGiaResponsi.findAll();
        return ResponseEntity.ok(tacGias.stream().map(this::mapToTacGiaResponse).toList());
    }

    private TacGiaResponse mapToTacGiaResponse(TacGia tac) {
        return TacGiaResponse.builder()
                .id(tac.getId())
                .tenTG(tac.getTen())
                .hoTG(tac.getHo())
                .gioiTinh(tac.isGioiTinh())
                .diaChi(tac.getDiaChi())
                .ngaySinh(tac.getNgaySinh())
                .sachs(tac.getSachListTG())
                .build();
    }

    public ResponseEntity<?> addTacGia(TacGiaRequest tacGiaRequest) {
        TacGia tacGia = TacGia.builder()
                .ten(tacGiaRequest.getTenTG())
                .ho(tacGiaRequest.getHoTG())
                .ngaySinh(tacGiaRequest.getNgaySinh())
                .diaChi(tacGiaRequest.getDiaChi())
                .gioiTinh(tacGiaRequest.isGioiTinh())
                .sachListTG(tacGiaRequest.getSachs())
                .build();
        tacGiaResponsi.save(tacGia);
        log.info("Thêm thanh công!!!");
        return new ResponseEntity<>("Thêm thành công tác giả", HttpStatus.CREATED);
    }
    public ResponseEntity<?> updateTacGia(TacGiaResponse tacGiaResponse) {
        try{
            TacGia tacGiaCu = tacGiaResponsi.getReferenceById(tacGiaResponse.getId());
            long idTG = tacGiaCu.getId();
            TacGia tacGia = TacGia.builder()
                    .id(tacGiaResponse.getId())
                    .ten(tacGiaResponse.getTenTG())
                    .ho(tacGiaResponse.getHoTG())
                    .ngaySinh(tacGiaResponse.getNgaySinh())
                    .diaChi(tacGiaResponse.getDiaChi())
                    .gioiTinh(tacGiaResponse.isGioiTinh())
                    .sachListTG(tacGiaResponse.getSachs())
                    .build();
            tacGiaResponsi.save(tacGia);
            log.info("{} đã cập nhật", idTG);
            return ResponseEntity.ok("Cập nhật thành công!!");
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> deleteTacGia(long id) {
        try{
            TacGia tacGia = tacGiaResponsi.getReferenceById(id);
            long idTG = tacGia.getId();
            tacGiaResponsi.delete(tacGia);
            log.info("{} đã xoá", idTG);
            return ResponseEntity.ok("Xoá thành công");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
