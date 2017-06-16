package homework.mySqlDaoImpl;

import homework.model.dao.ProjectDAO;
import homework.model.entities.Customer;
import homework.model.entities.Developer;
import homework.model.entities.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ProjectDAOImpl implements ProjectDAO<Integer, Project> {

    private String URL = "jdbc:mysql://127.0.0.1:3306/ivan_homework" +
            "?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private String USER = "root";
    private String PASSWORD = "root";

    private final String createSQL = "INSERT INTO projects (name, surname, customer_id, cost ) VALUES (?, ?, ?, ?)";
    private final String readProjectSQL = "SELECT * FROM projects WHERE id = ?";
    private final String readProjectsSQL = "SELECT * FROM projects";
    private final String updateSQL = "UPDATE projects SET name = ?, surname = ?, customer_id = ?, cost = ? WHERE id = ?";


    public ProjectDAOImpl() {
    }

    public ProjectDAOImpl(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void create(Project project) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(createSQL)) {

            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setInt(3, project.getCustomerId());
            preparedStatement.setInt(4, project.getCost());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public Project read(Integer id) {


        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(readProjectSQL)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                Project project = new Project();
                if (resultSet.next()) {
                    project.setId(resultSet.getInt("id"));
                    project.setName(resultSet.getString("name"));
                    project.setDescription(resultSet.getString("level"));
                    project.setCustomerId(resultSet.getInt("customer_id"));
                    project.setCost(resultSet.getInt("cost"));
                    return project;
                } else
                    return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public Collection<Project> read() {


        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(readProjectsSQL)) {

            return getProjectsFromStatement(preparedStatement);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void update(Integer id, Project project) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setInt(3, project.getCustomerId());
            preparedStatement.setInt(4, project.getCost());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM projects WHERE id = ?")) {

                preparedStatement.setInt(1, id);
                preparedStatement.execute();

                try (PreparedStatement preparedStatementProjectDev = connection.prepareStatement("DELETE FROM project_developers WHERE id = ?")) {
                    preparedStatementProjectDev.setInt(1, id);
                    preparedStatementProjectDev.execute();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Project> findCustomerProjects(Customer customer) {

        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM projects WHERE customer_id = ? ")) {
                preparedStatement.setInt(1, customer.getId());

                return getProjectsFromStatement(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<Project> getProjectsFromStatement(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            ArrayList<Project> customerProjects = new ArrayList<>();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("level"));
                project.setCustomerId(resultSet.getInt("customer_id"));
                project.setCost(resultSet.getInt("cost"));
                customerProjects.add(project);
            }
            return customerProjects;
        }
    }

    @Override
    public Collection<Project> findDeveloperProjects(Developer developer) {

        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM projects WHERE id IN (SELECT FROM project_developers WHERE developer_id = ?)")) {
                preparedStatement.setInt(1, developer.getId());

                return getProjectsFromStatement(preparedStatement);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
