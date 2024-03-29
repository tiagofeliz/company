package br.com.tiagofeliz.company.controller;

import br.com.tiagofeliz.company.model.dto.BudgetStatusDto;
import br.com.tiagofeliz.company.model.dto.DepartmentDto;
import br.com.tiagofeliz.company.model.dto.EmployeeDto;
import br.com.tiagofeliz.company.model.dto.ProjectDto;
import br.com.tiagofeliz.company.model.entity.Department;
import br.com.tiagofeliz.company.model.form.DepartmentForm;
import br.com.tiagofeliz.company.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> index(){
        List<DepartmentDto> departments = departmentService.findAll();
        if(departments.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> show(@PathVariable Long id){
        Optional<DepartmentDto> employee = departmentService.findById(id);
        if(!employee.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee.get());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DepartmentDto> create(@RequestBody @Valid DepartmentForm form, UriComponentsBuilder builder){
        Department department = form.toEntity();
        DepartmentDto dto = departmentService.create(department);

        URI uri = builder.path("/department/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DepartmentDto> update(@RequestBody @Valid DepartmentForm form, @PathVariable Long id){
        Department department = form.toEntity();
        return ResponseEntity.ok(departmentService.update(department, id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DepartmentDto> delete(@PathVariable Long id){
        departmentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<?> employees(@PathVariable Long id){
        List<EmployeeDto> departmentEmployees = departmentService.employees(id);
        if(departmentEmployees.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departmentEmployees);
    }

    @GetMapping("/{id}/budgetStatus")
    public ResponseEntity<List<BudgetStatusDto>> budgetStatus(@PathVariable Long id){
        List<BudgetStatusDto> departmentBudgetsStatus = departmentService.budgetStatus(id);
        if(departmentBudgetsStatus.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departmentBudgetsStatus);
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<ProjectDto>> projects(@PathVariable Long id){
        List<ProjectDto> departmentProjects = departmentService.projects(id);
        if(departmentProjects.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departmentProjects);
    }

}
