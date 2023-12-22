package com.example.aoprojekt;

import java.util.Collection;
import java.util.Collections;

public class Common {

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static <S> boolean isNullOrEmpty(Collection<S> collection) {
        return collection == null || collection.isEmpty();
    }
}
