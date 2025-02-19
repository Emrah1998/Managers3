package biz.managers3.managers3.dao.entity;

import biz.managers3.managers3.model.enums.ManagersStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "managers3")
@Builder
public class ManagersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer salary;
    private LocalDate hire_Date;
    private String department;
    @Enumerated(EnumType.STRING)
    private ManagersStatus status;

}
