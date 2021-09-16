package org.jguitart.rsql.panache.clauses;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.jguitart.rsql.panache.PanacheQueryDescriptor;
import org.jguitart.rsql.panache.exceptions.RsqlPanacheParserException;
import org.jguitart.rsql.panache.utils.ValueParser;

import java.util.List;
import java.util.stream.Collectors;

public class InClauseParser extends RsqlClauseParserBase {
    @Override
    public PanacheQueryDescriptor parseClause(ComparisonNode rsqlNode, Class entityClass) throws RsqlPanacheParserException {

        PanacheQueryDescriptor result = new PanacheQueryDescriptor();
        String paramName = rsqlNode.getSelector() + "_" + System.nanoTime();

        Class fieldType = getFieldType(rsqlNode, entityClass);

        List<Object> parsedValue = rsqlNode.getArguments().stream().map(valueRaw -> ValueParser.parseValue(valueRaw, fieldType)).collect(Collectors.toList());

        result.getParams().put(paramName, parsedValue);

        String query = String.format("%s in :%s", rsqlNode.getSelector(), paramName);
        result.setQuery(query);


        return result;
    }

}
