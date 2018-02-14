package com.chinapex.nexus.exception;

/**
 * created by pengmingguo on 2/14/18
 */
public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
