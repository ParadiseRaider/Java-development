package Java_core.Lesson_1.marathon.Obstacles;

import Java_core.Lesson_1.marathon.Teams.Competitor;

public class Cross extends Obstacle {
    int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }
}