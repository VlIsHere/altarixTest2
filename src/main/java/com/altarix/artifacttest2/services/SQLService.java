package com.altarix.artifacttest2.services;

import com.altarix.artifacttest2.dao.CompanyDAO;
import com.altarix.artifacttest2.dao.DepartmentDAO;
import com.altarix.artifacttest2.dao.EmployeeDAO;
import com.altarix.artifacttest2.dao.PositionInDeprtmntDAO;
import com.altarix.artifacttest2.exceptions.*;
import com.altarix.artifacttest2.json.response.DepartmentInfo;
import com.altarix.artifacttest2.pojo.Department;
import com.altarix.artifacttest2.pojo.Employee;
import org.apache.ibatis.exceptions.PersistenceException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
            this.reader =Resources.getResourceAsReader("mybatis-config.xml");
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            this.session = sqlSessionFactory.openSession();
            deprtmntDAO = session.getMapper(DepartmentDAO.class);
            employeeDAO = session.getMapper(EmployeeDAO.class);
            companyDAO = session.getMapper(CompanyDAO.class);
            positionInDeprtmntDAO = session.getMapper(PositionInDeprtmntDAO.class);
        } catch (IOException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    @PreDestroy
    public void closeSession(){
        session.close();
    }

    public TransactionResult createDepartment(Department department){
        TransactionResult response = new TransactionResult(201,"Created");
        try {
            Checker.checkID(department.getId());
            Checker.checkID(department.getIdCompany());
            Checker.checkDate(department.getDateOfCreating(), new Date(System.currentTimeMillis()));
            deprtmntDAO.insert(department);
            session.commit();
        } catch (NegativeNumbException | InvalidDateException e) {
            logger.log(Level.SEVERE, null, e);
            response.setCode(400);
            response.setMessage(e.getLogMessage());
        }finally {
            response.setField(department.getId());
            return response;
        }
    }

    public TransactionResult updateNameDeprtmnt(Department department){
        TransactionResult response = new TransactionResult(200,"OK");
        try {
            Checker.checkID(department.getId());
            Checker.checkID(department.getIdCompany());
            Checker.checkDate(department.getDateOfCreating(), new Date(System.currentTimeMillis()));
            Department checkD =(Department) deprtmntDAO.getByID(department.getId());
            if (checkD.getNameDepartment().equals(department.getNameDepartment())) throw new NotUniqueException();
            deprtmntDAO.update(department.getId(),department);
            session.commit();
        }catch (NotUniqueException | NegativeNumbException | InvalidDateException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(400);
            response.setMessage(e.getLogMessage());
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        } finally{
            response.setField(department.getId());
            return response;
        }
    }

    public TransactionResult delDeprtmnt(long id){
        TransactionResult response = new TransactionResult(200,"OK");
        try {
            Checker.checkID(id);
            ArrayList<Employee> employees = employeeDAO.getByIdDeprtmnt(id);
            if (!employees.isEmpty()) throw new NotEmptyDeprtmntException();
            deprtmntDAO.delete(id);
            ArrayList<Department> childs = deprtmntDAO.getChilds(id);//если удалим, а он имеет детей, то они без него останутся!
            Department tmp;
            for (int i = 0; i < childs.size(); i++) {
                tmp = childs.get(i);
                tmp.setIdRootDepartment(null);
                deprtmntDAO.update(tmp.getId(),tmp);
            }
            session.commit();
        }catch (NotEmptyDeprtmntException e){
            response.setCode(403);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (NegativeNumbException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        }finally {
            return response;
        }
    }

    public TransactionResult getDeprtmntInfo(long idDep){
        TransactionResult response = new TransactionResult(200,"OK");
        DepartmentInfo depInfo = null;
        try {
            Checker.checkID(idDep);
            depInfo = new DepartmentInfo();
            depInfo.setDepartment((Department) deprtmntDAO.getByID(idDep));
            depInfo.setBoss(employeeDAO.getBossOfDep(idDep));
            depInfo.setCntEmployee(employeeDAO.getCntEmplByDep(idDep));
        }catch (NegativeNumbException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        }finally {
            response.addResult(depInfo);
            return response;
        }
    }

    public TransactionResult getChildDeps(long idRoot) {
        TransactionResult response = new TransactionResult(200,"OK");
        ArrayList<Department> childs = null;
        try {
            Checker.checkID(idRoot);
            childs = deprtmntDAO.getChilds(idRoot);
        }catch (NegativeNumbException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        }finally {
            response.addResult(childs);
            return response;
        }
    }

    public TransactionResult getSubtreeDeps(long idRoot){
        TransactionResult response = new TransactionResult(200,"OK");
        ArrayList<Department> childs = null;
        try {
            Checker.checkID(idRoot);
            childs = getAllChilds(idRoot);
        }catch (NegativeNumbException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        }finally {
            response.addResult(childs);
            return response;
        }
    }

    public TransactionResult transferDep(Long idDepChild,Long idParent){
        TransactionResult response = new TransactionResult(200,"OK");
        try {
            Checker.checkID(idDepChild);
            Checker.checkID(idParent);
            deprtmntDAO = session.getMapper(DepartmentDAO.class);
            if (deprtmntDAO.getByID(idDepChild)==null || deprtmntDAO.getByID(idParent)==null)
                throw new InvalidDataException();
            deprtmntDAO.updateParentDep(idDepChild,idParent);
            session.commit();
        }catch (InvalidDataException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        }finally {
            response.setField(idDepChild);
            return response;
        }
    }

    public TransactionResult getAncestors(long idDepChild) {
        TransactionResult response = new TransactionResult(200,"OK");
        ArrayList<Department> ancestors = null;
        try {
            Checker.checkID(idDepChild);
            Department child = (Department) deprtmntDAO.getByID(idDepChild);
            ancestors = getArrAncestors(child.getIdRootDepartment());
        }catch (InvalidDataException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        }finally {
            response.addResult(ancestors);
            return response;
        }
    }

    public TransactionResult getDepByName(String nameDep) {
        TransactionResult response = new TransactionResult(200,"OK");
        response.addResult(deprtmntDAO.getByName(nameDep));
        return response;
    }

    public TransactionResult getFondMoneyByDep(long idDep){
        TransactionResult response = new TransactionResult(200,"OK");
        try {
            Checker.checkID(idDep);
        } catch (NegativeNumbException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }finally {
            response.addResult(employeeDAO.getFondMoney(idDep));
            return response;
        }
    }

    public TransactionResult getEmployeesByDep(long idDep){
        TransactionResult response = new TransactionResult(200,"OK");
        ArrayList<Employee> employees = null;
        try {
            Checker.checkID(idDep);
            employees = employeeDAO.getByIdDeprtmnt(idDep);
        }catch (NegativeNumbException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }finally {
            response.addResult(employees);
            return response;
        }
    }

    public TransactionResult createEmployee(Employee employee){
        TransactionResult response = new TransactionResult(201,"Created");
        try {
            Checker.checkEmployee(employee,employeeDAO);
            employeeDAO.insert(employee);
            session.commit();
        } catch (InvalidDataException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        }finally {
            response.setField(employee.getId());
            return response;
        }
    }

    public TransactionResult changeEmployee(Employee employee){
        TransactionResult response = new TransactionResult(200,"OK");
        try {
            Checker.checkEmployee(employee,employeeDAO);
            if (employeeDAO.getByID(employee.getId())==null) throw new InvalidDataException();
            employeeDAO.update(employee.getId(),employee);
            session.commit();
        } catch (InvalidDataException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        } finally {
            response.setField(employee.getId());
            return response;
        }
    }

    public TransactionResult dismisEmployee(long id,Date dateDismis){
        TransactionResult response = new TransactionResult(200,"OK");
        try {
            Checker.checkID(id);
            Checker.checkDate(dateDismis,new Date(System.currentTimeMillis()));
            Employee emp = (Employee) employeeDAO.getByID(id);
            Checker.checkDate(emp.getEmploymentDate(),dateDismis);
            emp.setDateOfDismissal(dateDismis);
            employeeDAO.update(id,emp);
            session.commit();
        } catch (NegativeNumbException | InvalidDateException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        } finally {
            response.setField(id);
            return response;
        }
    }

    public TransactionResult getEmployee(long id){
        TransactionResult response = new TransactionResult(200,"OK");
        Employee res = null;
        try {
            Checker.checkID(id);
            res = (Employee) employeeDAO.getByID(id);
        } catch (InvalidDataException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        } finally {
            response.addResult(res);
            return response;
        }
    }

    public TransactionResult transferEmployee(long idEmp, long idNewDep) {
        TransactionResult response = new TransactionResult(200,"OK");
        try {
            Checker.checkID(idEmp);
            Checker.checkID(idNewDep);
            Employee emp = (Employee) employeeDAO.getByID(idEmp);
            if (emp==null) throw new InvalidDataException();
            emp.setIdDepartment(idNewDep);
            employeeDAO.update(idEmp,emp);
            session.commit();
        } catch (InvalidDataException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        }finally {
            response.setField(idEmp);
            return response;
        }
    }

    public TransactionResult transferEmployees(long idOldDep, long idNewDep) {
        TransactionResult response = new TransactionResult(200,"OK");
        try {
            Checker.checkID(idOldDep);
            Checker.checkID(idNewDep);
            ArrayList<Employee> emps = employeeDAO.getByIdDeprtmnt(idOldDep);
            if (emps.size()==0) throw new InvalidDataException();
            Employee tmp;
            for (int i = 0; i < emps.size(); i++) {
                tmp = emps.get(i);
                if (!tmp.isChief()) {
                    tmp.setIdDepartment(idNewDep);
                    employeeDAO.update(tmp.getId(), tmp);
                }
            }
            session.commit();
        } catch (InvalidDataException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        }finally {
            return response;
        }
    }

    public TransactionResult getBossOfEmployee(long id){
        TransactionResult response = new TransactionResult(200,"OK");
        Employee res = null;
        try {
            Checker.checkID(id);
            Employee emp = (Employee) employeeDAO.getByID(id);
            res = employeeDAO.getBossOfDep(emp.getIdDepartment());
        } catch (InvalidDataException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        }finally {
            response.addResult(res);
            return response;
        }
    }

    public TransactionResult getContactEmplsData(String sex){
        TransactionResult response = new TransactionResult(200,"OK");
        ArrayList<Employee> emps = null;
        try {
            char cSex = Checker.checkSex(sex);
            emps = employeeDAO.getMans(cSex);
        } catch (InvalidDataException e) {
            response.setCode(400);
            response.setMessage(e.getLogMessage());
            logger.log(Level.SEVERE, null, e);
        }catch (PersistenceException e){
            logger.log(Level.SEVERE, null, e);
            response.setCode(409);
            response.setMessage(e.getMessage());
        }finally {
            response.addResult(emps);
            return response;
        }
    }

//for getAncestors
    private ArrayList<Department> getArrAncestors(Long idRoot){
        Department tmp;
        ArrayList<Department> deps = new ArrayList<>();
        while (idRoot!=null){
            tmp = (Department) deprtmntDAO.getByID(idRoot);
            deps.add(tmp);
            idRoot = tmp.getIdRootDepartment();
        }
        return deps;
    }

//for getSubtreeDeps
    private  ArrayList<Department> getAllChilds(long idRoot){
        ArrayList<Department> childs = deprtmntDAO.getChilds(idRoot);
        ArrayList<Department> tmp;
        if (childs!=null || childs.size()!=0) {
            for (int i = 0; i < childs.size(); i++) {
                tmp = getAllChilds(childs.get(i).getId());
                childs.addAll(tmp);
            }
        }
        return childs;
    }

}
