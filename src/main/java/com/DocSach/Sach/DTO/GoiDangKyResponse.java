package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.CT_Goi;
import com.DocSach.Sach.Entity.QuanLy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoiDangKyResponse {
    private String maGoi;
    private String chuThich;
    private BigDecimal giaTien;
    private int thoiHan;
    private Set<CT_Goi> ctGoiSet;
    private boolean active;
}
