package com.DocSach.Sach.Responsitory;

import com.DocSach.Sach.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountResponsi extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    boolean existsByUsername(String username);
    Account getReferenceByUsername(String username);
}
