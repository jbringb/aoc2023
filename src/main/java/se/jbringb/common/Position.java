package se.jbringb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Position {
    private int y;
    private int x;
    private Direction direction;

    public void move(int x, int y) {
        this.y += y;
        this.x += x;
    }
}