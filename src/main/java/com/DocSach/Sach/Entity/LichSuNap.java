package com.DocSach.Sach.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class LichSuNap {
    @Id
    @Column(name = "Id_Nap")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime thoiGianNap;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "Id_DocGia",
            nullable = false
    )
    private Reader reader;

    private BigDecimal tien;
}
