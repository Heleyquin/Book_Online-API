package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Embeddable.Key_BinhLuan;
import com.DocSach.Sach.Embeddable.Key_DanhGia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DanhGiaRequest {
    private Key_DanhGia id;
    private int point;
    private String nhanXet;
}
