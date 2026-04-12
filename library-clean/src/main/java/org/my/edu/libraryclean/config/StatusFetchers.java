package org.my.edu.libraryclean.config;

import graphql.schema.DataFetcher;
import org.my.edu.libraryclean.model.status.StatusApp;
import org.my.edu.libraryclean.resolver.StatusQueryResolver;

public class StatusFetchers {
    public static DataFetcher<StatusApp> status() {
        return dataFetchingEnvironment ->
                new StatusQueryResolver().status();
    }
}
