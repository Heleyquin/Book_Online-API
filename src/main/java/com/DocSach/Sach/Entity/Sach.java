package com.DocSach.Sach.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "SACH",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "tenSach_Unique",
                        columnNames = "tenSach"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sach {
    @Id
    @Column(name = "Id_Sach")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenSach;

    private String NXB;

    @Column(name = "IMG")
    private String UrlImg;

    private String gioiThieu;

    private LocalDateTime ngayRaMat;

    private String urlFile;

    private BigDecimal giaTien;

    @CreationTimestamp
    private LocalDateTime ngayTao;

    @UpdateTimestamp
    private LocalDateTime ngayUpdate;

    private boolean active = true;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "Id_QuanLy",
            nullable = false
    )
    private QuanLy quanLy;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ct_theloai",
            joinColumns = @JoinColumn (name = "Id_Sach"),
            inverseJoinColumns = @JoinColumn (name = "Id_TheLoai")
    )
    private Set<TheLoai> theLoaiList = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ct_tacgia",
            joinColumns = @JoinColumn (name = "Id_Sach"),
            inverseJoinColumns = @JoinColumn (name = "Id_TacGia")
    )
    private Set<TacGia> tacGiaList = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "sachListFavor", cascade = CascadeType.ALL)
    private Set<Reader> favorList = new HashSet<>();

}
