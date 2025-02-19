package biz.managers3.managers3.service;

import biz.managers3.managers3.model.request.CreateManagersRequest;
import biz.managers3.managers3.model.request.UpdateSalaryRequest;
import biz.managers3.managers3.model.response.ManagersResponse;

public interface ManagersService {
    void saveManager(CreateManagersRequest request);
    void deleteManager(Long id);
    void updateManagerSalary(Long id, UpdateSalaryRequest salary);
    ManagersResponse getManagers(Long id);
}
