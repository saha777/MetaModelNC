package dao;

import java.util.List;

public interface Dao<T> {
    List<T> findAll();

    List<T> findByParentId(Integer departmentId);

    T findById(Integer id);

    void update(T employee);
}
