package homework.controllers;

import homework.dao.basicDao.ProjectDAO;
import homework.model.entities.Project;
import homework.dao.mySqlDaoImpl.ProjectDAOImpl;

import static homework.model.utils.Utils.*;
import static homework.model.utils.Utils.provideIntInputStreamWithMessage;

public class ProjectsController {

    private ProjectDAO<Integer, Project> projectDAO = new ProjectDAOImpl();

    public void showMenu() {

        while (true) {
            printBorder();
            System.out.println("Projects menu");
            System.out.println("1) Create project");
            System.out.println("2) Find project");
            System.out.println("3) Show all projects");
            System.out.println("4) Update project");
            System.out.println("5) Delete project");
            System.out.println("6) Back to main menu");
            printBorder();

            Integer choice = provideIntInputStream();
            if (choice == null) {
                System.err.println("Not correct entered data, try again");
                break;
            } else {
                switch (choice) {
                    case 1:
                        createProject();
                        break;
                    case 2:
                        findProject();
                        break;
                    case 3:
                        showAllProjects();
                        break;
                    case 4:
                        updateProject();
                        break;
                    case 5:
                        deleteProject();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Enter correct choice.");
                }
            }
        }
    }

    private void createProject() {
        Project project = new Project();

        String projectName = provideStringInputStream("Enter project name or press 'enter' to return to menu: ");
        if (isValidString(projectName))
            project.setName(projectName);
        else return;

        String projectDescription = provideStringInputStream("Enter project description or press 'enter' to return to menu: ");
        if (isValidString(projectDescription))
            project.setDescription(projectDescription);
        else return;

        Integer customerId = provideIntInputStreamWithMessage("Enter customer id or press 'enter' to return to menu: ");
        if (customerId != null)
            project.setCustomerId(customerId);
        else return;

        Integer cost = provideIntInputStreamWithMessage("Enter cost or press 'enter' to return to menu: ");
        if (cost != null)
            project.setCost(cost);
        else return;

        projectDAO.create(project);
    }

    private void findProject() {
        Integer projectId = provideIntInputStreamWithMessage("Enter project id or press 'enter' to return to menu: ");
        findProject(projectId);
    }

    private Project findProject(Integer projectId) {
        Project project = null;
        if (projectId != null) {
            project = projectDAO.read(projectId);
            if (project != null) {
                System.out.print("Project with id = " + projectId + " is: ");
                System.out.println(project);
            } else
                System.out.println("Project with id = " + projectId + " doesn't exist");
        }
        return project;
    }

    private void showAllProjects() {
        System.out.println("All projects in database: ");
        for (Project project : projectDAO.read()) {
            System.out.println(project);
        }
    }

    private void updateProject() {
        Integer projectId = provideIntInputStreamWithMessage("Enter project id you want to update or press 'enter' to return to menu: ");

        Project project = null;
        if (projectId != null)
            project = findProject(projectId);

        if (project == null)
            return;

        String developerName = provideStringInputStream("Enter new project name or press 'enter': ");
        if (isValidString(developerName))
            project.setName(developerName);
        else return;

        String developerSurname = provideStringInputStream("Enter new project surname or press 'enter': ");
        if (isValidString(developerSurname))
            project.setDescription(developerSurname);
        else return;

        Integer customerId = provideIntInputStreamWithMessage("Enter new customer id or press 'enter': ");
        if (customerId != null)
            project.setCustomerId(customerId);

        Integer cost = provideIntInputStreamWithMessage("Enter new cost or press 'enter': ");
        if (cost != null)
            project.setCost(cost);

        projectDAO.update(projectId, project);
    }

    private void deleteProject() {

        Integer projectId = provideIntInputStreamWithMessage("Enter project id you want to delete or press 'enter' to return to menu: ");

        Project project = null;
        if (projectId != null)
            project = findProject(projectId);

        if (project == null)
            return;

        projectDAO.delete(projectId);

        System.out.println("Project with id = " + projectId + " deleted.");
    }
}
