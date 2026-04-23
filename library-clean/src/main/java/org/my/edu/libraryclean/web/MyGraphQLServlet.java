package org.my.edu.libraryclean.web;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.my.edu.libraryclean.config.GraphQLProvider;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/graphql")
public class MyGraphQLServlet extends HttpServlet {
    private final GraphQL graphQL;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MyGraphQLServlet() {
        try {
            GraphQLProvider provider = new GraphQLProvider();
            provider.init();
            this.graphQL = provider.getGraphQL();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize GraphQL", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Map<String, Object> requestBody = objectMapper.readValue(request.getReader(), Map.class);
            String query = (String) requestBody.get("query");
            Map<String, Object> variables = (Map<String, Object>) requestBody
                    .getOrDefault("variables", Map.of());

            ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                    .query(query)
                    .variables(variables)
                    .build();
            ExecutionResult executionResult = graphQL.execute(executionInput);

            Map<String, Object> result = Map.of(
                    "data", executionResult.getData(),
                    "errors", executionResult.getErrors()
            );

            objectMapper.writeValue(response.getWriter(), result);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(response.getWriter(), Map.of("error", e.getMessage()));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("\"" +
                "<h1>GraphQL Endpoint</h1>" +
                "<p>Use POST requests with JSON body containing 'query' field.</p>" +
                "<p>Example: {\"query\": \"{ allUsers { id name } }\"}</p>" +
                "\""
        );
    }
}