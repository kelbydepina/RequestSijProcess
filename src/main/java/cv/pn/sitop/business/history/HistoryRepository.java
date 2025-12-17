package cv.pn.sitop.business.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HistoryRepository extends JpaRepository<History, String> {
    List<History> findByReportIdOrderByDateTimeDesc(String reportId);

    Page<History> findAll(Pageable pageable);

    //List<History> findByReportIdOrderByDateTimeDesc(String reportId);

    //List<History> findByReportIdOrderByCreatedAtDesc(String reportId);
}
