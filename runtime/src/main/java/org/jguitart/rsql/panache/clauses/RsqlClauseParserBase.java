package org.jguitart.rsql.panache.clauses;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.jguitart.rsql.panache.exceptions.RsqlPanacheParserException;

import java.lang.reflect.Field;

abstract public class RsqlClauseParserBase implements IClauseParser {


    protected Class getFieldType(ComparisonNode rsqlNode, Class entityClass) throws RsqlPanacheParserException {
        Field field = null;
        try {
            field = entityClass.getDeclaredField(rsqlNode.getSelector());
        } catch (NoSuchFieldException e) {
            // Field not exist in class, but it can be in superclasses, like id.
            try {
                field = entityClass.getField(rsqlNode.getSelector());
            } catch (NoSuchFieldException eDeclared) {
                throw new RsqlPanacheParserException();
            }
        }
        return field.getType();
    }

}
