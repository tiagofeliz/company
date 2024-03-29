package br.com.tiagofeliz.company.model.form;

import br.com.tiagofeliz.company.model.entity.Budget;
import br.com.tiagofeliz.company.model.entity.Department;
import br.com.tiagofeliz.company.model.entity.Project;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentForm {

    @NotNull @NotEmpty
    private String name;
    private List<Project> projects;
    private List<Budget> budgets;

    public Department toEntity(){
        Department department = new Department();
        department.setName(this.name);
        department.setBudgets(this.budgets);
        department.setProjects(this.projects);
        return department;
    }

}
