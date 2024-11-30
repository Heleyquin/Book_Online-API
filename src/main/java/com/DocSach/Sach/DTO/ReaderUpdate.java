package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Embeddable.Key_CT_Goi_Dang_Ky;
import com.DocSach.Sach.Entity.*;
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
public class ReaderUpdate {
    private Long id;
    private String ten;
    private String ho;
    private boolean gioiTinh;
    private String ngayTao;
    private String email;
    private Set<Long> lichSuDocs;
    private Set<Long> sachs;
    private Set<Key_CT_Goi_Dang_Ky> ctGoiDangKys;
    private Account idAccount;
    private Set<Long> ctLichSuMua;
    private Set<Long> ctSachMongMuon;
}
