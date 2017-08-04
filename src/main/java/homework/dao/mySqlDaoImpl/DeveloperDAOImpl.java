package homework.dao.mySqlDaoImpl;

import homework.dao.basicDao.DeveloperDAO;
import homework.model.entities.Developer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DeveloperDAOImpl implements DeveloperDAO<Integer, Developer> {


    private String URL = "jdbc:mysql://127.0.0.1:3306/ivan_homework" +
            "?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private String USER = "root";
    private String PASSWORD = "root";

    public DeveloperDAOImpl() {
    }

    public DeveloperDAOImpl(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void create(Developer developer) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO developers (name, surname, company_id, salary) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, developer.getName());
            preparedStatement.setString(2, developer.getSurname());
            preparedStatement.setInt(3, developer.getCompanyId());
            preparedStatement.setInt(4, developer.getSalary());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public Developer read(Integer id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM developers WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                Developer developer = new Developer();
                if (resultSet.next()) {
                    developer.setId(resultSet.getInt("id"));
                    developer.setName(resultSet.getString("name"));
                    developer.setSurname(resultSet.getString("level"));
                    developer.setCompanyId(resultSet.getInt("company_id"));
                    developer.setSalary(resultSet.getInt("salary"));
                    return developer;
                } else
                    return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public Collection<Developer> read() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM developers ")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                ArrayList<Developer> developers = new ArrayList<>();

                while (resultSet.next()) {
                    Developer developer = new Developer();
                    developer.setId(resultSet.getInt("id"));
                    developer.setName(resultSet.getString("name"));
                    developer.setSurname(resultSet.getString("level"));
                    developer.setCompanyId(resultSet.getInt("company_id"));
                    developer.setSalary(resultSet.getInt("salary"));
                    developers.add(developer);
                }
                return developers;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void update(Integer id, Developer developer) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE developers SET name = ?, surname = ?, company_id = ?, salary = ? WHERE id = ?")) {

            preparedStatement.setString(1, developer.getName());
            preparedStatement.setString(2, developer.getSurname());
            preparedStatement.setInt(3, developer.getCompanyId());
            preparedStatement.setInt(4, developer.getSalary());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM developers WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
