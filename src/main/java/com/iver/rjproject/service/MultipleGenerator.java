package com.iver.rjproject.service;

import java.util.List;

public interface MultipleGenerator<T> {
    List<T> generate(int size);
}
