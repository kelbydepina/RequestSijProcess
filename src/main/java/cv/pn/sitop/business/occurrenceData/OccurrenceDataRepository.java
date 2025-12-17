package cv.pn.sitop.business.occurrenceData;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OccurrenceDataRepository extends JpaRepository<OccurrenceData, String> {

    List<OccurrenceData> findAllByDescriptionAndSelfCode(String description, String selfCode);

    List<OccurrenceData> findByOccurrenceId(String id);

    // List<OccurrenceData> findAllByDomainAndSelfCode(String domain, String selfCode);
}
