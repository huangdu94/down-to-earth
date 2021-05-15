package club.huangdu94.pattern.structure.filter.criteria;

import java.util.List;

import club.huangdu94.pattern.structure.filter.entity.Person;

/**
 * 标准且
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 16:08
 */
public class AndCriteria implements Criteria {

    private final Criteria criteria;
    private final Criteria otherCriteria;

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
