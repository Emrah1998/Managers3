package biz.managers3.managers3.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagersResponse {
    private String name;
    private String surname;
    private Integer salary;
    private LocalDate hireDate;
    private String department;
}
