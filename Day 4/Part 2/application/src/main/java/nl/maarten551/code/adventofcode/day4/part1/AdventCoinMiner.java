package nl.maarten551.code.adventofcode.day4.part1;

import nl.maarten551.code.adventofcode.day4.part1.thread.adventcoinminer.IHashKeyDeliverer;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdventCoinMiner implements IHashKeyDeliverer {
    private static Integer maximumAmountOfTries = 100000000;
    private static Pattern validCodeRegex = Pattern.compile("^000000.*");

    private HashMap<String, String> adventCodeHashes = new HashMap<String, String>();
    private Integer currentHashKey = -1;
    private String hashSalt;

    public AdventCoinMiner(String hashSalt) {
        this.hashSalt = hashSalt;
    }

    public String nextKeyToHash() {
        currentHashKey++;
        if(currentHashKey % 10000 == 0) {
            //System.out.println(String.format("Percentage of limit: %d%%", (int)((double)currentHashKey/(double)AdventCoinMiner.maximumAmountOfTries)*100));
            System.out.println(currentHashKey);
        }
        return this.hashSalt + this.currentHashKey.toString();
    }

    public void controlCreatedHash(String usedKey, String hash) {
        Matcher matcher = AdventCoinMiner.validCodeRegex.matcher(hash);
        if (matcher.find()) {
            this.adventCodeHashes.put(usedKey, hash);
        }
    }

    public boolean isOperationCompleted() {
        return !(this.adventCodeHashes.size() == 0 && this.currentHashKey <= AdventCoinMiner.maximumAmountOfTries);
    }

    public HashMap<String, String> getAdventCodeHashes() {
        return adventCodeHashes;
    }
}
