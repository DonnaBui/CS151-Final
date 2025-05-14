package prisoner;

import mvc.Utilities;

import java.io.Serializable;

public abstract class Strategy implements Serializable {
    protected Prisoner prisoner;
    protected StrategyType type;
    public static final int COUNT = 4;

    public Strategy(Prisoner p) {
        prisoner = p;
    }

    public StrategyType getType() {
        return type;
    }

    public void setPrisoner(Prisoner p) {
        prisoner = p;
    }

    enum StrategyType {
        COOPERATE,
        RANDOMLY_COOPERATE,
        CHEAT,
        TIT4TAT
    }
    public abstract boolean cooperate();

    static class Cooperate extends Strategy {
        public Cooperate(Prisoner p) {
            super(p);
            type = StrategyType.COOPERATE;
        }

        @Override
        public boolean cooperate() {
            return true;
        }
    }

    static class RandomlyCooperate extends Strategy {
        public RandomlyCooperate(Prisoner p) {
            super(p);
            type = StrategyType.RANDOMLY_COOPERATE;
        }

        @Override
        public boolean cooperate() {
            return Utilities.rng.nextBoolean();
        }
    }

    static class Cheat extends Strategy {
        public Cheat(Prisoner p) {
            super(p);
            type = StrategyType.CHEAT;
        }

        @Override
        public boolean cooperate() {
            return false;
        }
    }

    static class Tit4Tat extends Strategy {
        public Tit4Tat(Prisoner p) {
            super(p);
            type = StrategyType.TIT4TAT;
        }

        @Override
        public boolean cooperate() {
            return prisoner.isPartnerCheated();
        }
    }

}