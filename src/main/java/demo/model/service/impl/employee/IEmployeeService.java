package demo.model.service.impl.employee;


import demo.model.entity.Employee;
import org.springframework.data.domain.Page;



public interface IEmployeeService<T,E>{
    Page<Employee> findAll(int page,int size, String name);
    Employee findById(Long id);
    Employee save(T request);
    Employee update(T request,E id);
    Employee delete(Long id);
}
