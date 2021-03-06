package homework.dao.basicDao;

import homework.model.entities.Model;

import java.io.Serializable;
import java.util.Collection;

public interface DAO<K extends Serializable, T extends Model>{

    void create(T entity);

    T read(K key);

    Collection<T> read();

    void update(K key, T entity);

    void delete(K key);
}
