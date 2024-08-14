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

@Entity
@Immutable
@Table(name="`sach_sld")
@Subselect("select id_sach, gia_tien, active, gioi_thieu, id_quan_ly, ngay_ra_mat, ngay_tao, ngay_update, img, nxb, url_file, so_luot_doc, favor_count from sach_sld")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sach_SLD {
    @Id
    private Long id_sach;
    private String gia_tien;
    private String active;
    private String gioi_thieu;
    private Long id_quan_ly;
    private String ngay_ra_mat;
    private String ngay_tao;
    private String ngay_update;
    private String img;
    private String nxb;
    private String url_file;
    private String so_luot_doc;
    private String favor_count;
}

