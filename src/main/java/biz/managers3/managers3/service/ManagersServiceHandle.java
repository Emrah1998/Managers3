package biz.managers3.managers3.service;

import biz.managers3.managers3.dao.entity.ManagersEntity;
import biz.managers3.managers3.dao.repository.ManagersRepository;
import biz.managers3.managers3.exception.NotFoundException;
import biz.managers3.managers3.model.request.CreateManagersRequest;
import biz.managers3.managers3.model.request.UpdateSalaryRequest;
import biz.managers3.managers3.model.response.ManagersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static biz.managers3.managers3.exception.ExceptionConstants.*;
import static biz.managers3.managers3.model.enums.ManagersStatus.ACTIVE;
import static biz.managers3.managers3.model.enums.ManagersStatus.INACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagersServiceHandle implements ManagersService{

    private final ManagersRepository managersRepository;

    @Override
    public void saveManager(CreateManagersRequest request) {
        managersRepository.save(
                ManagersEntity.builder()
                        .name(request.getName())
                        .surname(request.getSurname())
                        .salary(request.getSalary())
                        .hire_Date(LocalDate.now())
                        .status(ACTIVE)
                        .build());
    }

    @Override
    public void deleteManager(Long id) {
       var manager = fetchManagersIfExist(id);
       manager.setStatus(INACTIVE);
       managersRepository.save(manager);
    }

    @Override
    public void updateManagerSalary(Long id, UpdateSalaryRequest salary) {
        var manager = fetchManagersIfExist(id);
        manager.setSalary(salary.getSalary());
        managersRepository.save(manager);
    }
    @Cacheable("getManagers")
    @Override
    public ManagersResponse getManagers(Long id) {
        var manager = fetchManagersIfExist(id);
        return new ManagersResponse(manager.getName(), manager.getSurname(), manager.getSalary(), manager.getHire_Date(), manager.getDepartment());
    }

    @CacheEvict(value = "getManagers", allEntries = true)
    public void deleteCache(){
    }

    @CachePut("getManagers")
    public ManagersResponse updateCache(Long id){
        return getManagers(id);
    }

    private ManagersEntity fetchManagersIfExist(Long id) {
        return managersRepository.findByIdAndStatusNot(id, INACTIVE)
                .orElseThrow(()-> {log.error("ManagersLog.fetchManagersIfExist.error manager with id: {} not found", id);
                    return new NotFoundException(String.format(MANAGER_NOT_FOUND_MESSAGE,id),
                            MANAGER_NOT_FOUND_CODE);
                });
    }

    public void changeManagerStatus(Long id){
        var manager = fetchManagersIfExist(id);
        manager.setStatus(manager.getStatus().toggle());
        managersRepository.save(manager);
    }

}
