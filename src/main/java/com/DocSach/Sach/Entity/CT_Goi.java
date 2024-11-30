package com.DocSach.Sach.Entity;

import com.DocSach.Sach.Embeddable.Key_CT_Goi;
import com.DocSach.Sach.Embeddable.Key_CT_Goi_Dang_Ky;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "CT_GOI"
)
public class CT_Goi {
    @EmbeddedId
    @JsonIgnore
    private Key_CT_Goi id;

    @ManyToOne
    @MapsId("idSach")
    @JoinColumn(name = "Id_Sach")
    private Sach sach;

    @JsonIgnore
    @ManyToOne
    @MapsId("maGoi")
    @JoinColumn(name = "MA_GOI")
    private GoiDangKy goiDetail;

    @CreationTimestamp
    private LocalDateTime thoiGian;

}
