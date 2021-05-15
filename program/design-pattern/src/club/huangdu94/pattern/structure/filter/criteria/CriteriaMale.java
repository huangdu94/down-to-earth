package club.huangdu94.pattern.structure.filter.criteria;

import club.huangdu94.pattern.structure.filter.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 男性标准
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 16:06
 */
public class CriteriaMale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("MALE")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}