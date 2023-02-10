package GlobalVar;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Globalvar {

    private final String agencies = "AAA001,BBB001,CCC001,DDD001,EEE001,FFF001,GGG001,HHH001,III001,JJJ001,KKK001,LLL001,MMM001,NNN001,OOO001,PPP001,QQQ001,RRR001,SSS001,TTT001,UUU001,VVV001,WWW001,XXX001,YYY001,ZZZ001";

    private final Set<String> agenciesSet = new HashSet<>(Arrays.asList("AAA001", "BBB001", "CCC001", "DDD001", "EEE001", "FFF001", "GGG001", "HHH001", "III001", "JJJ001", "KKK001", "LLL001", "MMM001", "NNN001", "OOO001", "PPP001", "QQQ001", "RRR001", "SSS001", "TTT001", "UUU001", "VVV001", "WWW001", "XXX001", "YYY001", "ZZZ001"));

    public String getAgencies() {
        return agencies;
    }

    public Set<String> getAgenciesSet() {
        return agenciesSet;
    }

    private Globalvar() {
    }

    private enum Global {
        INSTANCE;
        private final Globalvar instance;

        Global() {
            instance = new Globalvar();
        }

        private Globalvar getInstance() {
            return instance;
        }
    }

    public static Globalvar getInstance() {
        return Global.INSTANCE.getInstance();
    }
}
