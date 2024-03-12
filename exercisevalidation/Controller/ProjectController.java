package com.example.exercisevalidation.Controller;

import java.util.ArrayList;
import java.util.Objects;

import com.example.exercisevalidation.APIResponse.APIResponse;
import com.example.exercisevalidation.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    /* Create project tracker system , Where you can get all the project ,
add , remove , update project.
project Class : ID , title , description , status, companyName
• Create a new project (ID,title , description , status, companyName)
• Display all project .
• Update a project
• Delete a project
• Change the project status as done or not done
• Search for a project by given title
• Display All project for one company by companyName*/


    //• Create a new project (ID,title , description , status, companyName)
    ArrayList<Project> projects = new ArrayList<Project>();

    @PostMapping("/addProject")
    public ResponseEntity addProject(@RequestBody @Valid Project project,Errors errors) {
        if (errors.hasErrors()){
            String info=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(info);
        }
        String status=project.getStatus().replace(" ","");
        project.setStatus(status);
        projects.add(project);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Project " + project.getTitle() + " Added successfully"));

    }

    //• Display all project .
    @GetMapping("getProjects")
    public ResponseEntity getAllProject() {
        if (projects.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Array empty"));

        }
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    //• Update a project
    @PutMapping("/updated/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String info = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(info);
        }
        if (index < projects.size()) {
            String status=project.getStatus().replace(" ","");
            project.setStatus(status);
            projects.set(index, project);
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Project is updated successfully"));
        }
        return ResponseEntity.status(400).body(new APIResponse("INDEX NOT VALID"));

    }

    //• Delete a project
    @DeleteMapping("/delete/{index}")
    public ResponseEntity delete(@PathVariable int index) {
        if (index < projects.size()) {
            String info = projects.get(index).getTitle();
            projects.remove(index);

            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Project " + info + "is deleted successfully"));

        }
        return ResponseEntity.status(400).body(new APIResponse("INDEX NOT VALID"));
    }

    //• Change the project status as done or not done
    @PutMapping("/change/{index}")
    public ResponseEntity change(@PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            String info = Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
            return ResponseEntity.status(400).body(info);
        }
        if (index < projects.size()) {
            if (projects.get(index).getStatus().equalsIgnoreCase("NotStarted")) {
                projects.get(index).setStatus("Progress");
            } else if (projects.get(index).getStatus().equalsIgnoreCase("Progress")) {
                projects.get(index).setStatus(" Completed");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Its Already  Completed"));
            }

            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Project " + projects.get(index).getTitle()+projects.get(index).getStatus()));
        } else return ResponseEntity.status(400).body(new APIResponse("INDEX NOT VALID"));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity searchProject(@PathVariable String title) {
        title = title.replace(" ", "");
        for (Project project : projects) {
            if (project.getTitle().equalsIgnoreCase(title))
                return ResponseEntity.status(HttpStatus.OK).body(project);
        }
        return ResponseEntity.status(400).body(new APIResponse("No found this title" + title));
    }


    //• Display All project for one company by companyName.
    @GetMapping("/displayProjectCompany/{name}")
    public ResponseEntity displayProjectsCompany(@PathVariable String name) {
        ArrayList<Project> projectsCompany = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equalsIgnoreCase(name)) {
                projectsCompany.add(project);
            }
        }
        if (projectsCompany.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Not found "));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(projectsCompany);
        }
    }

}



