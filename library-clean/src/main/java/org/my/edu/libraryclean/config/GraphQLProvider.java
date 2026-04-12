package org.my.edu.libraryclean.config;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class GraphQLProvider {
    private GraphQL graphQL;

    public void init() throws IOException {
        File schemaFile = new File("library-clean/src/main/resources/graphql/schema.graphqls");
        String schema = FileUtils.readFileToString(schemaFile, "UTF-8");

        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schema);

        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("status",StatusFetchers.status()))
                .build();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);

        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
