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
@Table(name="`luot_doc_sach`")
@Subselect("select id, ten_sach, so_luot_doc, tac_gia, nam_ra_mat from luot_doc_sach")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LuotDocSach {
    @Id
    private Long id;
    private String ten_sach;
    private Long so_luot_doc;
    private String nam_ra_mat;
    private String tac_gia;
}
