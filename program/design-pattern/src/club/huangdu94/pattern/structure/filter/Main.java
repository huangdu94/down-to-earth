package club.huangdu94.pattern.structure.filter;

import java.util.ArrayList;
import java.util.List;

import club.huangdu94.pattern.structure.filter.criteria.AndCriteria;
import club.huangdu94.pattern.structure.filter.criteria.Criteria;
import club.huangdu94.pattern.structure.filter.criteria.CriteriaFemale;
import club.huangdu94.pattern.structure.filter.criteria.CriteriaMale;
import club.huangdu94.pattern.structure.filter.criteria.CriteriaSingle;
import club.huangdu94.pattern.structure.filter.criteria.OrCriteria;
import club.huangdu94.pattern.structure.filter.entity.Person;

/**
 * 过滤器模式
 * 过滤器模式（Filter Pattern）或标准模式（Criteria Pattern）是一种设计模式，
 * 这种模式允许开发人员使用不同的标准来过滤一组对象，通过逻辑运算以解耦的方式把它们连接起来。
 * 这种类型的设计模式属于结构型模式，它结合多个标准来获得单一标准。
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 16:01
 */
public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("Robert", "Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("\nFemales: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("\nSingle Males: ");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("\nSingle Or Females: ");
        printPersons(singleOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons) {
        persons.forEach(System.out::println);
    }
}
