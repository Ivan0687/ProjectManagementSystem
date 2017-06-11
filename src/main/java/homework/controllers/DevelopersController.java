package homework.controllers;

import homework.model.dao.DeveloperDAO;
import homework.model.entities.Developer;
import homework.mySqlDaoImpl.DeveloperDAOImpl;

import static homework.model.Utils.*;

public class DevelopersController {

    private DeveloperDAO<Developer> developerDAO = new DeveloperDAOImpl();

    public void showMenu() {

        while (true) {
            printBorder();
            System.out.println("Developers menu");
            System.out.println("1) Create developer");
            System.out.println("2) Find developer");
            System.out.println("3) Show all developers");
            System.out.println("4) Update developer");
            System.out.println("5) Delete developer");
            System.out.println("6) Back to main menu");
            printBorder();

            Integer choice = provideIntInputStream();
            if (choice == null) {
                System.err.println("Not correct entered data, try again");
                break;
            } else {
                switch (choice) {
                    case 1:
                        createDeveloper();
                        break;
                    case 2:
                        findDeveloper();
                        break;
                    case 3:
                        showAllDevelopers();
                        break;
                    case 4:
                        updateDeveloper();
                        break;
                    case 5:
                        deleteDeveloper();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Enter correct choice.");
                }
            }
        }
    }

    private void createDeveloper() {
        Developer developer = new Developer();

        String developerName = provideStringInputStream("Enter developer name or press 'enter' to return to menu: ");
        if (isValidString(developerName))
            developer.setName(developerName);
        else return;

        String developerSurname = provideStringInputStream("Enter developer surname or press 'enter' to return to menu: ");
        if (isValidString(developerSurname))
            developer.setSurname(developerSurname);
        else return;

        Integer companyId = provideIntInputStreamWithMessage("Enter company id or press 'enter' to return to menu: ");
        if (companyId != null)
            developer.setCompanyId(companyId);

        Integer salary = provideIntInputStreamWithMessage("Enter salary or press 'enter' to return to menu: ");
        if (salary != null)
            developer.setSalary(salary);

        developerDAO.create(developer);
    }

    private void findDeveloper() {
        Integer companyId = provideIntInputStreamWithMessage("Enter company id or press 'enter' to return to menu: ");
        findDeveloper(companyId);
    }

    private Developer findDeveloper(Integer developerId) {
        Developer developer = null;
        if (developerId != null) {
            developer = developerDAO.read(developerId);
            if (developer != null) {
                System.out.print("Developer with id = " + developerId + " is: ");
                System.out.println(developer);
            } else
                System.out.println("Developer with id = " + developerId + " doesn't exist");
        }
        return developer;
    }

    private void showAllDevelopers() {
        System.out.println("All developers in database: ");
        for (Developer developer : developerDAO.read()) {
            System.out.println(developer);
        }
    }

    private void updateDeveloper() {
        Integer developerId = provideIntInputStreamWithMessage("Enter developer id you want to update or press 'enter' to return to menu: ");

        Developer developer = null;
        if (developerId != null)
            developer = findDeveloper(developerId);

        if (developer == null)
            return;

        String developerName = provideStringInputStream("Enter new developer name or press 'enter' to return to menu: ");
        if (isValidString(developerName))
            developer.setName(developerName);
        else return;

        String developerSurname = provideStringInputStream("Enter new developer surname or press 'enter' to return to menu: ");
        if (isValidString(developerSurname))
            developer.setSurname(developerSurname);
        else return;

        Integer companyId = provideIntInputStreamWithMessage("Enter new company id or press 'enter' to return to menu: ");
        if (companyId != null)
            developer.setCompanyId(companyId);

        Integer salary = provideIntInputStreamWithMessage("Enter new salary or press 'enter' to return to menu: ");
        if (salary != null)
            developer.setSalary(salary);

        developerDAO.update(developerId, developer);
    }

    private void deleteDeveloper() {

        Integer developerId = provideIntInputStreamWithMessage("Enter developer id you want to delete or press 'enter' to return to menu: ");

        Developer developer = null;
        if (developerId != null)
            developer = findDeveloper(developerId);

        if (developer == null)
            return;

        developerDAO.delete(developerId);

        System.out.println("Developer with id = " + developerId + " deleted.");
    }
}
