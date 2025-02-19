package biz.managers3.managers3.controller;

import biz.managers3.managers3.model.request.CreateManagersRequest;
import biz.managers3.managers3.model.request.UpdateSalaryRequest;
import biz.managers3.managers3.model.response.ManagersResponse;
import biz.managers3.managers3.service.ManagersServiceHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/managers3")
public class ManagersController {
    private final ManagersServiceHandle managersService;

    @PostMapping
    public void saveManager(@RequestBody CreateManagersRequest request) {
        managersService.saveManager(request);
    }

    @DeleteMapping("/{id}")
    public void deleteManager(@PathVariable Long id) {
        managersService.deleteManager(id);
    }

    @PatchMapping("/{id}/salary")
    public void updateManagerSalary(@PathVariable Long id,
                                    @RequestBody UpdateSalaryRequest salary) {
        managersService.updateManagerSalary(id,salary);
    }

    @GetMapping("/{id}")
    public ManagersResponse getManagers(@PathVariable Long id) {
        return managersService.getManagers(id);
    }
}
