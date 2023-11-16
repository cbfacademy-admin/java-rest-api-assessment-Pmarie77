package com.cbfacademy.apiassessment.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SavingsGoalsBaseService<T> {
    
        T create(T entity);

        Optional<T> findById(UUID id);

        List<T> findAll();

        T update(UUID id, T entity);

        void delete(UUID id);
}

