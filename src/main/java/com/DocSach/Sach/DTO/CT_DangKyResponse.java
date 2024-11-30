package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Embeddable.Key_CT_Goi_Dang_Ky;
import com.DocSach.Sach.Entity.GoiDangKy;
import com.DocSach.Sach.Entity.Reader;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CT_DangKyResponse {
    private Key_CT_Goi_Dang_Ky id;
    private Reader reader;
    private GoiDangKy goiDangKy;
    private BigDecimal giaTien;
}
