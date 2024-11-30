package com.DocSach.Sach.Entity;

import com.DocSach.Sach.Embeddable.Key_DanhGia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "DANHGIA"
)
public class DanhGia {
    @EmbeddedId
    private Key_DanhGia id;

    @JsonIgnore
    @ManyToOne
    @MapsId("idSach")
    @JoinColumn(name = "Id_Sach")
    private Sach sachRate;

    @ManyToOne
    @MapsId("Id_DocGia")
    @JoinColumn(name = "Id_Docgia")
    private Reader readerRate;

    @Column(name = "DIEMSO", nullable = false)
    private int point;

    @Column(name = "NHANXET")
    private String nhanXet;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime thoiGianTao;

    @UpdateTimestamp
    private LocalDateTime thoiGianCapNhat;
    @PrePersist
    public void prePersist() {
        if (this.thoiGianTao == null) {
            this.thoiGianTao = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.thoiGianCapNhat = LocalDateTime.now();
    }
}
