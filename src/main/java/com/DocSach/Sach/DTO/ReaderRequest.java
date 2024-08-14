package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.Account;
import com.DocSach.Sach.Entity.LichSuDoc;
import com.DocSach.Sach.Entity.Sach;
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
public class ReaderRequest {
    private String email;
    private String ten;
    private String ho;
    private boolean gioiTinh;
    private Set<LichSuDoc> lichSuDocs;
    private Set<Sach> sachs;
    private Account idAccount;
//    private Set<Sach>
}
