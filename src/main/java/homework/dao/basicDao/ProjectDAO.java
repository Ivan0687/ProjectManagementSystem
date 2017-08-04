package homework.dao.basicDao;

import homework.model.entities.Customer;
import homework.model.entities.Developer;
import homework.model.entities.Project;

import java.util.Collection;

public interface ProjectDAO<K extends Number, T extends Project> extends DAO<K, T> {

}
