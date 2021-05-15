package club.huangdu94.pattern.structure.filter.criteria;

import java.util.List;
import java.util.stream.Collectors;

import club.huangdu94.pattern.structure.filter.entity.Person;

/**
 * 女性标准
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 16:06
 */
public class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return persons.stream()
            .filter(person -> person.getGender().equalsIgnoreCase("FEMALE"))
            .collect(Collectors.toList());
    }
}
