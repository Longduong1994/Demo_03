package demo.model.service.mapper.employee;

import demo.model.dto.request.EmployeeRequest;
import demo.model.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class EmployeeMapper implements IEmployeeMapper<Employee, EmployeeRequest> {
    @Override
    public EmployeeRequest toRequest(Employee employee) {
        return EmployeeRequest.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .departmentId(employee.getDepartment().getId())
                .roleId(employee.getRoles().stream().map(r->r.getId()).collect(Collectors.toList())).build();
    }
}
