package repositories;

import java.util.List;

public interface IRepository<T> {
    void add(T t);
    List<T> getAll();
}
