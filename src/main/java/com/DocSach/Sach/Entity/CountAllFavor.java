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
@Table(name="`count_all_favor`")
@Subselect("select id, ten_sach, tac_gia, nam_ra_mat, favor_count  from count_all_favor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountAllFavor {
    @Id
    private Long id;
    private String tenSach;
    private Long favor_count;
    private String nam_ra_mat;
    private String tac_gia;
}
