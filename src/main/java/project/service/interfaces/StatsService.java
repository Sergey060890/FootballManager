package project.service.interfaces;

public interface StatsService {
    /**
     * Stats player count start game
     */

    Integer statsPlayerCountStartGame(Integer id);

    /**
     * Stats player count all game
     */
    Integer statsPlayerCountAllGame(Integer id);

    /**
     * Stats player count all goal
     */
    Integer statsPlayerAllGoal(Integer id);

    /**
     * Stats player count all conceded goal
     */
    Integer statsGoalkeeperConcededGoal(Integer id);

    /**
     * Stats player count yellow card
     */
    Integer statsPlayerYellowCard(Integer id);

    /**
     * Stats player count red card
     */
    Integer statsPlayerRedCard(Integer id);

    /**
     * Stats player start game percent
     */
    String statsPlayerStartPercent(Integer id);

    /**
     * Stats team count game
     */
    Integer statsTeamCountGame(Integer id);

    /**
     * Stats team count win game
     */
    Integer statsTeamWinGame(Integer id);

    /**
     * Stats team count lose game
     */
    Integer statsTeamLoseGame(Integer id);

    /**
     * Stats team count draw game
     */
    Integer statsTeamDrawGame(Integer id);

    /**
     * Stats team count goal score
     */
    Integer statsTeamGoalScore(Integer id);

    /**
     * Stats team count goal conceded
     */
    Integer statsTeamGoalConc(Integer id);

    /**
     * Stats team count yellow card
     */
    Integer statsTeamYellowCard(Integer id);

    /**
     * Stats team count red card
     */
    Integer statsTeamRedCard(Integer id);

    /**
     * Stats team percent lose game
     */
    String statsTeamLosePercent(Integer id);

    /**
     * Stats team percent win game
     */
    String statsTeamWinPercent(Integer id);

    /**
     * Stats team percent draw game
     */
    String statsTeamDrawPercent(Integer id);
}
