package com.aston.capauto.exception;

public class PostWithIdException extends RuntimeException {

    private final String entityName;

    public PostWithIdException(String entityName) {
        super(String.format("A new %s cannot already have an ID in a create", entityName));
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
