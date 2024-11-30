package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.Reader;
import com.DocSach.Sach.Entity.Sach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LichSuDocRequest {
    private Reader idReader;
    private Sach sach;
}
