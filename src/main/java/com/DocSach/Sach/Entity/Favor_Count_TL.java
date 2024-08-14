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
@Table(name="`favor_book_count`")
@Subselect("select id, ten_sach,favor_count  from favor_book_count")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favor_Count_TL {
    @Id
    private Long id;
    private String tenSach;
    private Long favor_count;
}
