package br.com.hotmart.company.model.form;

import br.com.hotmart.company.model.entity.Department;
import br.com.hotmart.company.model.entity.Project;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProjectForm {

    @NotNull @NotEmpty
    private String name;
    private double value;
    private LocalDate startDate;
    private LocalDate endDate;
    private Department department;

    private Long idDepartment;

    public Project toEntity(){
        Project project = new Project();
        project.setName(this.name);
        project.setValue(this.value);
        project.setStartDate(this.startDate);
        project.setEndDate(this.endDate);
        if(this.idDepartment != null) {
            Department department = new Department();
            department.setId(this.idDepartment);
            project.setDepartment(department);
        }else{
            throw new IllegalArgumentException("Department id is required");
        }
        return project;
    }

}
