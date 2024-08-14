package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuanLyUpdate {
    private long id;
    private String ho;
    private String ten;
    private boolean gioiTinh;
    private String email;
    private Account idAccount;
    private String ngayTao;
}
