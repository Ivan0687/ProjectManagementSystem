package homework.dao.mySqlDaoImpl;

import homework.dao.basicDao.CustomerDAO;
import homework.model.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerDAOImpl implements CustomerDAO<Integer, Customer> {

    private String URL = "jdbc:mysql://127.0.0.1:3306/ivan_homework" +
            "?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private String USER = "root";
    private String PASSWORD = "root";

    public CustomerDAOImpl() {
    }

    public CustomerDAOImpl(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void create(Customer customer) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers (name, city) VALUES (?, ?)")) {

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getCity());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public Customer read(Integer id) {

        try (Connection connection = getConnection()) {
            Customer customer = new Customer();

            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        customer.setId(resultSet.getInt("id"));
                        customer.setName(resultSet.getString("name"));
                        customer.setCity(resultSet.getString("city"));

                    } else
                        return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Customer> read() {

        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers ")) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    ArrayList<Customer> customers = new ArrayList<>();

                    while (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setId(resultSet.getInt("id"));
                        customer.setName(resultSet.getString("name"));
                        customer.setCity(resultSet.getString("city"));

                        customers.add(customer);
                    }
                    return customers;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Integer id, Customer customer) {

        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customers SET name = ?, city = ? WHERE id = ?")) {

                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getCity());
                preparedStatement.setInt(3, id);


                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {

        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE id = ?")) {

                preparedStatement.setInt(1, id);
                preparedStatement.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}