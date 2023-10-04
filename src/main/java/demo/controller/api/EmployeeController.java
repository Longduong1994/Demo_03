package demo.controller.api;

import demo.model.dto.request.EmployeeRequest;
import demo.model.entity.Employee;
import demo.model.service.impl.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @GetMapping
    public ResponseEntity<Page<Employee>> findAll(@RequestParam(defaultValue = "") String name,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "2") int size) {
        return new ResponseEntity<>(employeeService.findAll(page,size,name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createRole(@RequestBody EmployeeRequest request) {
        return new ResponseEntity<>(employeeService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateRole(@RequestBody EmployeeRequest request,@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.update(request,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.delete(id),HttpStatus.OK);
    }
}
