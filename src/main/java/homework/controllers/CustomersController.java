package homework.controllers;

import homework.dao.basicDao.CustomerDAO;
import homework.model.entities.Customer;
import homework.dao.mySqlDaoImpl.CustomerDAOImpl;

import static homework.model.utils.Utils.*;

public class CustomersController {

    private CustomerDAO<Integer, Customer> customerDAO = new CustomerDAOImpl();


    public void showMenu() {

        while (true) {
            printBorder();
            System.out.println("Customers menu");
            System.out.println("1) Create customer");
            System.out.println("2) Find customer");
            System.out.println("3) Show all customers");
            System.out.println("4) Update customer");
            System.out.println("5) Delete customer");
            System.out.println("6) Back to main menu");
            printBorder();

            Integer choice = provideIntInputStream();
            if (choice == null) {
                System.err.println("Not correct entered data, try again");
                break;
            } else {
                switch (choice) {
                    case 1:
                        createCustomer();
                        break;
                    case 2:
                        findCustomer();
                        break;
                    case 3:
                        showAllCustomers();
                        break;
                    case 4:
                        updateCustomer();
                        break;
                    case 5:
                        deleteCustomer();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Enter correct choice.");
                }
            }
        }
    }

    private void createCustomer() {
        Customer customer = new Customer();

        String companyName = provideStringInputStream("Enter customer name or press 'enter' to return to menu: ");
        if (isValidString(companyName))
            customer.setName(companyName);
        else return;

        String companyCity = provideStringInputStream("Enter customer city or press 'enter' to return to menu: ");
        if (isValidString(companyCity))
            customer.setCity(companyCity);
        else return;

        customerDAO.create(customer);
    }

    private void findCustomer() {
        Integer companyId = provideIntInputStreamWithMessage("Enter customer id or press 'enter' to return to menu: ");
        findCustomer(companyId);
    }

    private Customer findCustomer(Integer customerId) {
        Customer customer = null;
        if (customerId != null) {
            customer = customerDAO.read(customerId);
            if (customer != null) {
                System.out.print("Customer with id = " + customerId + " is: ");
                System.out.println(customer);
            } else
                System.out.println("Customer with id = " + customerId + " doesn't exist");
        }
        return customer;
    }

    private void showAllCustomers() {
        System.out.println("All customers in database: ");
        for (Customer customer : customerDAO.read()) {
            System.out.println(customer);
        }
    }

    private void updateCustomer() {
        Integer customerId = provideIntInputStreamWithMessage("Enter customer id you want to update or press 'enter' to return to menu: ");

        Customer customer = null;
        if (customerId != null)
            customer = findCustomer(customerId);

        if (customer == null)
            return;

        String customerName = provideStringInputStream("Enter customer name or press 'enter': ");
        if (isValidString(customerName))
            customer.setName(customerName);

        String customerCity = provideStringInputStream("Enter customer city or press 'enter': ");
        if (isValidString(customerCity))
            customer.setCity(customerCity);

        customerDAO.update(customerId, customer);
    }

    private void deleteCustomer() {

        Integer customerId = provideIntInputStreamWithMessage("Enter customer id you want to delete or press 'enter' to return to menu: ");

        Customer customer = null;
        if (customerId != null)
            customer = findCustomer(customerId);

        if (customer == null)
            return;

        customerDAO.delete(customerId);

        System.out.println("Customer with id = " + customerId + " deleted.");
    }


}
