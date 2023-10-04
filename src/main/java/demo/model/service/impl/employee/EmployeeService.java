package demo.model.service.impl.employee;

import demo.model.dto.request.EmployeeRequest;
import demo.model.entity.Department;
import demo.model.entity.Employee;
import demo.model.entity.Role;
import demo.model.repository.IDepartmentRepository;
import demo.model.repository.IEmployeeRepository;
import demo.model.service.impl.department.IDepartmentService;
import demo.model.service.impl.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService<EmployeeRequest,Long> {
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;

    @Override
    public Page<Employee> findAll(int page,int size, String name) {
        employeeRepository.findAllByNameContains( name, PageRequest.of(page, size));
        return employeeRepository.findAllByNameContains( name, PageRequest.of(page, size));
    }

    @Override
    public Employee findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        return null;
    }
    @Override
    public Employee save(EmployeeRequest request) {
        Department department = departmentService.findById(request.getDepartmentId());
        List<Role> roles = new ArrayList<>();
        for (Long id:request.getRoleId()) {
            if(roleService.findById(id)!=null) {
                roles.add(roleService.findById(id));
            }
        }

        Employee employee = Employee.builder()
                .name(request.getName())
                .email(request.getEmail())
                .department(department)
                .roles(roles).build();
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(EmployeeRequest request, Long id) {
        Department department = departmentService.findById(request.getDepartmentId());
        List<Role> roles = new ArrayList<>();
        for (Long roleId:request.getRoleId()) {
            if(roleService.findById(roleId)!=null) {
                roles.add(roleService.findById(id));
            }
        }

        Employee employee = Employee.builder()
                .id(id)
                .name(request.getName())
                .email(request.getEmail())
                .department(department)
                .roles(roles).build();
        return employeeRepository.save(employee);
    }

    @Override
    public Employee delete(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            employeeRepository.deleteById(id);
            return employee.get();
        }
        return null;
    }
}
