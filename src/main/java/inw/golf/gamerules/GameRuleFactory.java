package inw.golf.gamerules;

public class GameRuleFactory {

    public GameRule[] CreateDefaultRuleSet() {
        return new GameRule[] {
                new RebirthRule(),
                new IsolationKillsRule(),
                new StayAliveRule(),
                new OvercrowdingKillsRule() };
    }
}


