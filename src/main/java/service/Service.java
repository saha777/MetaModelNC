package service;

import metamodel.dao.models.Role;

import java.util.List;
import java.util.Map;

public interface Service<T> {
    List<T> getAll(Role role);
    List<T> getByParentId(Role role, int parentId);
    T get(Role role, int empId);
    T getForUpdate(Role role, int empId);
    void update(Role role, int empId, Map<String, String[]> params);
}
