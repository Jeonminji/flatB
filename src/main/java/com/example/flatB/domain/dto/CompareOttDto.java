package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.CompareOttEntity;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompareOttDto {
    private String ottName;

    private String ottPlan;

    private String ottPrice;

    private String ottEtc;

    private String ottPlaybacknum;

    private String ottQuality;

    public CompareOttEntity toEntity() {
        CompareOttEntity compareOttEntity = CompareOttEntity.builder()
                .ottName(ottName)
                .ottPlan(ottPlan)
                .ottPrice(ottPrice)
                .ottEtc(ottEtc)
                .ottPlaybacknum(ottPlaybacknum)
                .ottQuality(ottQuality)
                .build();
        return compareOttEntity;
    }
}
