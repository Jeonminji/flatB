package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.ReviewOttEntity;
import com.example.flatB.domain.entity.UserEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewOttSaveDto { //save request
    private String ottPlatformname;

    private BigDecimal ottPricepoints;

    private BigDecimal ottQualitypoints;

    private BigDecimal ottTranslationpoints;

    private BigDecimal ottServicepoints;

    private BigDecimal ottTotalpoints;

    private String ottContent;

    private UserEntity userEntity;

    private LocalDateTime ottRegdate;

    public ReviewOttEntity toEntity() {
        ReviewOttEntity reviewOttEntity = ReviewOttEntity.builder()
                .ottPlatformname(ottPlatformname)
                .ottPricepoints(ottPricepoints)
                .ottQualitypoints(ottQualitypoints)
                .ottTranslationpoints(ottTranslationpoints)
                .ottServicepoints(ottServicepoints)
                .ottTotalpoints(ottTotalpoints)
                .ottContent(ottContent)
                .userEntity(userEntity)
                .ottRegdate(LocalDateTime.now())
                .build();
        return reviewOttEntity;
    }
}
