package com.andy.employe.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.employe.model.Employee;
import com.andy.employe.repos.EmployeeRepository;

@SuppressWarnings("null")
@Service
public class EmployeeService implements CrudService<Employee> {
     @Autowired
    private EmployeeRepository repository;


    /**
     * @param employee à enregistrer
     * @return  id de l'employee
     */
    @Override
    public Long save(Employee employee) {
       // employee.setCommitment(LocalDate.now());
        var emp = repository.save(employee);
        this.generateMatricule(emp);
        update(emp);
        return emp.getId();
    }

    /**
     * @param employee et ses infos à mettre à jour
     * @return employee et ses infos deja mise à jour
     */
    @Override
    public Employee update(Employee employee) {
        return repository.save(employee);
    }

    /**
     * @param id de l'employé
     * @return l'employe s'l existe dans la base de donnéees
     */
    @Override
    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * @param id de l'employe à verifier
     * @return true si l'employe existe et false dans le cas contraire
     */
    @Override
    public boolean isExist(Long id) {
        return repository.existsById(id);
    }

    /**
     * @return la liste de tout les employes
     */
    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<Employee>();
        repository.findAll().forEach(e->{
            employees.add(e);
        });
        return employees;
    }

    /**
     * @param id de l'employe
     *
     *           suppression d'un employe
     */

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private String generateMatricule(Employee employee){
        var now = LocalDate.now();
        var name = "ENTREPRISE";
        var year = now.getYear();
        var mont = now.getMonthValue();
        String mat = name+"-"+(String.valueOf(year))+"-"+(String.valueOf(mont))+"-"+(String.valueOf(employee.getId()));
        employee.setSerialNumber(mat);
        return mat;
    }
}
