package org.jguitart.rsql.panache;

import java.util.LinkedHashMap;
import java.util.Map;

public class PanacheQueryDescriptor {

    String query;
    Map<String, Object> params;

    public PanacheQueryDescriptor() {
        query = "";
        params = new LinkedHashMap<>();
    }

    public PanacheQueryDescriptor(String query, Map<String, Object> params) {
        this.query = query;
        this.params = params;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
