package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Embeddable.Key_DanhGia;
import com.DocSach.Sach.Entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanhGiaResponsi extends JpaRepository<DanhGia, Key_DanhGia> {
    List<DanhGia> findAllByIdIdSach(long id);
    DanhGia findDanhGiaById(Key_DanhGia keyDanhGia);
}
