package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Entity.Sach_Mong_Muon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SachMongMuonResponsi extends JpaRepository<Sach_Mong_Muon, Long> {
    List<Sach_Mong_Muon> findAllByReaderWishId(long id);
    Sach_Mong_Muon findSach_Mong_MuonByReaderWishIdAndSachWishId(long readerId, long sachId);
}
