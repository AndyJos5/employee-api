package com.andy.employe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static com.andy.employe.controller.Utils.*;


import com.andy.employe.model.Employee;
import com.andy.employe.services.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("employee")
@CrossOrigin("*")
@SuppressWarnings("unchecked")
public class EmployeeController implements CrudController<Employee> {
    @Autowired
    private EmployeeService service;
    /**
     * @param employee
     * @return l'identifiant de l'employee
     */
  
    @Override
    @PostMapping(SAVE)
    public ResponseEntity<Long> save(@RequestBody Employee employee) {
        try {
           var id= service.save(employee);
            if (id == null) {
              throw new Exception("Echec d'enregistrement");
            }
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }catch (Exception ex){
            return buildErrorResponse(ex.getMessage());
        }
    }

    /**
     * @param employee
     * @return
     */
    @Override
    @PutMapping(UPDATE)
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        try {
            var id= service.update(employee);
            if (id == null) {
                throw new Exception("Echec de mise à jour");
            }
            return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
        }catch (Exception ex){
            return buildErrorResponse(ex.getMessage());
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    @GetMapping(FIND_BY_ID)
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        try {
            var employee= service.findById(id);
            if (employee.isEmpty()) {
                throw new Exception("Pas d'employé(e)");
            }
            return new ResponseEntity<>(employee.get(),HttpStatus.CREATED);
        }catch (Exception ex){
            return buildErrorResponse(ex.getMessage());
        }
    }

    /**
     * @return
     */
    @Override
    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Employee>> findAll() {
        try {
            var id= service.findAll();
            if (id == null) {
                throw new Exception("Aucun employé");
            }
            return new ResponseEntity<>(id,HttpStatus.OK);
        }catch (Exception ex){
            return buildErrorResponse(ex.getMessage());
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    @DeleteMapping(DELETE_BY_ID)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
             service.delete(id);

            return buildSuccessResponse("Suppression effectuée avec succes");
        }catch (Exception ex){
            return buildErrorResponse(ex.getMessage());
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    @GetMapping(IS_EXIST)
    public ResponseEntity<Boolean> isExist(@PathVariable Long id) {
        try {

            return new ResponseEntity<>(service.isExist(id),HttpStatus.FOUND);
        }catch (Exception ex){
            return buildErrorResponse(ex.getMessage());
        }
    }

    /**
     * @return la page d'accueil
     */
    @Override
    @GetMapping
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Page des employees", HttpStatus.OK);
    }
}
