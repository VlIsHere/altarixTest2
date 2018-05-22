package com.altarix.artifacttest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MappedTypes(Company.class)
//@MapperScan("com.altarix.artifacttest2.mappers")
@SpringBootApplication
public class Artifacttest2Application {

	public static void main(String[] args) {
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

		SpringApplication.run(Artifacttest2Application.class, args);
	}
}
