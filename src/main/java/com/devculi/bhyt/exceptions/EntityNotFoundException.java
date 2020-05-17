package com.devculi.bhyt.exceptions;

import org.springframework.util.StringUtils;

import java.util.Map;

import static com.devculi.bhyt.helpers.CollectionUtils.toMap;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class clazz, String... searchParamsMap) {
        super(
                EntityNotFoundException.generateMessage(
                        clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) + " was not found for parameters " + searchParams;
    }
}
