package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.Reader;
import com.DocSach.Sach.Entity.Sach;
import com.DocSach.Sach.Entity.Sach_Mong_Muon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LichSuMuaRequest {
    private BigDecimal giaTien;
    private Sach sachBuy;
    private Reader readerBuy;
}
