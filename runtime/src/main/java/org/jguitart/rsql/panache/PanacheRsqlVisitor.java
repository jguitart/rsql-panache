package org.jguitart.rsql.panache;

import cz.jirutka.rsql.parser.ast.*;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.jguitart.rsql.panache.clauses.ClauseParserFactory;
import org.jguitart.rsql.panache.clauses.IClauseParser;
import org.jguitart.rsql.panache.exceptions.RsqlPanacheParserException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PanacheRsqlVisitor<T extends PanacheEntityBase> implements RSQLVisitor<PanacheQueryDescriptor, Class<T>> {

    ClauseParserFactory clauseParserFactory = new ClauseParserFactory();

    @Override
    public PanacheQueryDescriptor visit(AndNode andNode, Class<T> entityClass) {
        return this.processLogicalNode(andNode, entityClass, "and");
    }

    @Override
    public PanacheQueryDescriptor visit(OrNode orNode, Class<T> entityClass) {
        return this.processLogicalNode(orNode, entityClass, "or");
    }

    private PanacheQueryDescriptor processLogicalNode(LogicalNode node, Class<T> entityClass, String operation) {
        List<Node> childrens = node.getChildren();
        List<String> clauses = new ArrayList<>();
        Map<String, Object> params = new LinkedHashMap<>();
        for(Node children: childrens) {
            PanacheQueryDescriptor queryDescriptor = children.accept(this, entityClass);
            clauses.add(queryDescriptor.getQuery());
            params.putAll(queryDescriptor.getParams());
        }
        String query = String.join(String.format(" %s ", operation), clauses);
        return new PanacheQueryDescriptor(query, params);
    }

    @Override
    public PanacheQueryDescriptor visit(ComparisonNode comparisonNode, Class<T> entityClass) {

        IClauseParser parser = clauseParserFactory.getParser(comparisonNode.getOperator().getSymbol());
        try {
            return parser.parseClause(comparisonNode, entityClass);
        } catch (RsqlPanacheParserException e) {
            e.printStackTrace();
            return null;
        }

    }

}
