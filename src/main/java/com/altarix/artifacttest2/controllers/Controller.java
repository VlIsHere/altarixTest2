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

/**
 * R1) Необходимо реализовать метод, работающий по расписанию, и сохраняющий в отдельную таблицу информацию о
 * фонде заработной платы, каждого департамента. Метод должен запускаться каждые 5 минут. При удалении
 * какого-либо департамента из системы, должна также удаляться информация о департаменте и из этой таблицы.
 *
 * R2)При создании департамента, его переименовании или перемещении, необходимо записывать
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

    @Path("/d1/")
    @PUT
    @ApiOperation(value = "insertDeprtmnt")
    @PutMapping("/d1/")
    public TransactionResult insertDeprtmnt(@RequestBody Department department){
        return sqlService.createDepartment(department);
    }

    @Path("/d2/")
    @PUT
    @ApiOperation(value = "updateNameDeprtmnt")
    @PutMapping("/d2/")
    public TransactionResult updateNameDeprtmnt(@RequestBody Department department){
        return sqlService.updateNameDeprtmnt(department);
    }

    @Path("/d3/")
    @DELETE
    @ApiOperation(value = "delDeprtmnt")
    @DeleteMapping("/d3/{id}")
    public TransactionResult delDeprtmnt(@PathVariable(value = "id") long id){
        return sqlService.delDeprtmnt(id);
    }

    @Path("/d4/")
    @GET
    @ApiOperation(value = "delDeprtmnt")
    @GetMapping(value = "/d4/{idDep}")
    public TransactionResult getDeprtmnt(@PathVariable(value = "idDep") long idDep){
        return sqlService.getDeprtmntInfo(idDep);
    }
    @Path("/d5/")
    @GET
    @ApiOperation(value = "delDeprtmnt")
    @GetMapping(value = "/d5/{idRoot}")
    public TransactionResult getChildDeps(@PathVariable(value = "idRoot") long idRoot){
        return sqlService.getChildDeps(idRoot);
    }

    @Path("/d6/")
    @GET
    @ApiOperation(value = "delDeprtmnt")
    @GetMapping(value = "/d6/{idRoot}")
    public TransactionResult getSubtreeDeps(@PathVariable(value = "idRoot") long idRoot){
        return sqlService.getSubtreeDeps(idRoot);
    }

    @Path("/d7/")
    @PUT
    @ApiOperation(value = "delDeprtmnt")
    @PutMapping("/d7/{idParent}/{idChild}")
    public TransactionResult transferDep(@PathVariable(value = "idChild") Long idChild,@PathVariable(value = "idParent") Long idParent){
        return sqlService.transferDep(idChild,idParent);//v, cltkfnm djpvj;yjcnm lkz null
    }

    @Path("/d8/")
    @GET
    @ApiOperation(value = "delDeprtmnt")
    @GetMapping(value = "/d8/{idChild}")
    public TransactionResult getAncestors(@PathVariable(value = "idChild") long idChild){
        return sqlService.getAncestors(idChild);
    }

    @Path("/d9/")
    @GET
    @ApiOperation(value = "delDeprtmnt")
    @GetMapping(value = "/d9/{nameDep}")
    public TransactionResult getDepByName(@PathVariable(value = "nameDep") String nameDep){
        return sqlService.getDepByName(nameDep);
    }

    @Path("/d10/")
    @GET
    @ApiOperation(value = "delDeprtmnt")
    @GetMapping(value = "/d10/{idDep}")
    public TransactionResult getFondMoney(@PathVariable(value = "idDep") long idDep){
        return sqlService.getFondMoneyByDep(idDep);
    }

    @Path("/d11/")
    @GET
    @ApiOperation(value = "delDeprtmnt")
    @GetMapping(value = "/d11/{idDep}")
    public TransactionResult getEmployees(@PathVariable(value = "idDep") long idDep) {
        return sqlService.getEmployeesByDep(idDep);
    }

    @Path("/d12/")
    @PUT
    @ApiOperation(value = "delDeprtmnt")
    @PutMapping("/d12/")
    public TransactionResult insertEmployee(@RequestBody Employee employee){
        return sqlService.createEmployee(employee);
    }

    @Path("/d13/")
    @PATCH
    @ApiOperation(value = "delDeprtmnt")
    @PatchMapping("/d13/")
    public TransactionResult updateEmployee(@RequestBody Employee employee){
        return sqlService.changeEmployee(employee);
    }

    @Path("/d14/")
    @PATCH
    @ApiOperation(value = "delDeprtmnt")
    @PatchMapping("/d14/{id}/{dateDismis}")
    public TransactionResult updateEmployee(@PathVariable(value = "id")long id,@PathVariable(value = "dateDismis")Date dateDismis){
        return sqlService.dismisEmployee(id,dateDismis);
    }

    @Path("/d15/")
    @GET
    @ApiOperation(value = "delDeprtmnt")
    @GetMapping(value = "/d15/{id}")
    public TransactionResult getEmployee(@PathVariable(value = "id") long id) {
        return sqlService.getEmployee(id);
    }

    @Path("/d16/")
    @PATCH
    @ApiOperation(value = "delDeprtmnt")
    @PatchMapping("/d16/{idEmp}/{idNewDep}")
    public TransactionResult updateEmployee(@PathVariable(value = "idEmp") long idEmp,
                                  @PathVariable(value = "idNewDep")long idNewDep){
        return sqlService.transferEmployee(idEmp,idNewDep);
    }

    @Path("/d17/")
    @PATCH
    @ApiOperation(value = "delDeprtmnt")
    @PatchMapping("/d17/{idOldDep}/{idNewDep}")
    public TransactionResult updateEmployees(@PathVariable(value = "idOldDep") long idOldDep,
                                   @PathVariable(value = "idNewDep") long idNewDep){
        return sqlService.transferEmployees(idOldDep,idNewDep);
    }

    @Path("/d18/")
    @GET
    @ApiOperation(value = "getBoss")
    @GetMapping(value = "/d18/{id}")
    public TransactionResult getBoss(@PathVariable(value = "id") long id) {
        return sqlService.getBossOfEmployee(id);
    }

    @Path("/d19/")
    @GET
    @ApiOperation(value = "getContactEmpls")
    @GetMapping(value = "/d19/{sex}")
    public TransactionResult getContactEmpls(@PathVariable(value = "sex") String sex) {
        return sqlService.getContactEmplsData(sex.toUpperCase());
    }
}
