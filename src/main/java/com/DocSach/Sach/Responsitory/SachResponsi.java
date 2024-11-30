package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Entity.Cmt;
import com.DocSach.Sach.Entity.Sach;
import com.DocSach.Sach.Entity.TacGia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SachResponsi extends JpaRepository<Sach, Long> {
    boolean existsByTenSach(String tenSach);
}
