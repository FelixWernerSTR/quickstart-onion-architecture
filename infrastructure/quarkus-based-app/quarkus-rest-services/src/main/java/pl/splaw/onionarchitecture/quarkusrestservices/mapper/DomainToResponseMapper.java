package pl.splaw.onionarchitecture.quarkusrestservices.mapper;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.quarkusrestservices.dto.worklog.WorkLogResponse;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@ApplicationScoped
public class DomainToResponseMapper {
  
  public WorkLogResponse mapToWorkLogResponse(WorkLog workLog) {
    return new WorkLogResponse(workLog.getWorkLogId(), workLog.getStartDate(), workLog.getTimeSpentInSeconds(), workLog.getAssociatedWorker(),
        workLog.getDescription());
  }
  
  public List<WorkLogResponse> mapToWorkLogResponseList(List<WorkLog> workLogList) {
    return workLogList.stream().map((workLog) -> mapToWorkLogResponse(workLog)).collect(Collectors.toList());
  }
}
