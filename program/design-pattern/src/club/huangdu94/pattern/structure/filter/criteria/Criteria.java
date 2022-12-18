package club.huangdu94.pattern.structure.filter.criteria;

import club.huangdu94.pattern.structure.filter.entity.Person;

import java.util.List;

/**
 * 标准接口
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 16:05
 */
public interface Criteria {
    List<Person> meetCriteria(List<Person> persons);
}
