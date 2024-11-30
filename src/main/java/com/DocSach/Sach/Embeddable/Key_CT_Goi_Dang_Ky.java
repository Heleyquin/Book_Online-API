package com.DocSach.Sach.Embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Key_CT_Goi_Dang_Ky implements Serializable {
    private Long idDocGia;
    private String maGoi;
    private LocalDateTime thoiGianDangKy;
}
