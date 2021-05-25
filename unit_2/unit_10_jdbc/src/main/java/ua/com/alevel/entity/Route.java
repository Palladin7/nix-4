package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Route {
    private int id;
    private int from_id;
    private int to_id;
    private int cost;
}
