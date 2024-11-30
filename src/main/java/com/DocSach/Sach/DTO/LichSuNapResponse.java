package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.Reader;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LichSuNapResponse {
    private long id;
    private LocalDateTime thoiGianNap;
    private Reader reader;
    private BigDecimal tien;
}
