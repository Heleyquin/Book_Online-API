package com.DocSach.Sach.Entity;

import com.DocSach.Sach.Embeddable.Key_BinhLuan;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "BINHLUAN"
)
public class Cmt {
    @EmbeddedId
    private Key_BinhLuan id;

    @JsonIgnore
    @ManyToOne
    @MapsId("idSach")
    @JoinColumn(name = "Id_Sach")
    private Sach sachCmt;

    @ManyToOne
    @MapsId("Id_DocGia")
    @JoinColumn(name = "Id_Docgia")
    private Reader readerCmt;

    @Column(name = "NOIDUNG", nullable = false)
    private String noiDung;
}
