package velog.sideProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import velog.sideProject.controller.dto.SearchSeriesDTO;
import velog.sideProject.entity.series.Series;
import velog.sideProject.repository.series.jpa.SeriesRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;

    public List<SearchSeriesDTO> getSeriesListWithId(Long memberId){

        List<Series> seriesList = seriesRepository.findByMember_MemberId(memberId);

        return seriesList.stream().map(SearchSeriesDTO::toDTO).toList();
    }
}
