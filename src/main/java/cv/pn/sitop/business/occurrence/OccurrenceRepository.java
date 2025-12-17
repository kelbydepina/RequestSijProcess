package cv.pn.sitop.business.occurrence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OccurrenceRepository extends JpaRepository<Occurrence, String> {

    List<Occurrence> findByReportId(String relatorioId);

   // long countByReport_Id(String id);

    long countByReportId(String reportId);
}
