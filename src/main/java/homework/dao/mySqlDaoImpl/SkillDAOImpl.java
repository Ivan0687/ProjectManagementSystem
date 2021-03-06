package homework.dao.mySqlDaoImpl;

import homework.model.entities.Skill;
import homework.dao.basicDao.SkillDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class SkillDAOImpl implements SkillDAO<Integer, Skill> {

    private String URL = "jdbc:mysql://127.0.0.1:3306/ivan_homework" +
            "?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private String USER = "root";
    private String PASSWORD = "root";

    public SkillDAOImpl() {
    }

    public SkillDAOImpl(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void create(Skill skill) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO skills (name, level, tax) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, skill.getName());
            preparedStatement.setString(2, skill.getLevel());
            preparedStatement.setInt(3, skill.getTax());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public Skill read(Integer id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM SKILLS WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                Skill skill = new Skill();
                if (resultSet.next()) {
                    skill.setId(resultSet.getInt("id"));
                    skill.setName(resultSet.getString("name"));
                    skill.setLevel(resultSet.getString("level"));
                    skill.setTax(resultSet.getInt("tax"));
                    return skill;
                } else
                    return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public Collection<Skill> read() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM SKILLS ")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                ArrayList<Skill> skills = new ArrayList<>();

                while (resultSet.next()) {
                    Skill skill = new Skill();
                    skill.setId(resultSet.getInt("id"));
                    skill.setName(resultSet.getString("name"));
                    skill.setLevel(resultSet.getString("level"));
                    skill.setTax(resultSet.getInt("tax"));
                    skills.add(skill);
                }
                return skills;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void update(Integer id, Skill skill) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE skills SET name = ?, level = ?, tax = ? WHERE id = ?")) {

            preparedStatement.setString(1, skill.getName());
            preparedStatement.setString(2, skill.getLevel());
            preparedStatement.setInt(3, skill.getTax());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM SKILLS WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
