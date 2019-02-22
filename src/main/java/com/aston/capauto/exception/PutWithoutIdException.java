package com.aston.capauto.exception;

public class PutWithoutIdException extends RuntimeException {

    private final String entityName;

    public PutWithoutIdException(String entityName) {
        super(String.format("Cannot update %s without an ID", entityName));
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
