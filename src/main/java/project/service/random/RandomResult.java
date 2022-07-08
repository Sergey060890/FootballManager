package project.service.random;

import project.models.Player;

import java.sql.SQLException;
import java.util.Random;
import java.util.Set;

import static project.service.random.ConstansRandom.*;

public class RandomResult {
    public int randomGoal() {
        int i = MIN_GOAL + (int) (Math.random() * MAX_GOAL);
        return i;
    }

    public String randomResult(Integer gs, Integer gc) {
        String resultGame;
        if (gs > gc) {
            resultGame = WIN;
        } else if (gs == gc) {
            resultGame = DRAW;
        } else {
            resultGame = LOSE;
        }
        return resultGame;
    }

    public int randomYellowCardScore() {
        int[] myIntYC = new int[]{INT_0, INT_1, INT_1, INT_1,
                INT_2, INT_2, INT_2, INT_2, INT_2, INT_2, INT_3, INT_3};
        Random random = new Random();
        int n = myIntYC[random.nextInt(myIntYC.length)];
        return n;
    }

    public int randomRedCardScore() {
        int[] myIntRC = new int[]{INT_0, INT_0, INT_0, INT_0, INT_0,
                INT_0, INT_0, INT_0, INT_1, INT_1, INT_1, INT_1, INT_2};
        Random random = new Random();
        int n = myIntRC[random.nextInt(myIntRC.length)];
        return n;
    }

    public int timeRandomGoal() {
        int i = MIN_TIME + (int) (Math.random() * MAX_TIME);
        return i;
    }

    public int timeRandomYC() {
        int i = MIN_TIME + (int) (Math.random() * MAX_TIME);
        return i;
    }

    public int timeRandomRC() {
        int i = MIN_TIME + (int) (Math.random() * MAX_TIME);
        return i;
    }

    public int timeRandomSubs() {
        int i = MIN_TIME + (int) (Math.random() * MAX_TIME);
        return i;
    }

    public int countSubs() {
        int[] myIntSubs = new int[]{INT_1, INT_1, INT_2, INT_2, INT_2, INT_2, INT_3, INT_3,
                INT_3, INT_3, INT_3, INT_3, INT_3};
        Random random = new Random();
        int n = myIntSubs[random.nextInt(myIntSubs.length)];
        return n;
    }


    public Player create(Set<Player> players) {
        Player player;
        Random rnd = new Random();
        int i = rnd.nextInt(players.size());
        player = (Player) players.toArray()[i];
        return player;
    }

    public Player createNoGk(Set<Player> players) {
        Player player;
        Random rnd = new Random();
        players.removeIf(p -> p.getPosition().equals(GK));
        int i = rnd.nextInt(players.size());
        player = (Player) players.toArray()[i];
        return player;
    }

    public Player createGoalConcPlayer(Set<Player> players) {
        Player player = null;
        for (Player player1 : players
        ) {
            if (player1.getPosition().equals(GK)) {
                player = player1;
            }
        }
        return player;
    }

    public int attendance() {
        int[] myIntAtt = new int[]{ATTENDANCE, ATTENDANCE1, ATTENDANCE2,
                ATTENDANCE3, ATTENDANCE4, ATTENDANCE5, ATTENDANCE6,
                ATTENDANCE7, ATTENDANCE8, ATTENDANCE9,
                ATTENDANCE10};
        Random random = new Random();
        int n = myIntAtt[random.nextInt(myIntAtt.length)];
        return n;
    }


    public String referee() {
        String[] myIntRef = new String[]{REFEREE_1, REFEREE_2, REFEREE_3,
                REFEREE_4, REFEREE_5, REFEREE_6, REFEREE_7, REFEREE_8};
        Random random = new Random();
        String n = myIntRef[random.nextInt(myIntRef.length)];
        return n;
    }
}
