package com.DocSach.Sach.Controller;

import com.DocSach.Sach.Entity.Favor_Count_TL;
import com.DocSach.Sach.Service.Favor_Count_TlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/favor-count")
@RequiredArgsConstructor
@Slf4j
public class Favor_Count_Controller {
    final private Favor_Count_TlService favor_Count_TlService;
    @GetMapping
    public List<Favor_Count_TL> favor_count() {
        return favor_Count_TlService.getFavor_Count_TL();
    }
}
