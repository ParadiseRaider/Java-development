package Java_core.Lesson_1.marathon.Obstacles;

import Java_core.Lesson_1.marathon.Teams.Competitor;

public class Water extends Obstacle {
    int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length);
    }
}