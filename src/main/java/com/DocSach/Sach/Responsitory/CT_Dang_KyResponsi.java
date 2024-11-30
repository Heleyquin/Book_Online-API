package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Embeddable.Key_CT_Goi_Dang_Ky;
import com.DocSach.Sach.Entity.CT_Dang_Ky;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CT_Dang_KyResponsi extends JpaRepository<CT_Dang_Ky, Key_CT_Goi_Dang_Ky> {
    List<CT_Dang_Ky> findAllByReaderDangKyId(Long id);
}
