package ua.com.alevel.dao;

import ua.com.alevel.entity.AbstractEntity;

import java.util.Set;

public interface AbstractEntityDAO<DATA extends AbstractEntity> {

    void create(DATA data);
    DATA read(int id);
    Set<DATA> read();
    void update(DATA data);
    void delete(int id);
}
