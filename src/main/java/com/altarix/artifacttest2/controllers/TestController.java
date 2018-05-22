package com.altarix.artifacttest2.controllers;


import com.altarix.artifacttest2.models.domains.Company;
import com.altarix.artifacttest2.services.SQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private SQLService sqlService;

    //Создание департамента. При создании департамента нужно указать информацию о департаменте, в который он
    // будет входить. Для самого верхнего департамента такой информации указывать не нужно.
    @RequestMapping(value = "/g", method = RequestMethod.GET)
    public void createDeprtmnt(){
        //todo check
    }
//
////		Изменение наименования департамента. В системе не может быть двух департаментов с одинаковыми наименованиями.
//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//
////		Удаление департамента. Удаление возможно, только если в нем нет ни одного сотрудника.
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//
////Просмотр сведений о департаменте. Должна быть выдана информация о наименовании департамента, дате создания,
//// руководителе департамента и количестве сотрудников департамента.
//    @RequestMapping(value = "/u", method = RequestMethod.GET)

//		Предоставление информации о департаментах, находящихся в непосредственном подчинении данного департамента (на уровень ниже).
//Предоставление информации о всех департаментах, находящихся в подчинении данного департамента (все подчиненные департаменты. Для головного департамента - это все остальные департаменты).
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
//        CompanyMapper companyMapper;
//        SqlSessionFactory sqlSessionFactory;
//        Reader reader = null;
//        List<Company> companies = null;
//        try {
//            reader = Resources.getResourceAsReader("mybatis-config.xml");
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//            companyMapper = sqlSessionFactory.openSession().getMapper(CompanyMapper.class);
//            companies = companyMapper.getAll();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return  companies;
        return null;
    }
}
