package com.magnus.graphql.servlet;

import graphql.Assert;
import graphql.PublicApi;

import java.util.Collections;
import java.util.Map;

@PublicApi
public class GraphQLInvocationData {

    private final String query;
    private final String operationName;
    private final Map<String, Object> variables;

    public GraphQLInvocationData(String query, String operationName, Map<String, Object> variables) {
        org.springframework.util.Assert.notNull(query, "query must be provided");
        this.query = query;
        this.operationName = operationName;
        this.variables = variables != null ? variables : Collections.emptyMap();
    }

    public String getQuery() {
        return query;
    }

    public String getOperationName() {
        return operationName;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }
}
