package cv.pn.sitop.business.report;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, String> {

    boolean existsByStatusCodeAndStatusDescription(String statusCode, String statusDescription);
}
