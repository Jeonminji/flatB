package com.example.flatB.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "ott")
public class OttEntity {
    @Id
    private String ottName;

    private String ottLogo;

    @Builder
    public OttEntity(String ottName, String ottLogo) {
        this.ottName = ottName;
        this.ottLogo = ottLogo;
    }
}
