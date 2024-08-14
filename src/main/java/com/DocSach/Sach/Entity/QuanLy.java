package com.DocSach.Sach.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "QuanLy",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique",
                        columnNames = "EMAIL"
                )
        }
)
public class QuanLy {
    @Id
    @Column(name = "Id_QuanLy")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ho;
    private String ten;
    private boolean gioiTinh;

    @Column(name = "EMAIL")
    private String email;

    @CreationTimestamp
    private LocalDateTime ngayTao;

    @OneToOne
    @JoinColumn(name = "TK")
    private Account account;
}
