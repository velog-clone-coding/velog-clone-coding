package velog.sideProject.repository.series.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.series.Series;

import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {

    Optional<Series> findBySeriesId(Long seriesId);
}
