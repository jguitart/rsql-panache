package org.jguitart.rsql.panache.clauses;

import cz.jirutka.rsql.parser.ast.RSQLOperators;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;

@ApplicationScoped
public class ClauseParserFactory {

    public IClauseParser getParser(String operationSymbol) {
        if(Arrays.asList(RSQLOperators.EQUAL.getSymbols()).contains(operationSymbol)) {
            return new EqualLikeClauseParser();
        }


        if(Arrays.asList(RSQLOperators.GREATER_THAN.getSymbols()).contains(operationSymbol)) {
            return new GreaterThanClauseParser();
        }

        if(Arrays.asList(RSQLOperators.GREATER_THAN_OR_EQUAL.getSymbols()).contains(operationSymbol)) {
            return new GreaterThanOrEqualClauseParser();
        }

        if(Arrays.asList(RSQLOperators.LESS_THAN.getSymbols()).contains(operationSymbol)) {
            return new LessThanClauseParser();
        }

        if(Arrays.asList(RSQLOperators.LESS_THAN_OR_EQUAL.getSymbols()).contains(operationSymbol)) {
            return new LessThanOrEqualClauseParser();
        }

        if(Arrays.asList(RSQLOperators.IN.getSymbols()).contains(operationSymbol)) {
            return new InClauseParser();
        }

        if(Arrays.asList(RSQLOperators.NOT_IN.getSymbols()).contains(operationSymbol)) {
            return new NotInClauseParser();
        }

        if(Arrays.asList(RSQLOperators.NOT_EQUAL.getSymbols()).contains(operationSymbol)) {
            return new NotEqualClauseParser();
        }
        return null;
    }

}
