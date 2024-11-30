package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Embeddable.Key_BinhLuan;
import com.DocSach.Sach.Entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SachRequest {
    private String tenSach;
    private String nxb;
    private String urlImg;
    private String gioiThieu;
    private String ngayRaMat;
    private BigDecimal giaTien;
    private String urlFile;
    private QuanLy idQuanLy;
    private Set<Long> theLoaiSet;
    private Set<Long> tacGias;
    private Set<Long> favors;
    private Set<Key_BinhLuan> cmts;
}
