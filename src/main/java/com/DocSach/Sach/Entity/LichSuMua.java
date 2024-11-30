package com.DocSach.Sach.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_buy_unix",
                        columnNames = {"Id_DocGia", "Id_Sach"}
                )
        }
)
public class LichSuMua {
    @Id
    @Column(name = "Id_Mua")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime thoiGianMua;

    private BigDecimal giaTien;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "Id_DocGia",
            nullable = false
    )
    private Reader readerBuy;

//    @OneToMany(mappedBy = "lichSuMua",cascade = CascadeType.MERGE)
//    private Set<Reader> readerBuy = new HashSet<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "Id_Sach",
            nullable = false
    )
    private Sach sachBuy;
}
