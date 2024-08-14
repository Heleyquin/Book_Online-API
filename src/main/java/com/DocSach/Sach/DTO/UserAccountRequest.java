package com.DocSach.Sach.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountRequest {
    private String email;
    private String tk;
    private String ho;
    private String ten;
    private String diaChi;
    private boolean gioiTinh;
    private int role;
}
