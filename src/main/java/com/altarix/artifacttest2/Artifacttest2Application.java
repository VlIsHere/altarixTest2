package com.altarix.artifacttest2;

import com.altarix.artifacttest2.pojo.*;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MappedTypes({Company.class,Department.class,Employee.class,PositionInDeprtmnt.class,Domain.class,SalaryFund.class})
@MapperScan("com.altarix.artifacttest2.dao")
@SpringBootApplication
@ComponentScan(value = "com.altarix.artifacttest2")
public class Artifacttest2Application {
	public static void main(String[] args) {
		SpringApplication.run(Artifacttest2Application.class, args);
	}
}
