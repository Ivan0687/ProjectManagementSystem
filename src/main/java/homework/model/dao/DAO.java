package homework.model.dao;

import java.io.Serializable;
import java.util.Collection;

public interface DAO<K extends Serializable, T>{

    void create(T entity);

    T read(K key);

    Collection<T> read();

    void update(K key, T entity);

    void delete(K key);
}
