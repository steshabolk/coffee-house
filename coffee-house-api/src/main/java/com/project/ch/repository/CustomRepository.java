package com.project.ch.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> {

    void refresh(T t);
}
