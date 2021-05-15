package club.huangdu94.pattern.structure.filter.criteria;

import club.huangdu94.pattern.structure.filter.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 单身标准
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 16:07
 */
public class CriteriaSingle implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getMaritalStatus().equalsIgnoreCase("SINGLE")) {
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }
}
