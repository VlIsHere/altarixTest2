package com.altarix.artifacttest2.services;

import com.altarix.artifacttest2.dao.EmployeeDAO;
import com.altarix.artifacttest2.exceptions.*;
import com.altarix.artifacttest2.models.pojo.Employee;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
   //private static final Pattern DATE = Pattern.compile("^(0[1-9]|1[0-9]|2[0-9]|3[01])(:|/)(0[1-9]|1[012])(:|/)[0-9]{4}$");//DD:MM:YYYY
    private static final Pattern NAME = Pattern.compile("^(([а-яё]|[А-ЯЁ])([А-ЯЁ]|[а-яё]|[\\-])+?)$");
    private static final Pattern EMAIL = Pattern.compile("^[_A-Za-z0-9-\\+]+((\\.|[_A-Za-z0-9-])+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern PHONE_NUMBER =Pattern.compile("^(\\+7|8)(\\(\\d{3}\\)|\\d{3})\\d{3}-?\\d{2}-?\\d{2}$");
    private static Matcher matcher;

    public static void checkPhoneNumb(String phoneNumb) throws InvalidPhoneNumberException{
        matcher = PHONE_NUMBER.matcher(phoneNumb);
        if (!matcher.matches()) throw new InvalidPhoneNumberException();
    }

    public static void  checkSalary(int salary) throws NegativeNumbException {
        if (salary<0) throw new NegativeNumbException();
    }

    public static void checkID(long id) throws NegativeNumbException {
        if (id<0) throw new NegativeNumbException();
    }

    /**
     * @param date
     * @param currDate текущая точная дата
     * @throws InvalidDateException
     */
    public static void checkDate(Date date,Date currDate) throws InvalidDateException {
        if (date !=null && currDate!=null) {
            if (currDate.compareTo(date) < 0) throw new InvalidDateException();//дата должна меньше настоящей!
        }
    }

    public static char checkSex(String sex) throws InvalidDataException {
        if (sex.length()!=1 ) throw new InvalidDataException();
        if (sex.equals("M"))  return 'М';
        else if (sex.equals("W")) return 'Ж';
        else throw new InvalidDataException();
    }

    public static void checkEmail(String email) throws InvalidEmailException {
        matcher = EMAIL.matcher(email);
        if (!matcher.matches()) throw new InvalidEmailException();
    }

    public static void checkForFIO(String name) throws InvalidDataException {
        if (name!= null) {
            matcher = NAME.matcher(name);
            if (!matcher.matches()) throw new InvalidDataException();
        }
    }

    public static void checkEmployee(Employee emp, EmployeeDAO employeeDAO) throws InvalidDataException {
        Checker.checkID(emp.getId());
        Checker.checkID(emp.getIdCompany());
        Checker.checkID(emp.getIdDepartment());
        Checker.checkID(emp.getIdPosition());
        Checker.checkEmail(emp.getEmail());
        Checker.checkForFIO(emp.getName());
        Checker.checkForFIO(emp.getPatronymic());
        Checker.checkForFIO(emp.getSurname());
        Checker.checkDate(emp.getEmploymentDate(),new Date(System.currentTimeMillis()));
        Checker.checkDate(emp.getDateOfDismissal(),new Date(System.currentTimeMillis()));
        Checker.checkDate(emp.getDateBirth(),new Date(System.currentTimeMillis()));
        if ((emp.getDateOfDismissal()!=null && emp.getDateOfDismissal().compareTo(emp.getEmploymentDate())<0) ||
                emp.getEmploymentDate().compareTo(emp.getDateBirth())<=0) throw new InvalidDateException();

        Integer maxSalary = employeeDAO.getMaxSalary(emp.getIdDepartment());
        Employee boss = employeeDAO.getBossOfDep(emp.getIdDepartment());
        if (emp.isChief()) {
            if (boss.getDateOfDismissal()!=null) throw new BossAlreadyExistException();
            if (emp.getSalary()<maxSalary) throw new SalaryException();
        }else {
            if (emp.getSalary()>maxSalary) throw new SalaryException();
        }
    }
}
