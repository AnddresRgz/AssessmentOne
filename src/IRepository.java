import java.util.List;
import java.util.UUID;

public interface IRepository<T> {
    void add(T t);
    List<T> getAll();
    void update(T oldObj, T newObj);
    void delete(T t);
}
