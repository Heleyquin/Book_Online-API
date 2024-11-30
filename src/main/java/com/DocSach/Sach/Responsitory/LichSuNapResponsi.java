package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Entity.LichSuNap;
import com.DocSach.Sach.Entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LichSuNapResponsi extends JpaRepository<LichSuNap, Long> {
    List<LichSuNap> findAllByReader(Reader reader);
}
