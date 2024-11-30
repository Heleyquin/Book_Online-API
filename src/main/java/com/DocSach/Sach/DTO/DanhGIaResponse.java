package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Embeddable.Key_BinhLuan;
import com.DocSach.Sach.Embeddable.Key_DanhGia;
import com.DocSach.Sach.Entity.Reader;
import com.DocSach.Sach.Entity.Sach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DanhGIaResponse {
    private Key_DanhGia id;
    private int point;
    private Sach sachRate;
    private Reader readerRate;
    private LocalDateTime thoiGianTao;
    private LocalDateTime thoiGianCapNhat;
    private String nhanXet;
}
