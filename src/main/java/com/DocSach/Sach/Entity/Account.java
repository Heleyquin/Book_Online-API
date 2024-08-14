package com.DocSach.Sach.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "ACCOUNT"
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "TK", nullable = false)
    private String username;

    @Column(name = "MK", nullable = false)
    private String password;

    private int role;//0 QuanLy; 1 reader

    private boolean active = true;


}
