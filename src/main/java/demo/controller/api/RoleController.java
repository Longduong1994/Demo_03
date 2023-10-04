package demo.controller.api;

import demo.model.entity.Role;
import demo.model.service.impl.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id) {
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return new ResponseEntity<>(roleService.save(role), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role,@PathVariable Long id) {
        return new ResponseEntity<>(roleService.update(role,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Role> delete(@PathVariable Long id){
        return new ResponseEntity<>(roleService.delete(id),HttpStatus.OK);
    }
}
