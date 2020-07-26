package Lesson_7;

import java.util.LinkedList;

public abstract class AbstractFirstPath {

    public abstract boolean hasPathTo(int dist);
    public abstract LinkedList<Integer> pathTo(int dist);
}
