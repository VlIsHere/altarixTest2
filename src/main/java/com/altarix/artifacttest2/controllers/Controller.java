package com.altarix.artifacttest2.controllers;


import com.altarix.artifacttest2.json.response.DepartmentInfo;
import com.altarix.artifacttest2.models.pojo.Company;
import com.altarix.artifacttest2.models.pojo.Department;
import com.altarix.artifacttest2.services.SQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/d1/")
    public long createDeprtmnt(@RequestBody Department department){
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
//		Получение информации о всех вышестоящих департаментах данного департамента.
//		Поиск департамента по наименованию.
//		Получение информации о фонде заработной платы департамента (сумма зарплат всех сотрудников департамента).
//		Получение списка сотрудников департамента.
//		Создание сотрудника департамента.
//		Редактирование сведений о сотруднике департамента.
//		Увольнение сотрудника с указанием даты увольнения.
//		Получение информации о сотруднике.
//		Перевод сотрудника из одного департамента в другой.
//		Перевод всех сотрудников департамента в другой департамент.
//		Получение информации о руководителе данного сотрудника.
//		Поиск сотрудников по атрибутам (по каким – решить самостоятельно).

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Company> selectEmpl(){
//        CompanyDAO companyMapper;
//        SqlSessionFactory sqlSessionFactory;
//        Reader reader = null;
//        List<Company> companies = null;
//        try {
//            reader = Resources.getResourceAsReader("mybatis-config.xml");
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//            companyMapper = sqlSessionFactory.openSession().getMapper(CompanyDAO.class);
//            companies = companyMapper.getAll();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return  companies;
        return null;
    }
}
