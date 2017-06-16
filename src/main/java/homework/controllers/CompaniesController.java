package homework.controllers;

import homework.model.dao.CompanyDAO;
import homework.model.entities.Company;
import homework.mySqlDaoImpl.CompanyDAOImpl;

import static homework.model.Utils.*;
import static homework.model.Utils.provideIntInputStreamWithMessage;

public class CompaniesController {

    private CompanyDAO<Integer, Company> companyDAO = new CompanyDAOImpl();

    public void showMenu() {

        while (true) {
            printBorder();
            System.out.println("Companies menu");
            System.out.println("1) Create company");
            System.out.println("2) Find company");
            System.out.println("3) Show all Companies");
            System.out.println("4) Update company");
            System.out.println("5) Delete company");
            System.out.println("6) Back to main menu");
            printBorder();

            Integer choice = provideIntInputStream();
            if (choice == null) {
                System.err.println("Not correct entered data, try again");
                break;
            } else {
                switch (choice) {
                    case 1:
                        createCompany();
                        break;
                    case 2:
                        findCompany();
                        break;
                    case 3:
                        showAllCompanies();
                        break;
                    case 4:
                        updateCompany();
                        break;
                    case 5:
                        deleteCompany();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Enter correct choice.");
                }
            }
        }
    }

    private void createCompany() {
        Company company = new Company();

        String companyName = provideStringInputStream("Enter company name or press 'enter' to return to menu: ");
        if (isValidString(companyName))
            company.setName(companyName);
        else return;

        String companyCity = provideStringInputStream("Enter company city or press 'enter' to return to menu: ");
        if (isValidString(companyCity))
            company.setCity(companyCity);
        else return;

        companyDAO.create(company);
    }

    private void findCompany() {
        Integer companyId = provideIntInputStreamWithMessage("Enter company id or press 'enter' to return to menu: ");
        findCompany(companyId);
    }

    private Company findCompany(Integer companyId) {
        Company company = null;
        if (companyId != null) {
            company = companyDAO.read(companyId);
            if (company != null) {
                System.out.print("Company with id = " + companyId + " is: ");
                System.out.println(company);
            } else
                System.out.println("Company with id = " + companyId + " doesn't exist");
        }
        return company;
    }

    private void showAllCompanies() {
        System.out.println("All companies in database: ");
        for (Company company : companyDAO.read()) {
            System.out.println(company);
        }
    }

    private void updateCompany() {
        Integer companyId = provideIntInputStreamWithMessage("Enter company id you want to update or press 'enter': ");

        Company company = null;
        if (companyId != null)
            company = findCompany(companyId);

        if (company == null)
            return;

        String companyName = provideStringInputStream("Enter new company name or press 'enter': ");
        if (isValidString(companyName))
            company.setName(companyName);

        String companyCity = provideStringInputStream("Enter new company city or press 'enter': ");
        if (isValidString(companyCity))
            company.setCity(companyCity);

        companyDAO.update(companyId, company);
    }

    private void deleteCompany() {

        Integer companyId = provideIntInputStreamWithMessage("Enter company id you want to delete or press 'enter' to return to menu: ");

        Company company = null;
        if (companyId != null)
            company = findCompany(companyId);

        if (company == null)
            return;

        companyDAO.delete(companyId);

        System.out.println("Company with id = " + companyId + " deleted.");
    }

}
