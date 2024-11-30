package com.DocSach.Sach.DTO;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
    private List<LichSuMuaRequest> lichSuMuaRequests;
    private List<Long> wishIds;
}
