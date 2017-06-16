package homework.model.dao;

import homework.model.entities.Customer;
import homework.model.entities.Project;

import java.util.Collection;

public interface CustomerDAO<K extends Number, T extends Customer> extends DAO<K, T>{


}
