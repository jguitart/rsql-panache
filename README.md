# jguitart/rsql-panache

This is an extension to adapt rsql query parser (https://github.com/jirutka/rsql-parser) to quarkus panache extension.

## Dependencies
This extension needs quarkus panache and rsql-parser to work, so this extension will add those in you dependeny tree.

Add in you pom.xml the dependencies:

```
    <dependency>
      <groupId>cz.jirutka.rsql</groupId>
      <artifactId>rsql-parser</artifactId>
      <version>2.1.0</version>
    </dependency>
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-hibernate-orm-panache</artifactId>
    </dependency>
```

## Usage

The main way to use this extension is to ipmlement a RsqlParser.
This must extends PanacheRsqlParserBase<T>.

Basic example:
```
 public class FruitRsqlParser extends PanacheRsqlParserBase<Fruit> {

    // The parser doesn't take in account query sort, so if you want to define it, you should add it.
    Sort sort;

    public FruitRsqlParser(String sortColumn, boolean asc) {
        sort = asc ? Sort.by(sortColumn).ascending():Sort.by(sortColumn).descending();
    }

    // This method is called by parse method, and parse method calculates and passes queryDescriptor.
    // At the end it returns a panache Query ready to use.
    @Override
    public PanacheQuery<Fruit> build(PanacheQueryDescriptor queryDescriptor) {
        PanacheQuery<Fruit> result;
        if(queryDescriptor.getQuery()!=null && !queryDescriptor.getQuery().isBlank()) {
            result = Fruit.find(queryDescriptor.getQuery(), this.sort, queryDescriptor.getParams());
        } else {
            result = Fruit.findAll();
        }
        return result;
    }
}
```

Once this class is implemented then we can use it without any problem.

```
    String query = "name == apple || name == banana";
    FruitRsqlParser<Fruit> parser = new FruitRsqlParser("name", true); 
    PanacheQuery<Fruit> query = parser.parse(query);
    query.listAll();
```