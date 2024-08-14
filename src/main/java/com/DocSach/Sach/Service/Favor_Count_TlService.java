package com.DocSach.Sach.Service;

import com.DocSach.Sach.Entity.Favor_Count_TL;
import com.DocSach.Sach.Responsitory.Favor_Count_TLReponsi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Favor_Count_TlService {
    final private Favor_Count_TLReponsi favor_Count_TLReponsi;
    public List<Favor_Count_TL> getFavor_Count_TL() {
        List<Favor_Count_TL> favorCountTls = favor_Count_TLReponsi.findAll();
        return favorCountTls;
    }
}
