package com.DocSach.Sach.Service;

import com.DocSach.Sach.DTO.TheLoaiRequest;
import com.DocSach.Sach.DTO.TheLoaiResponse;
import com.DocSach.Sach.Entity.TheLoai;
import com.DocSach.Sach.Responsitory.TheLoaiResponsi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TheLoaiService {
    final private TheLoaiResponsi theLoaiResponsi;

    public ResponseEntity<?> getTheLoai(long id){
        TheLoai theLoai = theLoaiResponsi.getReferenceById(id);
        return ResponseEntity.ok(TheLoaiResponse.builder()
                .id(theLoai.getId())
                .tenTL(theLoai.getTenTheLoai())
                .sachs(theLoai.getSachListTL())
                .build());
    }

    public ResponseEntity<?> getAllTheLoai(){
        List<TheLoai> theLoais = theLoaiResponsi.findAll();
        return ResponseEntity.ok(theLoais.stream().map(this::mapToResponse).toList());
    }
    private TheLoaiResponse mapToResponse(TheLoai theLoai){
        return TheLoaiResponse.builder()
                .id(theLoai.getId())
                .tenTL(theLoai.getTenTheLoai())
                .sachs(theLoai.getSachListTL())
                .build();
    }

    public ResponseEntity<?> addTheLoai(TheLoaiRequest theLoaiRequest){
        if(theLoaiResponsi.existsByTenTheLoai(theLoaiRequest.getTenTL())){
            return new ResponseEntity<>("Tên sach đã có rồi!!!!!", HttpStatus.BAD_REQUEST);
        }
        TheLoai theLoai = TheLoai.builder()
                .tenTheLoai(theLoaiRequest.getTenTL())
                .sachListTL(theLoaiRequest.getSachs())
                .build();
        theLoaiResponsi.save(theLoai);
        log.info("{} đã thêm thành công", theLoai.getTenTheLoai());
        return new ResponseEntity<>("Thêm thành công!!!!!", HttpStatus.CREATED);
    }
    public ResponseEntity<?> updateTheLoai(TheLoaiResponse theLoaiResponse){
        try{
            TheLoai theLoaiCu = theLoaiResponsi.getReferenceById(theLoaiResponse.getId());
            TheLoai theLoai = TheLoai.builder()
                    .id(theLoaiResponse.getId())
                    .tenTheLoai(theLoaiResponse.getTenTL())
                    .sachListTL(theLoaiResponse.getSachs())
                    .build();
            theLoaiResponsi.save(theLoai);
            return ResponseEntity.ok("Sửa thành công!!");
        }catch (Exception e){
            return new ResponseEntity<>("Không sửa được", HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> deleteTheLoai(long id){
        try{
            TheLoai theLoai = theLoaiResponsi.getReferenceById(id);
            theLoaiResponsi.delete(theLoai);
            log.info("{} đã xoá", id);
            return ResponseEntity.ok("Xoá thành công");
        }catch (Exception e){
            return new ResponseEntity<>("Không xoá được!!", HttpStatus.BAD_REQUEST);
        }
    }
}
