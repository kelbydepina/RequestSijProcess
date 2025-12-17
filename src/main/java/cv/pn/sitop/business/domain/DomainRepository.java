package cv.pn.sitop.business.domain;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, String> {

    List<Domain> findAllByDomainAndSelfCode(String domain, String selfCode);
}
