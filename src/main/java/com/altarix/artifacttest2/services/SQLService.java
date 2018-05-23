package com.altarix.artifacttest2.services;

import com.altarix.artifacttest2.exceptions.InvalidDateException;
import com.altarix.artifacttest2.exceptions.NegativeNumbException;
import com.altarix.artifacttest2.mappers.CompanyMapper;
import com.altarix.artifacttest2.mappers.DepartmentMapper;
import com.altarix.artifacttest2.mappers.EmployeeMapper;
import com.altarix.artifacttest2.mappers.PositionInDeprtmntMapper;
import com.altarix.artifacttest2.models.domains.Department;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//		SqlSessionFactory sqlSessionFactory;
//		CompanyMapper companyMapper;
//		Reader reader = null;
//		try {
//			reader = Resources
//					.getResourceAsReader("mybatis-config.xml"); //Читаем файл с настройками подключения и настройками MyBatis
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//			SqlSession session = sqlSessionFactory.openSession();
//
//			companyMapper = session.getMapper(CompanyMapper.class); //Создаем маппер, из которого и будем вызывать методы getSubscriberById и getSubscribers
//            DepartmentMapper depMapper = session.getMapper(DepartmentMapper.class);
//            EmployeeMapper empMap = session.getMapper(EmployeeMapper.class);
//            PositionInDeprtmntMapper posMap = session.getMapper(PositionInDeprtmntMapper.class);
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
	private CompanyMapper companyMapper;
	private DepartmentMapper deprtmntMapper;
	private PositionInDeprtmntMapper posInDeprmnt;
	private EmployeeMapper employeeMapper;
	private Reader reader;
	private SqlSession session;

    public SQLService(){
        try {
            this.reader =Resources.getResourceAsReader("mybatis-config.xml"); //Читаем файл с настройками подключения и настройками MyBatis
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    public long createDepartment(Department department){
        try {
            Checker.checkID(department.getIdCompany());
            Checker.checkID(department.getIdCompany());
            Checker.checkDate(department.getDateOfCreating(), new Date(System.currentTimeMillis()));
            // mb sdelat idrootdep checking
            connect();
            deprtmntMapper = session.getMapper(DepartmentMapper.class);
            deprtmntMapper.insert(department);
            session.commit();
            disconnect();
        } catch (SQLException | NegativeNumbException | InvalidDateException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return department.getIdDepartment();
    }




    private boolean connect() throws SQLException {
        if (session==null || session.getConnection().isClosed()) {
            session = sqlSessionFactory.openSession();
            return true;
        }else return false;
    }

    private boolean disconnect() throws SQLException{
        if (!session.getConnection().isClosed()){
            session.close();
            return true;
        }else return false;
    }
}
