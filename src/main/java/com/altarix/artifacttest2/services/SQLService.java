package com.altarix.artifacttest2.services;

import com.altarix.artifacttest2.dao.CompanyDAO;
import com.altarix.artifacttest2.dao.DepartmentDAO;
import com.altarix.artifacttest2.dao.EmployeeDAO;
import com.altarix.artifacttest2.dao.PositionInDeprtmntDAO;
import com.altarix.artifacttest2.exceptions.InvalidDateException;
import com.altarix.artifacttest2.exceptions.NegativeNumbException;
import com.altarix.artifacttest2.exceptions.NotEmptyDeprtmntException;
import com.altarix.artifacttest2.exceptions.NotUniqueException;
import com.altarix.artifacttest2.json.response.DepartmentInfo;
import com.altarix.artifacttest2.models.pojo.Department;
import com.altarix.artifacttest2.models.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//		SqlSessionFactory sqlSessionFactory;
//		CompanyDAO companyMapper;
//		Reader reader = null;
//		try {
//			reader = Resources
//					.getResourceAsReader("mybatis-config.xml"); //Читаем файл с настройками подключения и настройками MyBatis
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//			SqlSession session = sqlSessionFactory.openSession();
//
//			companyMapper = session.getMapper(CompanyDAO.class); //Создаем маппер, из которого и будем вызывать методы getSubscriberById и getSubscribers
//            DepartmentDAO depMapper = session.getMapper(DepartmentDAO.class);
//            EmployeeDAO empMap = session.getMapper(EmployeeDAO.class);
//            PositionInDeprtmntDAO posMap = session.getMapper(PositionInDeprtmntDAO.class);
//
//			Company cmp = new Company();
//			cmp.setIdCompany(3L);
//			cmp.setNameCompany("safewgewrhrwhwrhhrw");
//			//companyMapper.insert(cmp);
//			companyMapper.update(2L,cmp);
//
//          // List<Company> subscribers = companyMapper.getAll();
//           List<Department> subscribers = depMapper.getAll();
//          // List<Employee> subscribers = empMap.getAll();
//          // List<PositionInDeprtmnt> subscribers = posMap.getAll();
//            Employee emp = (Employee) empMap.getByID(3);
//            System.out.println(emp.getIdEmployee() + " " + emp.getName() + " " + emp.getPhoneNumber());
////			Company cmn = (Company) companyMapper.getByID(3L);
////			System.out.println(cmn.getNameCompany());
////			companyMapper.delete(3L);
////			System.out.println(companyMapper.getByID(3L));
//			for (int i = 0; i<subscribers.size();i++) {
//              //  System.out.println(subscribers.get(i).getNamePosition() + " " + subscribers.get(i).getIdPosition());
//				//System.out.println(subscribers.get(i).getIdCompany() +" " + subscribers.get(i).getNameCompany());
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
/**
 * FLC 1	В качестве допустимых символов для атрибутов “фамилия”, “имя”, “отчество” допускается использование только русских символов и знака “-”.-----
 * FLC 2	Значения атрибутов типа “Дата” должны быть валидными.-------------------------------------------------------------
 * FLC 3	В качестве допустимых символов для атрибута “контактный телефон” могут использоваться символы “+-0123456789()” и пробел.---------------
 * FLC 4	Значение атрибута “адрес электронной почты” должно быть валидным.------------------------------------
 * FLC 5	Значение атрибута “Дата приема на работу” не может быть меньше значения атрибута “Дата рождения”.
 * FLC 6	Значение атрибута “Дата увольнения” не может быть меньше значения атрибута “ Дата приема на работу”.
 * FLC 7	Значение атрибута “Оклад” должно быть валидным.------------------------------------------------
 * FLC 8	Оклад сотрудника не может быть больше оклада руководителя департамента, в котором работает сотрудник.
 * FLC 9	В департаменте может быть только один руководитель.
 */

@Service
public class SQLService {
    private Logger logger = Logger.getLogger(SQLService.class.getName());
    private SqlSessionFactory sqlSessionFactory;
    private CompanyDAO companyDAO;
    private DepartmentDAO deprtmntDAO;
    private EmployeeDAO employeeDAO;
    private PositionInDeprtmntDAO positionInDeprtmntDAO;
	private Reader reader;
	private SqlSession session;

