package com.altarix.artifacttest2.controllers;


import com.altarix.artifacttest2.json.response.DepartmentInfo;
import com.altarix.artifacttest2.models.pojo.Department;
import com.altarix.artifacttest2.models.pojo.Employee;
import com.altarix.artifacttest2.services.SQLService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.sql.Date;
import java.util.List;

@Api(value = "Company", description = "APIs for working with db company")
@RestController
@RequestMapping(value = "/sqlQueries") //, consumes = "application/json"
public class Controller {
    private SQLService sqlService;

    @Autowired
    public Controller(SQLService sqlService) {
        this.sqlService = sqlService;
    }

    //Создание департамента. При создании департамента нужно указать информацию о департаменте, в который он
    //будет входить. Для самого верхнего департамента такой информации указывать не нужно.
    //инфа о департаменте, в который будет входить новый деп-нт, содержится в поле idrootDepartment
    @Path("login")
    @PUT
    @ApiOperation(value = "insertDeprtmnt")
    @PutMapping("/d1/")
    public long insertDeprtmnt(@RequestBody Department department){
        return sqlService.createDepartment(department);
    }

//		Изменение наименования департамента. В системе не может быть двух департаментов с одинаковыми наименованиями.
    //@RequestMapping(value = "/d2/", method = RequestMethod.PUT)
    @PutMapping("/d2/")
    public long updateNameDeprtmnt(@RequestBody Department department){
        return sqlService.updateNameDeprtmnt(department);
    }

//		Удаление департамента. Удаление возможно, только если в нем нет ни одного сотрудника.
   // @RequestMapping(value = "/d3/", method = RequestMethod.DELETE)
    @DeleteMapping("/d3/{id}")
    public boolean delDeprtmnt(@PathVariable(value = "id") long id){//можно ли передать по json только число типа long?
        return sqlService.delDeprtmnt(id);
    }

//Просмотр сведений о департаменте. Должна быть выдана информация о наименовании департамента, дате создания,
// руководителе департамента и количестве сотрудников департамента. @RequestMapping(value = "/d4/{id}", method = RequestMethod.GET)
    @GetMapping(value = "/d4/{idDep}", produces = "application/json")
    public DepartmentInfo getDeprtmnt(@PathVariable(value = "idDep") long idDep){
        return sqlService.getDeprtmntInfo(idDep);
    }

//Предоставление информации о департаментах, находящихся в непосредственном подчинении данного департамента (на уровень ниже).
    @GetMapping(value = "/d5/{idRoot}", produces = "application/json")
    public List<Department> getChildDeps(@PathVariable(value = "idRoot") long idRoot){
        return sqlService.getChildDeps(idRoot);
    }

//Предоставление информации о всех департаментах, находящихся в подчинении данного департамента
// (все подчиненные департаменты. Для головного департамента - это все остальные департаменты).
    @GetMapping(value = "/d6/{idRoot}", produces = "application/json")
    public List<Department> getSubtreeDeps(@PathVariable(value = "idRoot") long idRoot){
        return sqlService.getSubtreeDeps(idRoot);
    }

//		Перенос департамента. Задание другого департамента, куда будет входить данный департамент.
    @PutMapping("/d7/{idParent}/{idChild}")
    public long transferDep(@PathVariable(value = "idChild") Long idChild,@PathVariable(value = "idParent") Long idParent){
        return sqlService.transferDep(idChild,idParent);//v, cltkfnm djpvj;yjcnm lkz null
    }

//		Получение информации о всех вышестоящих департаментах данного департамента.
    @GetMapping(value = "/d8/{idChild}", produces = "application/json")
    public List<Department> getAncestors(@PathVariable(value = "idChild") long idChild){
        return sqlService.getAncestors(idChild);
    }

    //		Поиск департамента по наименованию.
    @GetMapping(value = "/d9/{nameDep}", produces = "application/json")
    public Department getDepByName(@PathVariable(value = "nameDep") String nameDep){
        return sqlService.getDepByName(nameDep);
    }

//		Получение информации о фонде заработной платы департамента (сумма зарплат всех сотрудников департамента).
    @GetMapping(value = "/d10/{idDep}")
    public long getFondMoney(@PathVariable(value = "idDep") long idDep){
        return sqlService.getFondMoneyByDep(idDep);
    }

//		Получение списка сотрудников департамента.
    @GetMapping(value = "/d11/{idDep}", produces = "application/json")
    public List<Employee> getEmployees(@PathVariable(value = "idDep") long idDep) {
        return sqlService.getEmployeesByDep(idDep);
    }

//		Создание сотрудника департамента.
    @PutMapping("/d12/")
    public long insertEmployee(@RequestBody Employee employee){
        return sqlService.createEmployee(employee);
    }

//		Редактирование сведений о сотруднике департамента.
    @PatchMapping("/d13/")
    public long updateEmployee(@RequestBody Employee employee){
        return sqlService.changeEmployee(employee);
    }

    //		Увольнение сотрудника с указанием даты увольнения.
    @PatchMapping("/d14/{id}/{dateDismis}")
    public long updateEmployee(@PathVariable(value = "id")long id,@PathVariable(value = "dateDismis")Date dateDismis){
        return sqlService.dismisEmployee(id,dateDismis);
    }

//		Получение информации о сотруднике.
    @GetMapping(value = "/d15/{id}", produces = "application/json")
    public Employee getEmployee(@PathVariable(value = "id") long id) {
        return sqlService.getEmployee(id);
    }

//	Перевод сотрудника из одного департамента в другой.
    @PatchMapping("/d16/{idEmp}/{idNewDep}")
    public boolean updateEmployee(@PathVariable(value = "idEmp") long idEmp,
                                  @PathVariable(value = "idNewDep")long idNewDep){
        return sqlService.transferEmployee(idEmp,idNewDep);
    }

//		Перевод всех сотрудников департамента в другой департамент.
    @PatchMapping("/d17/{idOldDep}/{idNewDep}")
    public boolean updateEmployees(@PathVariable(value = "idOldDep") long idOldDep,
                                   @PathVariable(value = "idNewDep") long idNewDep){
        return sqlService.transferEmployees(idOldDep,idNewDep);
    }

//		Получение информации о руководителе данного сотрудника.
    @GetMapping(value = "/d18/{id}", produces = "application/json")
    public Employee getBoss(@PathVariable(value = "id") long id) {
        return sqlService.getBossOfEmployee(id);
    }

//    		Поиск сотрудников по атрибутам (по каким – решить самостоятельно).
    @GetMapping(value = "/d19/{sex}", produces = "application/json")
    public List<Employee> getContactEmpls(@PathVariable(value = "sex") String sex) {
        return sqlService.getContactEmplsData(sex.toUpperCase());
    }
}
