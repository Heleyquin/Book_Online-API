package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReaderResponse {
    private Long id;
    private String ten;
    private String ho;
    private boolean gioiTinh;
    private LocalDateTime ngayTao;
    private String email;
    private Set<LichSuDoc> lichSuDocs;
    private Set<Sach> sachs;
    private Set<CT_Dang_Ky> ctGoiDangKys;
    private Account idAccount;
    private Set<LichSuMua> ctLichSuMua;
    private Set<Sach_Mong_Muon> ctSachMongMuon;

}
