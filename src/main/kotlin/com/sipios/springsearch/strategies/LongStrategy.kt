package com.sipios.springsearch.strategies

import com.sipios.springsearch.SearchOperation
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Expression
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate
import kotlin.reflect.KClass

class LongStrategy : ParsingStrategy {
    override fun buildPredicate(
        builder: CriteriaBuilder,
        path: Path<*>,
        fieldName: String,
        ops: SearchOperation?,
        value: Any?
    ): Predicate? {
        var fieldValue = path.get<Long>(fieldName)
        return when (ops) {
            SearchOperation.GREATER_THAN -> builder.greaterThan(fieldValue, value as Long)
            SearchOperation.LESS_THAN -> builder.lessThan(fieldValue, value as Long)			
            SearchOperation.EQUALS -> builder.equal(fieldValue, value as Long)
            SearchOperation.NOT_EQUALS -> builder.notEqual(fieldValue, value as Long)
            else -> super.buildPredicate(builder, path, fieldName, ops, value)
        }
    }

    override fun parse(value: String?, fieldClass: KClass<out Any>): Any? {
        return value?.toLong()
    }
}
