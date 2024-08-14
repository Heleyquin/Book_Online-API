package com.DocSach.Sach.DTO;

import com.DocSach.Sach.Entity.Sach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheLoaiResponse {
    private long id;
    private String tenTL;
    private Set<Sach> sachs;
}
