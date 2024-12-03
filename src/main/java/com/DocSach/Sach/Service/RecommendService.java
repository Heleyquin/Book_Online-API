package com.DocSach.Sach.Service;

import com.DocSach.Sach.DTO.SachResponse;
import com.DocSach.Sach.Entity.Sach;
import com.DocSach.Sach.Responsitory.SachResponsi;
import com.DocSach.Sach.config.BookMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendService {
    private final SachResponsi sachResponsi;

    @Value("${python.script.path}")
    private String pythonScriptPath;
    private final RestTemplate restTemplate;
    private final BookMap bookMap = new BookMap();
    public ResponseEntity<?> getRecommends(Long bookId) throws Exception {
        Long index_book = bookMap.bookDict.get(bookId);
        String url = "http://localhost:5000/recommendations?book_id=" + index_book;  // URL của server Python
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, List.class);

        List<String> listIdBookString = response.getBody();
        List<Long> recommendations = new ArrayList<>();
        for(String s:listIdBookString){
            recommendations.add(Long.parseLong(s));
        }
        List<Sach> recommendList = new ArrayList<>();
        for(int i=0; i<recommendations.size();i++){
            recommendList.add(sachResponsi.getReferenceById(recommendations.get(i)));
        }
        return ResponseEntity.ok(recommendList.stream().map(this::mapToSachResponse).toList());
    }
    private SachResponse mapToSachResponse(Sach sach) {
        return SachResponse.builder()
                .tenSach(sach.getTenSach())
                .id(sach.getId())
                .nxb(sach.getNXB())
                .ngayTao(sach.getNgayTao())
                .ngayUpdate(sach.getNgayUpdate())
                .ngayRaMat(sach.getNgayRaMat())
                .active(sach.isActive())
                .urlImg(sach.getUrlImg())
                .giaTien(sach.getGiaTien())
                .urlFile(sach.getUrlFile())
                .gioiThieu(sach.getGioiThieu())
                .idQuanLy(sach.getQuanLy())
                .tacGias(sach.getTacGiaList())
                .theLoaiSet(sach.getTheLoaiList())
                .cmts(sach.getCmtSet())
                .favors(sach.getFavorList())
                .build();
    }

}
