package Java_core.Lesson_1.marathon;

import Java_core.Lesson_1.marathon.Obstacles.Cross;
import Java_core.Lesson_1.marathon.Obstacles.Obstacle;
import Java_core.Lesson_1.marathon.Obstacles.Wall;
import Java_core.Lesson_1.marathon.Teams.Cat;
import Java_core.Lesson_1.marathon.Teams.Competitor;
import Java_core.Lesson_1.marathon.Teams.Dog;
import Java_core.Lesson_1.marathon.Teams.Human;
import Java_core.Lesson_1.marathon.Obstacles.Course;
import Java_core.Lesson_1.marathon.Teams.Team;

public class Main {
    public static void main(String[] args) {
        Course c = new Course(new Cross(80), new Wall(2), new Wall(1), new Cross(120));
        Team team = new Team(new Human("Боб"), new Cat("Барсик"), new Dog("Бобик"));
        c.doIt(team);
        team.showResult();
    }
}
