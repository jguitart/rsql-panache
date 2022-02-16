package org.jguitart.rsql.panache;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.jguitart.rsql.panache.model.SampleEntity;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RsqlParseTest {




    @Test
    void parseEqualsOperationTest() {
        String name = "john";
        int size = 15;
        String operation = "name=="+name+" and size=="+size;
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("name = "));
        assertTrue(actual.getQuery().contains("size = "));
        assertTrue(actual.getQuery().contains(" and "));
        assertEquals(2, actual.getParams().size());
        assertTrue(actual.getParams().containsValue(name));
        assertTrue(actual.getParams().containsValue(size));
    }

    @Test
    void parseLikeOperationTest() {
        String name = "hn";
        int size = 15;
        String operation = "name==*"+name+"* and size=="+size;
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("name like "));
        assertTrue(actual.getQuery().contains("size = "));
        assertTrue(actual.getQuery().contains(" and "));
        assertTrue(actual.getParams().containsValue("%"+name+"%"));
        assertTrue(actual.getParams().containsValue(size));
    }

    @Test
    void parseGreaterThanOperationSymbolTest() {
        int size = 15;
        String operation = "size>"+size;
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("size > "));
        assertTrue(actual.getParams().containsValue(size));
    }

    @Test
    void parseGreaterThanOperationLettersTest() {
        int size = 15;
        String operation = "size=gt="+size;
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("size > "));
        assertTrue(actual.getParams().containsValue(size));
    }

    @Test
    void parseGreaterThanOrEqualOperationTest() {
        int size = 15;
        String operation = "size=ge="+size;
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("size >= "));
        assertTrue(actual.getParams().containsValue(size));
    }

    @Test
    void parseLessThanOperationTest() {
        int size = 15;
        String operation = "size=lt="+size;
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("size < "));
        assertTrue(actual.getParams().containsValue(size));
    }

    @Test
    void parseLessThanOrEqualOperationTest() {
        int size = 15;
        String operation = "size=le="+size;
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("size <= "));
        assertTrue(actual.getParams().containsValue(size));
    }

    @Test
    void parseInOperationTest() {
        String[] names = new String[] {"john", "max", "terry"};
        String operation = "name=in=("+String.join(",", names)+")";
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("name in "));
        assertEquals(1, actual.getParams().size());
        List<Object> values = (List<Object>) actual.getParams().values().iterator().next();
        assertArrayEquals(names, values.toArray());
    }

    @Test
    void parseNotInOperationtest() {
        String[] names = new String[] {"john", "max", "terry"};
        int size = 15;
        String operation = "name=out=("+String.join(",", names)+")";
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("name not in "));
        assertEquals(1, actual.getParams().size());
        List<Object> values = (List<Object>) actual.getParams().values().iterator().next();
        assertArrayEquals(names, values.toArray());
    }

    @Test
    void parseNotEqualOperationTest() {
        String name = "john";
        int size = 15;
        String operation = "name!="+name+" and size!="+size;
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("name not like "));
        assertTrue(actual.getQuery().contains("size not like "));
        assertTrue(actual.getQuery().contains(" and "));
        assertEquals(2, actual.getParams().size());
        assertTrue(actual.getParams().containsValue(name));
        assertTrue(actual.getParams().containsValue(size));
    }

    @Test
    void parseEnumValueOperationTest() {
        SampleEntity.EnumValue enumValue = SampleEntity.EnumValue.value1;
        String operation = "enumTest=="+enumValue.name();
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("enumTest = "));
        assertEquals(1, actual.getParams().size());
        assertTrue(actual.getParams().containsValue(enumValue));
    }

    @Test
    void parseInstantValueOperationTest() {
        long instantValue = System.currentTimeMillis();
        String operation = "instantTest=="+instantValue;
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("instantTest = "));
        assertEquals(1, actual.getParams().size());
        Object value = new ArrayList<>(actual.getParams().values()).get(0);
        assertTrue(value.getClass().isAssignableFrom(Instant.class));
        Instant actualValue = (Instant) value;
        assertEquals(instantValue, actualValue.toEpochMilli());
    }

    @Test
    void parseDateValueOperationTest() {
        long dateValue = System.currentTimeMillis();
        String operation = "dateTest=="+dateValue;
        SampleRsqlParser parser = new SampleRsqlParser();
        PanacheQueryDescriptor actual = parser.parseRsqlQuery(operation, SampleEntity.class);
        assertNotNull(actual);
        assertNotNull(actual.getQuery());
        assertFalse(actual.getQuery().isBlank());
        assertNotNull(actual.getParams());
        assertTrue(actual.getQuery().contains("dateTest = "));
        assertEquals(1, actual.getParams().size());
        Object value = new ArrayList<>(actual.getParams().values()).get(0);
        assertTrue(value.getClass().isAssignableFrom(Date.class));
        Date actualValue = (Date) value;
        assertEquals(dateValue, actualValue.getTime());
    }

}

class SampleRsqlParser extends PanacheRsqlParserBase<SampleEntity> {

    @Override
    public PanacheQuery<SampleEntity> build(PanacheQueryDescriptor queryDescriptor) {
        // Returns null because this implementation must be done in application.
        return null;
    }
}
