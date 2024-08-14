package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuanLyRequest {
    private String ho;
    private String ten;
    private boolean gioiTinh;
    private String email;
    private Account idAccount;
}
