package cv.pn.sitop.business.occurrenceData;

import cv.pn.sitop.business.occurrence.OccurrenceDto;
import cv.pn.sitop.utilities.APIResponse;

import java.util.List;

public interface IOccurrenceDataService {

    APIResponse saveOccurrence(OccurrenceDto dto);

    APIResponse getAllOccurrence();

   // void saveAll(List<OccurrenceData> dataList);

    void save(OccurrenceData data);
}
