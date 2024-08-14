package com.DocSach.Sach.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "DocGia",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique_docGia",
                        columnNames = "EMAIL"
                )
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reader {
    @Id
    @Column(name = "Id_DocGia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ten;
    private String ho;
    private boolean gioiTinh;

    @CreationTimestamp
    private LocalDateTime ngayTao;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.MERGE)
    private Set<LichSuDoc> lichSuDocs = new HashSet<>();

//    @ManyToMany(mappedBy = "favorList", cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ct_favor",
            joinColumns = @JoinColumn(name = "Id_DocGia"),
            inverseJoinColumns = @JoinColumn(name = "Id_Sach")
    )
    private Set<Sach> sachListFavor = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "TK")
    private Account account;
}
