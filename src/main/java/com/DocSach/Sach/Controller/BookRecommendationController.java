package com.DocSach.Sach.Controller;

import com.DocSach.Sach.Service.RecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
@Slf4j
public class BookRecommendationController {
    private final RecommendService recommendService;
    @GetMapping("/get-recommendations/{id}")
    public ResponseEntity<?> getRecommendations(@PathVariable("id") Long id) throws Exception {
        return recommendService.getRecommends(id);
    }
}