    public SQLService(){
        try {
            this.reader =Resources.getResourceAsReader("mybatis-config.xml"); //Читаем файл с настройками подключения и настройками MyBatis
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            this.session = sqlSessionFactory.openSession();
        } catch (IOException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    public long createDepartment(Department department){
        try {
            Checker.checkID(department.getId());
            Checker.checkID(department.getIdCompany());
            Checker.checkDate(department.getDateOfCreating(), new Date(System.currentTimeMillis()));
            // mb sdelat idrootdep checking iz db i
            deprtmntDAO = session.getMapper(DepartmentDAO.class);
            deprtmntDAO.insert(department);
            session.commit();
        } catch (NegativeNumbException | InvalidDateException e) {
            session.rollback();
            logger.log(Level.SEVERE, null, e);
        }
        return department.getId();
    }

    public long updateNameDeprtmnt(Department department){
        try {
            Checker.checkID(department.getId());
            Checker.checkID(department.getIdCompany());
            Checker.checkDate(department.getDateOfCreating(), new Date(System.currentTimeMillis()));
            // mb sdelat idrootdep checking iz db
            deprtmntDAO = session.getMapper(DepartmentDAO.class);
            Department checkD =(Department) deprtmntDAO.getByID(department.getId());
            if (checkD.getNameDepartment().equals(department.getNameDepartment())) throw new NotUniqueException();
            deprtmntDAO.update(department.getId(),department);
            session.commit();
        }catch (NotUniqueException e){
            session.rollback();
            logger.log(Level.SEVERE, null, e);
           //todo уведомить пользователя, что он отправил не уникальное название department
        }
        catch (NegativeNumbException | InvalidDateException e) {
            session.rollback();
            logger.log(Level.SEVERE, null, e);
        }
        return department.getId();
    }

    public boolean delDeprtmnt(long id){
        boolean isOK = false;
        try {
            Checker.checkID(id);
            // mb sdelat idrootdep checking iz db
            deprtmntDAO = session.getMapper(DepartmentDAO.class);
            employeeDAO = session.getMapper(EmployeeDAO.class);
            ArrayList<Employee> employees = employeeDAO.getByIdDeprtmnt(id);//мб написать запрос для 1-го встречного с idDep??
            if (!employees.isEmpty()) throw new NotEmptyDeprtmntException();
            deprtmntDAO.delete(id);
            session.commit();
            isOK = true;
        }catch (NotEmptyDeprtmntException e){
            //todo тоже какую-то обработку надо
        }catch (NegativeNumbException e) {
            session.rollback();
            logger.log(Level.SEVERE, null, e);
        }
        return isOK;
    }

    public DepartmentInfo getDeprtmntInfo(long idDep){
        DepartmentInfo depInfo = null;
        try {
            Checker.checkID(idDep);
            // mb sdelat idrootdep checking iz db
            deprtmntDAO = session.getMapper(DepartmentDAO.class);
            employeeDAO = session.getMapper(EmployeeDAO.class);

            depInfo = new DepartmentInfo();
            depInfo.setDepartment((Department) deprtmntDAO.getByID(idDep));
            depInfo.setBoss(employeeDAO.getBossOfDep(idDep));
            depInfo.setCntEmployee(employeeDAO.getCntEmplByDep(idDep));
        }catch (NegativeNumbException e) {
            session.rollback();
            logger.log(Level.SEVERE, null, e);
        }
        return depInfo;
    }

    public List<Department> getChildDeps(long idRoot) {
        ArrayList<Department> childs = null;
        try {
            Checker.checkID(idRoot);
            // mb sdelat idrootdep checking iz db
            deprtmntDAO = session.getMapper(DepartmentDAO.class);
            childs = deprtmntDAO.getChilds(idRoot);
        }catch (NegativeNumbException e) {
            session.rollback();
            logger.log(Level.SEVERE, null, e);
        }
        return childs;
    }

    public List<Department> getSubtreeDeps(long idRoot){
        ArrayList<Department> childs = null;
        try {
            Checker.checkID(idRoot);
            // mb sdelat idrootdep checking iz db
            deprtmntDAO = session.getMapper(DepartmentDAO.class);
            childs = deprtmntDAO.getChilds(idRoot);
            go(childs,idRoot);
            session.commit();
        }catch (NegativeNumbException e) {
            session.rollback();
            logger.log(Level.SEVERE, null, e);
        }
        return childs;
    }

    private void go(ArrayList<Department> childs,long idRoot){
        for (int i = 0; i < childs.size(); i++) {
            go(childs,childs.get(i).getId());//todo not right recursion!
            childs.addAll(deprtmntDAO.getChilds(idRoot));
        }
    }

    @PreDestroy
    public void closeSession(){
        session.close();
    }
}
