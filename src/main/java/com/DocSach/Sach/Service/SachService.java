package com.DocSach.Sach.Service;

import com.DocSach.Sach.DTO.SachRequest;
import com.DocSach.Sach.DTO.SachResponse;
import com.DocSach.Sach.DTO.SachUpdate;
import com.DocSach.Sach.Entity.*;
import com.DocSach.Sach.Responsitory.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class SachService {
    private final SachResponsi sachResponsi;
    private final TheLoaiResponsi theLoaiResponsi;
    private final TacGiaResponsi tacGiaResponsi;
    private final ReaderResponsive readerResponsive;
    private final LuotDocSachReponsi luotDocSachReponsi;
    private final CountAllFavorReponsi countAllFavorReponsi;
    private final Top5Reponsi top5Reponsi;
    private final Top5MienPhiReponsi top5MienPhiReponsi;
    private final Sach_LSD_Responsive sachLsdResponsive;


    public List<Top5MienPhi> getTop5MienPhi(){
        List<Top5MienPhi> top5MienPhi = top5MienPhiReponsi.findAll();
        return top5MienPhi;
    }
    public List<CountAllFavor> getCountAllFavorReponsi() {
        List<CountAllFavor> countAllFavorList = countAllFavorReponsi.findAll();
        return countAllFavorList;
    }

    public List<LuotDocSach> getLuotDoc(){
        List<LuotDocSach> luotDocSaches = luotDocSachReponsi.findAll();
        return luotDocSaches;
    }

    public ResponseEntity<?> getSachResponsi(long id) {
        Sach sach = sachResponsi.getReferenceById(id);
        return ResponseEntity.ok(SachResponse.builder()
                .tenSach(sach.getTenSach())
                .id(sach.getId())
                .nxb(sach.getNXB())
                .ngayTao(sach.getNgayTao())
                .ngayCapNhat(sach.getNgayUpdate())
                .active(sach.isActive())
                .urlImg(sach.getUrlImg())
                .ngayRaMat(sach.getNgayRaMat())
                .gioiThieu(sach.getGioiThieu())
                .giaTien(sach.getGiaTien())
                .urlFile(sach.getUrlFile())
                .idQuanLy(sach.getQuanLy())
                .tacGias(sach.getTacGiaList())
                .theLoaiSet(sach.getTheLoaiList())
                .favors(sach.getFavorList())
                .build());
    }

    public ResponseEntity<?> getAllSach(){
        List<Sach> sachs = sachResponsi.findAll();
        return ResponseEntity.ok(sachs.stream().map(this::mapToSachResponse).toList());
    }

    private SachResponse mapToSachResponse(Sach sach) {
        return SachResponse.builder()
                .tenSach(sach.getTenSach())
                .id(sach.getId())
                .nxb(sach.getNXB())
                .ngayTao(sach.getNgayTao())
                .ngayCapNhat(sach.getNgayUpdate())
                .ngayRaMat(sach.getNgayRaMat())
                .active(sach.isActive())
                .urlImg(sach.getUrlImg())
                .giaTien(sach.getGiaTien())
                .urlFile(sach.getUrlFile())
                .gioiThieu(sach.getGioiThieu())
                .idQuanLy(sach.getQuanLy())
                .tacGias(sach.getTacGiaList())
                .theLoaiSet(sach.getTheLoaiList())
                .favors(sach.getFavorList())
                .build();
    }

    public ResponseEntity<?> addSach(SachRequest sachRequest){
        if(sachResponsi.existsByTenSach(sachRequest.getTenSach())){
            return new ResponseEntity<>("Tên sách đã có rồi!!!!!", HttpStatus.BAD_REQUEST);
        }
        Sach sach = Sach.builder()
                .tenSach(sachRequest.getTenSach())
                .active(true)
                .UrlImg(sachRequest.getUrlImg())
                .gioiThieu(sachRequest.getGioiThieu())
                .NXB(sachRequest.getNxb())
                .giaTien(sachRequest.getGiaTien())
                .urlFile(sachRequest.getUrlFile())
                .ngayRaMat(LocalDateTime.parse(sachRequest.getNgayRaMat()))
                .quanLy(sachRequest.getIdQuanLy())
                .tacGiaList(convertToTacGiaEntities(sachRequest.getTacGias()))
                .theLoaiList(convertToTheLoaiEntities(sachRequest.getTheLoaiSet()))
                .favorList(convertToReaderEntities(sachRequest.getFavors()))
                .build();
        sachResponsi.save(sach);
        log.info("Đã thêm {}", sach.getTenSach());
//        log.info("Thêm list {}", sach.getTheLoaiList().stream().toList());
        return new ResponseEntity<>("Thêm thành công!!!!!", HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateSach(SachUpdate sachUpdate){
        try{
            Sach sachCu = sachResponsi.getReferenceById(sachUpdate.getId());
            Sach sach = Sach.builder()
                    .id(sachUpdate.getId())
                    .tenSach(sachUpdate.getTenSach())
                    .active(sachUpdate.isActive())
                    .UrlImg(sachUpdate.getUrlImg())
                    .gioiThieu(sachUpdate.getGioiThieu())
                    .ngayRaMat(LocalDateTime.parse(sachUpdate.getNgayRaMat()))
                    .giaTien(sachUpdate.getGiaTien())
                    .urlFile(sachUpdate.getUrlFile())
                    .NXB(sachUpdate.getNxb())
                    .quanLy(sachUpdate.getIdQuanLy())
                    .tacGiaList(convertToTacGiaEntities(sachUpdate.getTacGias()))
                    .theLoaiList(convertToTheLoaiEntities(sachUpdate.getTheLoaiSet()))
                    .favorList(sachUpdate.getFavors())
                    .ngayTao(LocalDateTime.parse(sachUpdate.getNgayTao()))
                    .build();
            sachResponsi.save(sach);
            log.info("{} đã cập nhật thành {}", sachCu.getTenSach(), sach.getTenSach());
            log.info("Thêm list {}", sach.getTheLoaiList().stream().toList());
            return ResponseEntity.ok("Cập nhật thành công");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cập nhật không thành công");
        }
    }

    private Set<TheLoai> convertToTheLoaiEntities(Set<Long> theLoaiId) {
        Set<TheLoai> theLoaiSet = new HashSet<>();
        for (Long id : theLoaiId) {
            theLoaiSet.add(theLoaiResponsi.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại")));
        }
        return theLoaiSet;
    }
    private Set<TacGia> convertToTacGiaEntities(Set<Long> tacGiaIds) {
        Set<TacGia> tacGiaSet = new HashSet<>();
        for (Long id : tacGiaIds) {
            tacGiaSet.add(tacGiaResponsi.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy tác giả")));
        }
        return tacGiaSet;
    }
    private Set<Reader> convertToReaderEntities(Set<Long> readerIds) {
        Set<Reader> readerSet = new HashSet<>();
        for (Long id : readerIds) {
            readerSet.add(readerResponsive.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy tác giả")));
        }
        return readerSet;
    }

    public List<Top5Sach> getTop5(){
        List<Top5Sach> top5SachList = top5Reponsi.findAll();
        return top5SachList;
    }

    @Transactional
    public ResponseEntity<?> addSachs(List<SachRequest> sachRequest){
        for(SachRequest sachsq : sachRequest){
            Sach sach = Sach.builder()
                    .tenSach(sachsq.getTenSach())
                    .active(true)
                    .UrlImg(sachsq.getUrlImg())
                    .gioiThieu(sachsq.getGioiThieu())
                    .NXB(sachsq.getNxb())
                    .giaTien(sachsq.getGiaTien())
                    .urlFile(sachsq.getUrlFile())
                    .ngayRaMat(LocalDateTime.parse(sachsq.getNgayRaMat()))
                    .quanLy(sachsq.getIdQuanLy())
                    .tacGiaList(convertToTacGiaEntities(sachsq.getTacGias()))
                    .theLoaiList(convertToTheLoaiEntities(sachsq.getTheLoaiSet()))
                    .favorList(convertToReaderEntities(sachsq.getFavors()))
                    .build();
            sachResponsi.save(sach);
            log.info("Đã thêm {}", sach.getTenSach());
        }
        return ResponseEntity.ok("Thêm thành công");
    }

    public ResponseEntity<?> deleteSach(long id){
        try{
            Sach sach = sachResponsi.getReferenceById(id);
            sachResponsi.delete(sach);
            log.info("{} đã xoá", id);
            return ResponseEntity.ok("Xoá thành công");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xoá không thành công");
        }
    }

    public ResponseEntity<?> getPdf(String fileName){
        try{
            String filePath = "statics/" + fileName;
            ClassPathResource resp = new ClassPathResource(filePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resp.getFilename() + "\"")
                    .body(resp.getInputStream().readAllBytes());
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getImg(String fileName){
        try{
            String filePath = "statics/" + fileName;
            ClassPathResource resp = new ClassPathResource(filePath);
            if(!resp.exists()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resp.getFilename() + "\"")
                    .body(resp);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public List<Sach_SLD> getSach_LSD(){
        List<Sach_SLD> s = sachLsdResponsive.findAll();
        return s;
    }

//    public ResponseEntity<?> getNewest(){
//
//    }
//    public ResponseEntity<?> getMostRead(){
//
//    }
}
