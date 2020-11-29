package com.iflytek.pattern.structure.filter.criteria;

import com.iflytek.pattern.structure.filter.entity.Person;

import java.util.List;

/**
 * 标准且
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 16:08
 */
public class AndCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }
}
