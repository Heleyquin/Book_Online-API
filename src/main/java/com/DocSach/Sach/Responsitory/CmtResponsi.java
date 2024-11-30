package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Embeddable.Key_BinhLuan;
import com.DocSach.Sach.Entity.Cmt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CmtResponsi extends JpaRepository<Cmt, Key_BinhLuan> {
    List<Cmt> findAllByIdIdSach(Long id);
}
