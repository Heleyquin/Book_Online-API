package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Entity.Account;
import com.DocSach.Sach.Entity.QuanLy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuanLyResponsi extends JpaRepository<QuanLy, Long> {
    QuanLy findByAccount(Account account);
}
