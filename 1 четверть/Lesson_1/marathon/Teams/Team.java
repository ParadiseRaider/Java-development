package Java_core.Lesson_1.marathon.Teams;

public class Team {
    Competitor[] com;

    public Team(Competitor... competitors) {
        com = competitors;
    }

    public void showResult() {
        for (int i=0;i<com.length;i++) {
            com[i].info();
        }
    }

    public Competitor[] getCom() {
        return com;
    }
}
