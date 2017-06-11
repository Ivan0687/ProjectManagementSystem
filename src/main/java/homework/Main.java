package homework;

import homework.controllers.*;

import static homework.model.Utils.printBorder;
import static homework.model.Utils.provideIntInputStream;

public class Main {

    public static void main(String[] args) {

        while (true) {

            printBorder();
            System.out.println("Main menu");
            System.out.println("1) Manage skills");
            System.out.println("2) Manage developers");
            System.out.println("3) Manage companies");
            System.out.println("4) Manage projects");
            System.out.println("5) Manage customers");
            System.out.println("6) Exit");
            printBorder();

            Integer choice = provideIntInputStream();

            if (choice == null) {
                System.err.println("Not correct entered data, try again");
                break;

            } else {
                switch (choice) {
                    case 1:
                        new SkillsController().showMenu();
                        break;
                    case 2:
                        new DevelopersController().showMenu();
                        break;
                    case 3:
                        new CompaniesController().showMenu();
                        break;
                    case 4:
                       new ProjectsController().showMenu();
                        break;
                    case 5:
                        new CustomersController().showMenu();
                        break;
                    case 6:
                        System.exit(0);
                    default:
                        System.out.println("Enter correct choice.");
                }
            }
        }
    }
}
