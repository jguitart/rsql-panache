package org.jguitart.rsql.panache.clauses;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.jguitart.rsql.panache.PanacheQueryDescriptor;
import org.jguitart.rsql.panache.exceptions.RsqlPanacheParserException;

public interface IClauseParser {

    PanacheQueryDescriptor parseClause(ComparisonNode rsqlNode, Class entityClass) throws RsqlPanacheParserException;

}
