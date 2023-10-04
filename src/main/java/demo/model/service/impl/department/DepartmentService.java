package demo.model.service.impl.department;

import demo.model.entity.Department;
import demo.model.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService{

    @Autowired
    private IDepartmentRepository departmentRepository;
    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return department.get();
        }
        return null;
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Department department, Long id) {
        department.setId(id);
        return departmentRepository.save(department);
    }

    @Override
    public Department delete(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()){
            departmentRepository.deleteById(id);
            return department.get();
        }
        return null;
    }
}
