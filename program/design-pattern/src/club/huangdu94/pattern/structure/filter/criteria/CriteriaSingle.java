package club.huangdu94.pattern.structure.filter.criteria;

import java.util.List;
import java.util.stream.Collectors;

import club.huangdu94.pattern.structure.filter.entity.Person;

/**
 * 单身标准
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 16:07
 */
public class CriteriaSingle implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return persons.stream()
            .filter(person -> person.getMaritalStatus().equalsIgnoreCase("SINGLE"))
            .collect(Collectors.toList());
    }
}
