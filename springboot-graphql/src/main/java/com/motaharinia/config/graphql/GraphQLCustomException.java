package com.motaharinia.config.graphql;

import com.motaharinia.business.service.BusinessExceptionEnum;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GraphQLCustomException extends RuntimeException implements GraphQLError {

    private BusinessExceptionEnum businessException;
    private String description;


    public GraphQLCustomException(BusinessExceptionEnum businessException, String description) {
        super(businessException.getValue());
        this.businessException = businessException;
        this.description = description;
    }

    @Override
    public String getMessage() {
        return this.description;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Map.of("businessException", businessException.getValue(), "description", description);
    }


//    private final ErrorInformation errorInformation;
//
//    public GraphQLExceptionWithErrorInformation(ErrorInformation errorInformation, String message) {
//        super(message);
//        this.errorInformation = errorInformation;
//    }
//
//    @Override
//    public List<SourceLocation> getLocations() {
//        return null;
//    }
//
//    @Override
//    public ErrorType getErrorType() {
//        return null;
//    }
//
//    @Override
//    public Map<String, Object> getExtensions() {
//        Map<String, Object> errorsMap = new HashMap<>();
//        errorsMap.put("errorType", errorInformation.toString());
//        errorsMap.put("message", errorInformation.getMessageKey());
//        return errorsMap;
//    }

}
