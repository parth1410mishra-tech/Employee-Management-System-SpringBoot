package com.parth.employee.controller;
import com.parth.employee.model.Employee;
import com.parth.employee.repository.EmployeeRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.List;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeRepository repository;
    public EmployeeController(EmployeeRepository repository){this.repository=repository;}
    public record EmployeeRequest(@NotBlank String name,@NotBlank String department,@NotNull @DecimalMin("0.01") BigDecimal salary){}
    @PostMapping
    public ResponseEntity<Employee> add(@Valid @RequestBody EmployeeRequest request){
        Employee saved=repository.save(new Employee(request.name(),request.department(),request.salary()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping public List<Employee> all(){return repository.findAll();}
    @GetMapping("/{id}") public Employee byId(@PathVariable Long id){
        return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found: "+id));
    }
    @PutMapping("/{id}") public Employee update(@PathVariable Long id,@Valid @RequestBody EmployeeRequest request){
        Employee updated=new Employee(id,request.name(),request.department(),request.salary());
        if(!repository.update(id,updated)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found: "+id);
        return updated;
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!repository.delete(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found: "+id);
        return ResponseEntity.noContent().build();
    }
}