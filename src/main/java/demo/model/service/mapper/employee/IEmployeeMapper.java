package demo.model.service.mapper.employee;

public interface IEmployeeMapper<T,E> {
    E toRequest(T t);

}
