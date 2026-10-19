package com.globitel.task.management.system.core.shared.technologies;

/**
 * @author Khaled Diab
 * @at 5:59 PM, Sunday, 10/11/2026
 */
public interface TMSCache {
    void put(String key, String value);
    String get(String key);
    void remove(String key);
    boolean containsKey(String key);
}
