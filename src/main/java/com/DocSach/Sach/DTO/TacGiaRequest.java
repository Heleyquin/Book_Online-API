package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.Sach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TacGiaRequest {
    private String tenTG;
    private String hoTG;
    private boolean gioiTinh;
    private LocalDateTime ngaySinh;
    private String diaChi;
    private Set<Sach> sachs;
}
