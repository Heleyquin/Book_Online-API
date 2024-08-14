package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.Reader;
import com.DocSach.Sach.Entity.Sach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LichSuDocResponse {
    private long id;
    private BigDecimal daTra;
    private Reader idReader;
    private Sach sach;
    private LocalDateTime thoiGian;
}
