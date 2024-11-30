package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Entity.LichSuMua;
import com.DocSach.Sach.Entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LichSuMuaResponsi extends JpaRepository<LichSuMua, Long> {
    List<LichSuMua> findAllByReaderBuyId(long id);
}
