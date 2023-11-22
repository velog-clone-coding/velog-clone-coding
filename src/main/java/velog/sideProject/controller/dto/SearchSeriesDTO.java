package velog.sideProject.controller.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import velog.sideProject.entity.series.Series;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class SearchSeriesDTO {

    private final String typeName = "SearchSeriesDTO";
    private Long seriesId;
    private String seriesName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static SearchSeriesDTO toDTO(Series series) {
        return SearchSeriesDTO.builder()
                .seriesId(series.getSeriesId())
                .seriesName(series.getSeries())
                .createdAt(series.getCreatedAt())
                .modifiedAt(series.getModifiedAt())
                .build();
    }

    @Builder
    public SearchSeriesDTO(Long seriesId, String seriesName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
