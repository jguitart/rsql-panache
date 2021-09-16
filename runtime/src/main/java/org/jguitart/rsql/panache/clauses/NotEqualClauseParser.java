package org.jguitart.rsql.panache.clauses;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.jguitart.rsql.panache.PanacheQueryDescriptor;
import org.jguitart.rsql.panache.exceptions.RsqlPanacheParserException;
import org.jguitart.rsql.panache.utils.ValueParser;

public class NotEqualClauseParser extends RsqlClauseParserBase {
    @Override
    public PanacheQueryDescriptor parseClause(ComparisonNode rsqlNode, Class entityClass) throws RsqlPanacheParserException {

        PanacheQueryDescriptor result = new PanacheQueryDescriptor();
        String paramName = rsqlNode.getSelector() + "_" + System.nanoTime();

        Class fieldType = getFieldType(rsqlNode, entityClass);
        Object parsedValue = ValueParser.parseValue(rsqlNode.getArguments().get(0), fieldType);
        String query = String.format("%s not like :%s", rsqlNode.getSelector(), paramName);
        result.setQuery(query);

        result.getParams().put(paramName, parsedValue);
        return result;
    }
}
