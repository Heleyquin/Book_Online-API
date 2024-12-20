package com.DocSach.Sach.DTO;

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
public class SachResponse {
    private long id;
    private String tenSach;
    private String nxb;
    private String urlImg;
    private String gioiThieu;
    private LocalDateTime ngayTao;
    private LocalDateTime ngayUpdate;
    private LocalDateTime ngayRaMat;
    private BigDecimal giaTien;
    private String urlFile;
    private boolean active;
    private QuanLy idQuanLy;
    private Set<TheLoai> theLoaiSet;
    private Set<TacGia> tacGias;
    private Set<Reader> favors;
    private Set<Cmt> cmts;
}
