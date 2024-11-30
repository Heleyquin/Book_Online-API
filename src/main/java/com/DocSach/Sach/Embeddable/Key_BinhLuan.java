package com.DocSach.Sach.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Key_BinhLuan implements Serializable {
    private Long idSach;

    @JsonProperty("Id_DocGia")
    private Long Id_DocGia;
    private LocalDateTime thoiGianTao;
}