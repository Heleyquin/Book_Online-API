package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheLoaiResponsi extends JpaRepository<TheLoai, Long> {
    boolean existsByTenTheLoai(String tenTL);

}
