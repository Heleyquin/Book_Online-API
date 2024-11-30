package com.DocSach.Sach.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TacGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_TacGia")
    private Long id;

    @Column(name = "TEN")
    private String ten;

    @Column(name = "HO")
    private String ho;

    private LocalDateTime ngaySinh;

    private boolean gioiTinh;

    private String diaChi;

    @ManyToMany(mappedBy = "tacGiaList", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Sach> sachListTG = new HashSet<>();

}
