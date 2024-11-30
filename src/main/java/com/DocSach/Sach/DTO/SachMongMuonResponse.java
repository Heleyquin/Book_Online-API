package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.Reader;
import com.DocSach.Sach.Entity.Sach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SachMongMuonResponse {
    private Long id;
    private Reader readerWish;
    private Sach sachWish;
}
