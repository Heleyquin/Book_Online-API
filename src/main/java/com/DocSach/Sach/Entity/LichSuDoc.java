package com.DocSach.Sach.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_chuong_th_unix",
                        columnNames = {"Id_DocGia", "Id_Sach", "thoiGian"}
                )
        }
)
public class LichSuDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal daTra;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "Id_DocGia",
            nullable = false
    )
    private Reader reader;

//    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "Id_Sach",
            nullable = false
    )
    private Sach sach;

    @CreationTimestamp
    private LocalDateTime thoiGian;

}
