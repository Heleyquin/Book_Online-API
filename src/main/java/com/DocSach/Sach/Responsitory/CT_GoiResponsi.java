package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Embeddable.Key_CT_Goi;
import com.DocSach.Sach.Entity.CT_Goi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CT_GoiResponsi extends JpaRepository<CT_Goi, Key_CT_Goi> {
}
