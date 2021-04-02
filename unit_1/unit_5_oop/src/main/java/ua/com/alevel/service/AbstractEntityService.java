package ua.com.alevel.service;

import ua.com.alevel.entity.AbstractEntity;

import java.util.Set;

public interface AbstractEntityService<DATA extends AbstractEntity> {

    void create(DATA data);
    DATA read(int id);
    Set<DATA> read();
    void update(DATA data);
    void delete(int id);
}
