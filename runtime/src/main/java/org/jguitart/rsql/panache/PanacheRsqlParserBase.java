package org.jguitart.rsql.panache;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

public abstract class PanacheRsqlParserBase<T extends PanacheEntityBase> {

    public PanacheQueryDescriptor parseRsqlQuery(String query, Class<T> entityClass) {
        // If query is null or blank, parser will return a void descriptor.
        // The extended class of this one will implement what to do with it.
        if(query == null || query.isBlank()) {
            return new PanacheQueryDescriptor();
        } else {
            Node rootNode = new RSQLParser().parse(query);
            return rootNode.accept(new PanacheRsqlVisitor<>(), entityClass);
        }
    }

    public PanacheQuery<T> parseQuery(String query, Class<T> entityClass) {
        PanacheQueryDescriptor descriptor = this.parseRsqlQuery(query, entityClass);
        return this.build(descriptor);
    }

    public abstract PanacheQuery<T> build(PanacheQueryDescriptor queryDescriptor);

}
