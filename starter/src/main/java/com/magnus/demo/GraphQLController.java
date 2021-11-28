package com.magnus.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.magnus.graphql.servlet.ExecutionResultHandler;
import com.magnus.graphql.servlet.GraphQLInvocation;
import com.magnus.graphql.servlet.GraphQLInvocationData;
import com.magnus.graphql.servlet.components.GraphQLRequestBody;
import graphql.ExecutionResult;
import graphql.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@Internal
public class GraphQLController {

    @Autowired
    GraphQLInvocation graphQLInvocation;

    @Autowired
    ExecutionResultHandler executionResultHandler;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "${graphql.url:graphql}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object graphqlPOST(@RequestBody GraphQLRequestBody body,
                              WebRequest webRequest) {
        String query = body.getQuery();
        if (query == null) {
            query = "";
        }
        CompletableFuture<ExecutionResult> executionResult = graphQLInvocation.invoke(new GraphQLInvocationData(query, body.getOperationName(), body.getVariables()), webRequest);
        return executionResultHandler.handleExecutionResult(executionResult);
    }

    @RequestMapping(value = "${graphql.url:graphql}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object graphqlGET(
            @RequestParam("query") String query,
            @RequestParam(value = "operationName", required = false) String operationName,
            @RequestParam(value = "variables", required = false) String variablesJson,
            WebRequest webRequest) {
        CompletableFuture<ExecutionResult> executionResult = graphQLInvocation.invoke(new GraphQLInvocationData(query, operationName, convertVariablesJson(variablesJson)), webRequest);
        return executionResultHandler.handleExecutionResult(executionResult);
    }

    private Map<String, Object> convertVariablesJson(String jsonMap) {
        if (jsonMap == null) return Collections.emptyMap();
        try {
            return objectMapper.readValue(jsonMap, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not convert variables GET parameter: expected a JSON map", e);
        }

    }


}
