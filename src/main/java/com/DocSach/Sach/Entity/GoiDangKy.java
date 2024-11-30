package com.DocSach.Sach.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "GOIDANGKY"
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class GoiDangKy {
    @Id
    @Column(name = "MA_GOI", nullable = false)
    private String maGoi;

    @Column(name = "CHU_THICH")
    private String chuThich;

    @Column(name = "GIA_TIEN", nullable = false)
    private BigDecimal giaTien;

    @Column(name = "THOI_HAN", nullable = false)
    private int thoiHan;

    @Column(name = "TRANG_THAI")
    private boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "goiDetail",cascade = CascadeType.MERGE)
    private Set<CT_Goi> ctGoiSet = new HashSet<>();



}
