package Java_core.Lesson_1.marathon.Obstacles;

import Java_core.Lesson_1.marathon.Teams.Competitor;

public class Wall extends Obstacle {
    int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.jump(height);
    }
}