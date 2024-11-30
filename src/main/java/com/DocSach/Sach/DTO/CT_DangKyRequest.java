package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Embeddable.Key_CT_Goi_Dang_Ky;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CT_DangKyRequest {
    private String maGoi;
    private Long idReader;
    private BigDecimal giaTien;
}
