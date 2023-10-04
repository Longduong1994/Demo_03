package demo.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequest {
    private Long id;
    @NotBlank(message = "Name Not  Blank")
    private String name;
    @Pattern(regexp = "^(.+)@(\\S+)$",message = "Email is not valid")
    private String email;
    private Long departmentId;
    private List<Long> roleId;
}
