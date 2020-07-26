package Java_core.Lesson_1.marathon.Obstacles;

import Java_core.Lesson_1.marathon.Teams.Competitor;
import Java_core.Lesson_1.marathon.Teams.Team;

public class Course {
    Obstacle[] obs;

    public Course(Obstacle... obstacle) {
        obs = obstacle;
    }

    public void doIt(Team team) {
        Competitor[] comm = team.getCom();
        for (int i=0;i<comm.length;i++) {
            for (int j=0;j<obs.length;j++) {
                obs[j].doIt(comm[i]);
                if (!comm[i].isOnDistance()) break;
            }
        }
    }
}
