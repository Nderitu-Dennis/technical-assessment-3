package tech.csm.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tech.csm.model.Department;
import tech.csm.model.Employee;
import tech.csm.model.Project;
import tech.csm.model.ProjectAssignment;
import tech.csm.model.Team;
import tech.csm.service.DepartmentService;
import tech.csm.service.EmployeeService;
import tech.csm.service.ProjectAssignmentService;
import tech.csm.service.ProjectService;
import tech.csm.service.TeamService;

@Controller
public class ProjectController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectAssignmentService projectAssignmentService;

    // Project Assignment form
    @GetMapping("/project-assignment-form")
    public String getProjectAssignmentForm(Model model) {
        model.addAttribute("departments", departmentService.getActiveDepartments());
        model.addAttribute("employees", employeeService.getActiveEmployees());
        model.addAttribute("assignment", new ProjectAssignment());
        return "project-assignment-form";
    }

    // 2. Get Teams by Department
    @GetMapping("/teams-by-department-id")
    @ResponseBody
    public List<Team> getTeamsByDepartment(@RequestParam("departmentId") Integer departmentId) {
        return teamService.getTeamsByDepartment(departmentId);
    }

    // 3. Get Projects by Team only active & bill_able
    @GetMapping("/projects-by-team-id")
    @ResponseBody
    public List<Project> getProjectsByTeam(@RequestParam("teamId") Integer teamId) {
        return projectService.getBillableProjectsByTeam(teamId);
    }

    // 4. Save Project Assignment
    @PostMapping("/save-assignment")
    public String saveProjectAssignment(@ModelAttribute ProjectAssignment assignment, RedirectAttributes rd) {
        ProjectAssignment saved = projectAssignmentService.saveAssignment(assignment);
        String msg = "Project Assignment saved with id : " + saved.getAssignmentId();
        rd.addFlashAttribute("msg", msg);
        return "redirect:./project-assignment-form";
    }
    
    //get all assigned projects
    @GetMapping("/get-assigned-projects-list")
    public String getProjectsAssigned(Model model) {
        List<ProjectAssignment> assignments = projectAssignmentService.getAllAssignedProjects();
        if (assignments == null) {
            assignments = Collections.emptyList();
        }
        model.addAttribute("projectsAssigned", assignments);  //variable names shld not contain dashes
        return "assigned-projects-list";
    }


    // 5. Delete Project Assignment
    @GetMapping("/delete-assignment")
    public String deleteProjectAssignment(@RequestParam("assignmentId") Integer assignmentId, RedirectAttributes rd) {
        projectAssignmentService.deleteAssignment(assignmentId);
        rd.addFlashAttribute("msg", "Project deleted successfully!");
        return "redirect:./get-assigned-projects-list";
    }
}
