package biz.managers3.managers3.dao.repository;

import biz.managers3.managers3.dao.entity.ManagersEntity;
import biz.managers3.managers3.model.enums.ManagersStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ManagersRepository extends CrudRepository<ManagersEntity,Long> {

    Optional<ManagersEntity> findByIdAndStatusNot(Long id, ManagersStatus status);
}
