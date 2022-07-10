package project.service.interfaces;

public interface StatsService {
    /**
     * Player statistics
     * //
     */

    Integer statsPlayerCountStartGame(Integer id);

    Integer statsPlayerCountAllGame(Integer id);

    Integer statsPlayerAllGoal(Integer id);

    Integer statsGoalkeeperConcededGoal(Integer id);

    Integer statsPlayerYellowCard(Integer id);

    Integer statsPlayerRedCard(Integer id);

    String statsPlayerStartPercent(Integer id);

    /**
     * Team statistics
     */

    Integer statsTeamCountGame(Integer id);

    Integer statsTeamWinGame(Integer id);

    Integer statsTeamLoseGame(Integer id);

    Integer statsTeamDrawGame(Integer id);

    Integer statsTeamGoalScore(Integer id);

    Integer statsTeamGoalConc(Integer id);

    Integer statsTeamYellowCard(Integer id);

    Integer statsTeamRedCard(Integer id);

    String statsTeamLosePercent(Integer id);

    String statsTeamWinPercent(Integer id);

    String statsTeamDrawPercent(Integer id);
}
