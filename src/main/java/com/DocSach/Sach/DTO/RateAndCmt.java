package com.DocSach.Sach.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RateAndCmt{
    private DanhGiaRequest danhGiaRequest;
    private CmtRequest cmtRequest;

}
