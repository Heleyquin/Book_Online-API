package com.DocSach.Sach.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Immutable
@Table(name="`luot_doc_doc_gia`")
@Subselect("select id, ho, ten, email, tk, so_luot_doc, ngay_tao  from luot_doc_doc_gia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LuotDocDocGia {
    @Id
    private Long id;
    private String ho;
    private String ten;
    private String email;
    private String tk;
    private Long so_luot_doc;
    private LocalDateTime ngay_tao;
}
