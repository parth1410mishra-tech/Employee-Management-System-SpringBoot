package com.parth.employee.repository;
import com.parth.employee.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<Employee> mapper=(rs,row)->new Employee(rs.getLong("id"),rs.getString("name"),rs.getString("department"),rs.getBigDecimal("salary"));
    public EmployeeRepository(JdbcTemplate jdbc){this.jdbc=jdbc;}
    public Employee save(Employee e){
        jdbc.update("INSERT INTO employees(name,department,salary) VALUES (?,?,?)",e.getName(),e.getDepartment(),e.getSalary());
        e.setId(jdbc.queryForObject("SELECT LAST_INSERT_ID()",Long.class)); return e;
    }
    public List<Employee> findAll(){return jdbc.query("SELECT id,name,department,salary FROM employees ORDER BY id",mapper);}
    public Optional<Employee> findById(Long id){return jdbc.query("SELECT id,name,department,salary FROM employees WHERE id=?",mapper,id).stream().findFirst();}
    public boolean update(Long id,Employee e){return jdbc.update("UPDATE employees SET name=?,department=?,salary=? WHERE id=?",e.getName(),e.getDepartment(),e.getSalary(),id)>0;}
    public boolean delete(Long id){return jdbc.update("DELETE FROM employees WHERE id=?",id)>0;}
}