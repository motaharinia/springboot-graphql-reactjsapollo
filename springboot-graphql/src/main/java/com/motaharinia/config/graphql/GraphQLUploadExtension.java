//package com.motaharinia.config.graphql;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.fasterxml.jackson.databind.node.POJONode;
//import graphql.schema.*;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//
///**
// * This extension implements https://github.com/jaydenseric/graphql-multipart-request-spec for graphql-java package.
// * <p>
// * 1. Register the scalar to the {@link graphql.schema.idl.RuntimeWiring.Builder}
// * <p>
// * <code>runtimeWiringBuilder.scalar(GraphQLUploadExtension.getScalarType(MultipartFile.class));</code>
// * <p>
// * 2. In Spring boot controller, where  httpServletRequest is type of MultipartHttpServletRequest.
// * <p>
// * <code>
// * final Map<String, Object> operationsMap = GraphQLUploadExtension.process(operations, map, httpServletRequest::getFile);
// * </code>
// */
//public class GraphQLUploadExtension {
//
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    private static final TypeReference<Map<String, List<String>>> MAP_REFERENCE = new TypeReference<>() {
//    };
//    private static final TypeReference<Map<String, Object>> TREEMAP_REFERENCE = new TypeReference<>() {
//    };
//
//    public static <T> GraphQLScalarType getScalarType(Class<T> clazz) {
//        // modified from the ApolloScalars in com.graphql-java-kickstart to make it support any Pojo type.
//        return GraphQLScalarType.newScalar()
//                .name("Upload")
//                .description("A file part in a multipart request")
//                .coercing(new Coercing<T, Void>() {
//                    @Override
//                    public Void serialize(Object dataFetcherResult) {
//                        throw new CoercingSerializeException("Upload is an input-only type");
//                    }
//
//                    @Override
//                    public T parseValue(Object input) {
//                        if (null == input) {
//                            return null;
//                        } else if (clazz.isAssignableFrom(input.getClass())) {
//                            //noinspection unchecked
//                            return (T) input;
//                        } else {
//                            throw new CoercingParseValueException(String.format("Expected type %s but was %s.", clazz.getName(), input.getClass().getName()));
//                        }
//                    }
//
//                    @Override
//                    public T parseLiteral(Object input) {
//                        throw new CoercingParseLiteralException("Must use variables to specify Upload values");
//                    }
//                })
//                .build();
//    }
//
//    public static <T> Map<String, Object> process(final String operations, final String map, Function<String, T> getFileFunction) throws IOException {
//        final Map<String, List<String>> mapping = OBJECT_MAPPER.readValue(map, MAP_REFERENCE);
//
//        final JsonNode tree = OBJECT_MAPPER.readTree(operations);
//        for (final Map.Entry<String, List<String>> entry : mapping.entrySet()) {
//            T file = getFileFunction.apply(entry.getKey());
//
//            for (final String objectPath : entry.getValue()) {
//                String[] path = objectPath.split("\\.");
//
//                JsonNode node = tree;
//                for (int i = 0; i < path.length - 1; i++) {
//                    node = node.path(path[i]);
//                    if (node.isNull() || node.isMissingNode()) {
//                        throw new IllegalStateException(String.format("Accessing an undefined node %s (%d) at object path %s.", path[i], i, objectPath));
//                    }
//                }
//
//                final String fieldName = path[path.length - 1];
//                try {
//                    int index = Integer.parseInt(fieldName);
//                    // is a number, array is expected
//                    if (!node.isArray()) {
//                        throw new IllegalStateException(String.format("Expecting an ArrayNode at %s, got %s.", objectPath, node.getNodeType().toString()));
//                    }
//
//                    if (!node.path(index).isNull()) {
//                        throw new IllegalStateException(String.format("Expecting a NullNode as placeholder at object path %s.", objectPath));
//                    }
//
//                    final ArrayNode arrayNode = (ArrayNode) node;
//                    arrayNode.set(index, new POJONode(file));
//                } catch (final NumberFormatException e) {
//                    // not a number, object is expected
//                    if (!node.isObject()) {
//                        throw new IllegalStateException(String.format("Expecting an ObjectNode at %s, got %s.", objectPath, node.getNodeType().toString()));
//                    }
//
//                    if (!node.path(fieldName).isNull()) {
//                        throw new IllegalStateException(String.format("Expecting a NullNode as placeholder at object path %s.", objectPath));
//                    }
//
//                    final ObjectNode objectNode = (ObjectNode) node;
//                    objectNode.set(fieldName, new POJONode(file));
//                }
//            }
//        }
//
//        return OBJECT_MAPPER.readValue(OBJECT_MAPPER.treeAsTokens(tree), TREEMAP_REFERENCE);
//    }
//}
