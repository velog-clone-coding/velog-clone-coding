package velog.sideProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.controller.dto.SearchSeriesDTO;
import velog.sideProject.entity.series.Series;
import velog.sideProject.exception.exception.VelogNotFoundException;
import velog.sideProject.service.SeriesService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/series")
public class SeriesController {

    private Long memberId = 1L;

    private final SeriesService seriesService;

    /**
     * select SeriesList with member_id
     **/
    @GetMapping("")
    public List<SearchSeriesDTO> getSeriesList() {
        log.info("getSeriesList request");

        return seriesService.getSeriesListWithId(memberId);


    }
}
