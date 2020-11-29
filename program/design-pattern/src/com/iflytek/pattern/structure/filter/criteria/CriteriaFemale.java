package com.iflytek.pattern.structure.filter.criteria;

import com.iflytek.pattern.structure.filter.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 女性标准
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 16:06
 */
public class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("FEMALE")) {
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
