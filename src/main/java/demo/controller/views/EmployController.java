package demo.controller.views;

import demo.model.dto.request.EmployeeRequest;
import demo.model.entity.Employee;
import demo.model.service.impl.department.IDepartmentService;
import demo.model.service.impl.employee.IEmployeeService;
import demo.model.service.impl.role.IRoleService;
import demo.model.service.mapper.employee.IEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IEmployeeMapper employeeMapper;

    @GetMapping()
    public String findAll(Model model, @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size,@RequestParam(defaultValue = "") String name ){
        model.addAttribute("employees",employeeService.findAll(page,size,name));
        model.addAttribute("name",name);
        return "employee/list";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("employeeRequest", new EmployeeRequest());
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("department",departmentService.findAll());
        return  "employee/create";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute EmployeeRequest employeeRequest, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("department", departmentService.findAll());
            return "/employee/create";
        }
        employeeService.save(employeeRequest);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,@PathVariable Long id){
        EmployeeRequest employeeRequest = (EmployeeRequest) employeeMapper.toRequest(employeeService.findById(id));
        model.addAttribute("employeeRequest", employeeRequest);
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("department",departmentService.findAll());
        return  "employee/edit";
    }

    @PostMapping("/update")
    public String doUpdate(@Valid @ModelAttribute EmployeeRequest employeeRequest,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "employee/edit";
        }
        employeeService.update(employeeRequest,employeeRequest.getId());
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        employeeService.delete(id);
        return "redirect:/employee";
    }
}
