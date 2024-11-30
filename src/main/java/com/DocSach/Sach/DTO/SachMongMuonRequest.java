package com.DocSach.Sach.DTO;

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
public class SachMongMuonRequest {
    private Reader readerWish;
    private Sach sachWish;
}
