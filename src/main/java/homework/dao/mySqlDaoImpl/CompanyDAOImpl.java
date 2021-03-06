package homework.dao.mySqlDaoImpl;

import homework.dao.basicDao.CompanyDAO;
import homework.model.entities.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CompanyDAOImpl implements CompanyDAO<Integer, Company> {

    private String URL = "jdbc:mysql://127.0.0.1:3306/ivan_homework" +
            "?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private String USER = "root";
    private String PASSWORD = "root";

    public CompanyDAOImpl() {
    }

    public CompanyDAOImpl(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void create(Company company) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO companies (name, city) VALUES (?, ?)")) {

            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getCity());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Company read(Integer id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM companies WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Company company = new Company();
                if (resultSet.next()) {
                    company.setId(resultSet.getInt("id"));
                    company.setName(resultSet.getString("name"));
                    company.setCity(resultSet.getString("city"));
                    return company;
                } else
                    return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Company> read() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM companies ")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                ArrayList<Company> companies = new ArrayList<>();

                while (resultSet.next()) {
                    Company company = new Company();
                    company.setId(resultSet.getInt("id"));
                    company.setName(resultSet.getString("name"));
                    company.setCity(resultSet.getString("city"));
                    companies.add(company);
                }
                return companies;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Integer id, Company company) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE companies SET name = ?, city = ? WHERE id = ?")) {

            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getCity());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM companies WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
