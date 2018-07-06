package AES;

public class KeySchedule {
    public Word[] Rcon;
    public Word[] original_key = new Word[4];
    public Word[] roundKeys = new Word[44];

    public KeySchedule(Word[] key) {
        if (key != null) {
            initializeRcon();
            original_key = key;
            keyExpansion();
        } else {
            System.exit(1);
        }
    }

    private void keyExpansion() {
        for (int i = 0; i < 4; i++) {
            roundKeys[i] = original_key[i];
        }
        for (int i = 4; i < roundKeys.length; i++) {
            Word temp = roundKeys[i-1];
            if (Math.floorMod(i,4) == 0) {
                temp = temp.rotWord().subWord().exclusiveOR(Rcon[i/4-1]);
            }
            roundKeys[i] = roundKeys[i-4].exclusiveOR(temp);
        }
    }

    private void initializeRcon() {
        Rcon = new Word[10];
        Byte blank = new Byte(0);
        for (int i = 0; i < 8; i++) {
            int temp = (int) Math.pow(2,i);
            Rcon[i] = new Word(new Byte(temp),blank,blank,blank);
        }
        Rcon[8] = new Word(new Byte(0x1B),blank,blank,blank);
        Rcon[9] = new Word(new Byte(0x36),blank,blank,blank);
    }

    public void print() {
        System.out.println("Key schedule as follows:");
        for (int i = 0; i < roundKeys.length; i++) {
            if (i == 0) {
                System.out.print("Original key: ");
            } else if (Math.floorMod(i,4) == 0) {
                System.out.print("\nRound key " + i/4 + ": ");
            } else {
                System.out.print(" ");
            }
            System.out.print(roundKeys[i].toString());
        }
        System.out.println();
    }
}
