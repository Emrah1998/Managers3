package biz.managers3.managers3.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateManagersRequest {
    private String name;
    private String surname;
    private Integer salary;
}
