package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Embeddable.Key_BinhLuan;
import com.DocSach.Sach.Entity.Reader;
import com.DocSach.Sach.Entity.Sach;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CmtRequest {
    private Key_BinhLuan id;
    private String noiDung;
}
