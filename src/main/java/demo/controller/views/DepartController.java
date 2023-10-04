package demo.controller.views;

import demo.model.entity.Department;
import demo.model.service.impl.department.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/department")
public class DepartController {
    @Autowired
    private IDepartmentService departmentService;
    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("list", departmentService.findAll());
        return "department/list";
    }

    @GetMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("department/create","department", new Department());
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute Department department ,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "department/create";
        }
        departmentService.save(department);
        return  "redirect:/department";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id){
        return new ModelAndView("department/edit", "department", departmentService.findById(id));
    }

    @PostMapping("/update")
    public String doUpdate(@Valid @ModelAttribute Department department ,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "department/edit";
        }
        departmentService.update(department,department.getId());
        return "redirect:/department";
    }

    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable Long id){
        departmentService.delete(id);
        return "redirect:/department";
    }

}
