package demo.model.service.impl.role;

import demo.model.entity.Role;
import demo.model.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()){
            return role.get();
        }
        return null;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role, Long id) {
        role.setId(id);
        return roleRepository.save(role);
    }

    @Override
    public Role delete(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()){
            roleRepository.deleteById(id);
            return role.get();
        }
        return null;
    }
}
