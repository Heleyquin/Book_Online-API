package com.DocSach.Sach.Entity;

import com.DocSach.Sach.Embeddable.Key_CT_Goi_Dang_Ky;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "CT_DANG_KY"
)

public class CT_Dang_Ky {
    @EmbeddedId
    @JsonIgnore
    private Key_CT_Goi_Dang_Ky id;

    @JsonIgnore
    @ManyToOne
    @MapsId("idDocGia")
    @JoinColumn(name = "Id_Docgia")
    private Reader readerDangKy;

    @JsonIgnore
    @ManyToOne
    @MapsId("maGoi")
    @JoinColumn(name = "MA_GOI")
    private GoiDangKy goiDangKy;

    private BigDecimal giaTien;

}
