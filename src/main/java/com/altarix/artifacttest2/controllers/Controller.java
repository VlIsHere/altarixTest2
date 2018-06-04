package com.altarix.artifacttest2.controllers;

import com.altarix.artifacttest2.pojo.Department;
import com.altarix.artifacttest2.pojo.Employee;
import com.altarix.artifacttest2.services.SQLService;
import com.altarix.artifacttest2.services.TransactionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import java.sql.Date;

/** http://localhost:8080/swagger-ui.html
 * R1) Необходимо реализовать метод, работающий по расписанию, и сохраняющий в отдельную таблицу информацию о
 * фонде заработной платы, каждого департамента. Метод должен запускаться каждые 5 минут. При удалении
 * какого-либо департамента из системы, должна также удаляться информация о департаменте и из этой таблицы.
 *
 * R2) При создании департамента, его переименовании или перемещении, необходимо записывать
 * информацию об этих событиях в отдельной таблице.
 */
@Path("/sqlQueries")
@Api(value = "Company", description = "APIs for working with db company")
@RestController
@RequestMapping(value = "/sqlQueries")
public class Controller {
    private SQLService sqlService;

    @Autowired
    public Controller(SQLService sqlService) {
        this.sqlService = sqlService;
    }

    @PUT
    @Path("/d1/")
    @ApiOperation(value = "createDeprtmnt", response = TransactionResult.class)
    @PutMapping("/d1/")
    public TransactionResult insertDeprtmnt(@RequestBody Department department){
        return sqlService.createDepartment(department);
    }

    @PUT
    @Path("/d2/")
    @ApiOperation(value = "updateNameDeprtmnt", response = TransactionResult.class)
    @PutMapping("/d2/")
    public TransactionResult updateNameDeprtmnt(@RequestBody Department department){
        return sqlService.updateNameDeprtmnt(department);
    }

    @DELETE
    @Path("/d3/")
    @ApiOperation(value = "delDeprtmnt", response = TransactionResult.class)
    @DeleteMapping("/d3/{id}")
    public TransactionResult delDeprtmnt(@PathVariable(value = "id") long id){
        return sqlService.delDeprtmnt(id);
    }

    @GET
    @Path("/d4/")
    @ApiOperation(value = "getDeprtmnt", response = TransactionResult.class)
    @GetMapping(value = "/d4/{idDep}")
    public TransactionResult getDeprtmnt(@PathVariable(value = "idDep") long idDep){
        return sqlService.getDeprtmntInfo(idDep);
    }

    @GET
    @Path("/d5/")
    @ApiOperation(value = "getChildDeps", response = TransactionResult.class)
    @GetMapping(value = "/d5/{idRoot}")
    public TransactionResult getChildDeps(@PathVariable(value = "idRoot") long idRoot){
        return sqlService.getChildDeps(idRoot);
    }

    @GET
    @Path("/d6/")
    @ApiOperation(value = "getSubtreeDeps", response = TransactionResult.class)
    @GetMapping(value = "/d6/{idRoot}")
    public TransactionResult getSubtreeDeps(@PathVariable(value = "idRoot") long idRoot){
        return sqlService.getSubtreeDeps(idRoot);
    }

    @PUT
    @Path("/d7/")
    @ApiOperation(value = "transferDep", response = TransactionResult.class)
    @PutMapping("/d7/{idParent}/{idChild}")
    public TransactionResult transferDep(@PathVariable(value = "idChild") Long idChild,@PathVariable(value = "idParent") Long idParent){
        return sqlService.transferDep(idChild,idParent);//v, cltkfnm djpvj;yjcnm lkz null
    }

    @GET
    @Path("/d8/")
    @ApiOperation(value = "getAncestors", response = TransactionResult.class)
    @GetMapping(value = "/d8/{idChild}")
    public TransactionResult getAncestors(@PathVariable(value = "idChild") long idChild){
        return sqlService.getAncestors(idChild);
    }

    @GET
    @Path("/d9/")
    @ApiOperation(value = "getDepByName", response = TransactionResult.class)
    @GetMapping(value = "/d9/{nameDep}")
    public TransactionResult getDepByName(@PathVariable(value = "nameDep") String nameDep){
        return sqlService.getDepByName(nameDep);
    }

    @GET
    @Path("/d10/")
    @ApiOperation(value = "getFondMoney", response = TransactionResult.class)
    @GetMapping(value = "/d10/{idDep}")
    public TransactionResult getFondMoney(@PathVariable(value = "idDep") long idDep){
        return sqlService.getFondMoneyByDep(idDep);
    }

    @GET
    @Path("/d11/")
    @ApiOperation(value = "getEmployees", response = TransactionResult.class)
    @GetMapping(value = "/d11/{idDep}")
    public TransactionResult getEmployees(@PathVariable(value = "idDep") long idDep) {
        return sqlService.getEmployeesByDep(idDep);
    }

    @PUT
    @Path("/d12/")
    @ApiOperation(value = "createEmployee", response = TransactionResult.class)
    @PutMapping("/d12/")
    public TransactionResult insertEmployee(@RequestBody Employee employee){
        return sqlService.createEmployee(employee);
    }

    @PATCH
    @Path("/d13/")
    @ApiOperation(value = "updateEmployee", response = TransactionResult.class)
    @PatchMapping("/d13/")
    public TransactionResult updateEmployee(@RequestBody Employee employee){
        return sqlService.changeEmployee(employee);
    }

    @PATCH
    @Path("/d14/")
    @ApiOperation(value = "updateEmployee", response = TransactionResult.class)
    @PatchMapping("/d14/{id}/{dateDismis}")
    public TransactionResult updateEmployee(@PathVariable(value = "id")long id,@PathVariable(value = "dateDismis")Date dateDismis){
        return sqlService.dismisEmployee(id,dateDismis);
    }

    @GET
    @Path("/d15/")
    @ApiOperation(value = "getEmployee", response = TransactionResult.class)
    @GetMapping(value = "/d15/{id}")
    public TransactionResult getEmployee(@PathVariable(value = "id") long id) {
        return sqlService.getEmployee(id);
    }

    @PATCH
    @Path("/d16/")
    @ApiOperation(value = "updateEmployee", response = TransactionResult.class)
    @PatchMapping("/d16/{idEmp}/{idNewDep}")
    public TransactionResult updateEmployee(@PathVariable(value = "idEmp") long idEmp,
                                  @PathVariable(value = "idNewDep")long idNewDep){
        return sqlService.transferEmployee(idEmp,idNewDep);
    }

    @PATCH
    @Path("/d17/")
    @ApiOperation(value = "updateEmployees", response = TransactionResult.class)
    @PatchMapping("/d17/{idOldDep}/{idNewDep}")
    public TransactionResult updateEmployees(@PathVariable(value = "idOldDep") long idOldDep,
                                   @PathVariable(value = "idNewDep") long idNewDep){
        return sqlService.transferEmployees(idOldDep,idNewDep);
    }

    @GET
    @Path("/d18/")
    @ApiOperation(value = "getBoss", response = TransactionResult.class)
    @GetMapping(value = "/d18/{id}")
    public TransactionResult getBoss(@PathVariable(value = "id") long id) {
        return sqlService.getBossOfEmployee(id);
    }

    @GET
    @Path("/d19/")
    @ApiOperation(value = "getContactEmpls", response = TransactionResult.class)
    @GetMapping(value = "/d19/{sex}")
    public TransactionResult getContactEmpls(@PathVariable(value = "sex") String sex) {
        return sqlService.getContactEmplsData(sex.toUpperCase());
    }
}
