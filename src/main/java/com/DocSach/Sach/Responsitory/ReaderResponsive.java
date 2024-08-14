package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Entity.Account;
import com.DocSach.Sach.Entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReaderResponsive extends JpaRepository<Reader, Long> {
    Reader findByAccount(Account account);

    List<Reader> findByNgayTaoAfter(LocalDateTime ngayTao);
}
