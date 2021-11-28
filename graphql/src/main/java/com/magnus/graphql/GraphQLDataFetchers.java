package com.magnus.graphql;

import graphql.com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
/**
 * DataFetcher是graph中最重要的概念，每个query中的field都和关联一个dataFetch
 */
public class GraphQLDataFetchers {

    //也可以是数据库或者外部服务
    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "totalPages", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "totalPages", "635",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "totalPages", "371",
                    "authorId", "author-3")
    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            //backendBookId 前端传入的参数值 .getArgument指前端传入的参数名称
            String backendBookId = dataFetchingEnvironment.getArgument("inputBookid");
            return books
                    .stream()
                    .filter(book -> book.get("id").equals(backendBookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            //此时book的primitive信息已经找到了，缺少book中auth信息
            Map<String,String> backendBook = dataFetchingEnvironment.getSource();
            String authorId = backendBook.get("authorId");
            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getPageCountDataFetcher() {
        return dataFetchingEnvironment -> {
            //book代表后端返上来的数据
            Map<String,String> backendBook = dataFetchingEnvironment.getSource();
            return backendBook.get("totalPages");
        };
    }
}