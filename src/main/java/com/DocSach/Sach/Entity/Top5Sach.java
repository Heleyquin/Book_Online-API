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

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name="`top_5_sach_doc_nhieu`")
@Subselect("select id, nxb, img , active, gia_tien, gioi_thieu, ngay_ra_mat, ngay_tao, ngay_update, ten_sach, url_file, id_quan_ly, so_luot from top_5_sach_doc_nhieu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Top5Sach {
    @Id
    private Long id;
    private String nxb;
    private String img;
    private boolean active;
    private BigDecimal gia_tien;
    private String gioi_thieu;
    private LocalDate ngay_ra_mat;
    private LocalDate ngay_tao;
    private LocalDate ngay_update;
    private String ten_sach;
    private String url_file;
    private Long id_quan_ly;
    private Long so_luot;
}
