package by.mops.db.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
    Optional<T> find(Long id);
    void save(T model);
    boolean update(T model);

    boolean delete(Long id);

    List<T> findAll();
}
