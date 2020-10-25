import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

//package com.motaharinia.config.graphql;
//
//import graphql.ExceptionWhileDataFetching;
//import graphql.GraphQLError;
//import graphql.kickstart.execution.graphql.GraphQLErrorHandler;
//import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {
//
//    @Override
//    public List<GraphQLError> processErrors(List<GraphQLError> list) {
//        System.out.println("processErrors :: ");
//        return list.stream().map(this::getNested).collect(Collectors.toList());
//    }
//
//    private GraphQLError getNested(GraphQLError graphql) {
//        System.out.println("getNested graphql before  :: " + graphql.getMessage());
//        if (graphql instanceof ExceptionWhileDataFetching) {
//            System.out.println("getNested graphql after :: " + graphql.getMessage());
//            ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) graphql;
//            if (exceptionError.getException() instanceof GraphQLError) {
//                System.out.println("getNested GraphQLError :: " + exceptionError.getException().getMessage());
//                return (GraphQLError) exceptionError.getException();
//            }
//        }
//        return graphql;
//    }
//}
