package com.DocSach.Sach.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "THELOAI",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "tenTheLoai_Unique",
                columnNames = "TENTL"
        )
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheLoai {
    @Id
    @Column(name = "Id_TheLoai")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TENTL", nullable = false)
    private String tenTheLoai;

    @ManyToMany(mappedBy = "theLoaiList", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Sach> sachListTL = new HashSet<>();

//    public TheLoai(Long id) {
//        this.id = id;
//    }
}
