package demo.controller.api;

import demo.model.entity.Department;
import demo.model.service.impl.department.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> createDepart(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.save(department), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepart(@RequestBody Department department,@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.update(department,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Department> delete(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.delete(id),HttpStatus.OK);
    }
}
