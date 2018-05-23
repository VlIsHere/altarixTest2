package com.altarix.artifacttest2.services;

import com.altarix.artifacttest2.exceptions.*;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
   //private static final Pattern DATE = Pattern.compile("^(0[1-9]|1[0-9]|2[0-9]|3[01])(:|/)(0[1-9]|1[012])(:|/)[0-9]{4}$");//DD:MM:YYYY
    private static final Pattern NAME = Pattern.compile("^([А-ЯЁ]([а-яё]|[\\-])+?)$");
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
       if (currDate.compareTo(date)<0) throw new InvalidDateException();//дата должна меньше настоящей!
    }

    public static void checkEmail(String email) throws InvalidEmailException {
        matcher = EMAIL.matcher(email);
        if (!matcher.matches()) throw new InvalidEmailException();
    }

    public static void checkForFIO(String name) throws InvalidDataException {
        matcher = NAME.matcher(name);
        if (!matcher.matches()) throw new InvalidDataException();
    }
}
