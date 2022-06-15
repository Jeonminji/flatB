package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.OttEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OttDto {
    String ottName;

    String ottLogo;

    public OttDto(OttEntity ottEntity) {
        this.ottName = ottEntity.getOttName();
        this.ottLogo = ottEntity.getOttLogo();
    }

    public static OttDto ofEntity(OttEntity ottEntity) {
        return new OttDto(ottEntity);
    }
}
