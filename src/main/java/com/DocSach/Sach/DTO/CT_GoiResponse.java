package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Embeddable.Key_CT_Goi;
import com.DocSach.Sach.Entity.GoiDangKy;
import com.DocSach.Sach.Entity.Sach;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CT_GoiResponse {
    private Key_CT_Goi id;
    private Sach sach;
    private GoiDangKy goiDangKy;
    private LocalDateTime thoiGian;
}
