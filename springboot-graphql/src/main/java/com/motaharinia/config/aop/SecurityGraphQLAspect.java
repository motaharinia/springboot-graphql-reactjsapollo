package com.motaharinia.config.aop;

import com.motaharinia.business.service.BusinessExceptionEnum;
import com.motaharinia.config.graphql.GraphQLCustomException;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-22<br>
 * Time: 15:17:04<br>
 * Description:<br>
 */
@Aspect
@Component
@Order(1)
public class SecurityGraphQLAspect {

    /**
     * @AdminSecured annotated methods can be called only by admin authenticated user.
     */
    @Before("pointcutGraphQLMutation()")
    public void checkGraphQLMutation(JoinPoint jp) {
        String className = jp.getSignature().getDeclaringType().getName();
        String methodName = jp.getSignature().getName();
        System.out.println("Mutation Before controllerMethods class.method:"+ className+ "."+ methodName);
    }

//    @Around("pointcutGraphQLQuery()")
//    public void checkGraphQLQuery(ProceedingJoinPoint jp) throws Exception {
//        String className = jp.getSignature().getDeclaringType().getName();
//        String methodName = jp.getSignature().getName();
//        MethodSignature signature = (MethodSignature) jp.getSignature();
//        Method method = signature.getMethod();
//        GraphQLQuery myAnnotation = method.getAnnotation(GraphQLQuery.class);
//        System.out.println("Query Before controllerMethods class.method:"+ className+ "."+ methodName + "," + myAnnotation.name());
//        throw new GraphQLCustomException(BusinessExceptionEnum.AOP_ERROR, "sample description");
//    }

    /**
     * Any method annotated with @GraphQLMutation
     */
    @Pointcut("@annotation(io.leangen.graphql.annotations.GraphQLMutation)")
    private void pointcutGraphQLMutation() {
        //leave empty
    }
    /**
     * Any method annotated with @GraphQLQuery
     */
    @Pointcut("@annotation(io.leangen.graphql.annotations.GraphQLQuery)")
    private void pointcutGraphQLQuery() {
        //leave empty
    }
}