package org.jguitart.rsql.panache.clauses;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.jguitart.rsql.panache.PanacheQueryDescriptor;
import org.jguitart.rsql.panache.exceptions.RsqlPanacheParserException;
import org.jguitart.rsql.panache.utils.ValueParser;

import java.lang.reflect.Field;

public class GreaterThanClauseParser extends RsqlClauseParserBase {
    @Override
    public PanacheQueryDescriptor parseClause(ComparisonNode rsqlNode, Class entityClass) throws RsqlPanacheParserException {

        PanacheQueryDescriptor result = new PanacheQueryDescriptor();
        String paramName = rsqlNode.getSelector() + "_" + System.nanoTime();
        String valueRaw = rsqlNode.getArguments().get(0);

        Class fieldType = getFieldType(rsqlNode, entityClass);
        Object parsedValue = ValueParser.parseValue(valueRaw, fieldType);
        result.getParams().put(paramName, parsedValue);

        String query = String.format("%s > :%s", rsqlNode.getSelector(), paramName);
        result.setQuery(query);


        return result;
    }

}
