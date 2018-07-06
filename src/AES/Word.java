package AES;

public class Word {
    public Byte[] word;

    public Word() {
        word = new Byte[4];
    }

    public Word(Byte[] word) {
        if (word.length != 4) {
            System.out.println("Key error!");
            System.exit(1);
        }
        this.word = word;
    }

    public Word(Byte b0, Byte b1, Byte b2, Byte b3) {
        word = new Byte[4];
        word[0] = b0;
        word[1] = b1;
        word[2] = b2;
        word[3] = b3;
    }

    public Word rotWord() {
        Word temp = new Word();
        for (int i = 0; i < 4; i++) {
            temp.word[i] = word[Math.floorMod(i+1,4)];
        }
        return temp;
    }

    public Word subWord() {
        Word temp = new Word();
        for (int i = 0; i < 4; i++) {
            temp.word[i] = AES._instance.subByte(word[i]);
        }
        return temp;
    }

   public  Word exclusiveOR(Word w) {
        Word temp = new Word();
        for (int i = 0; i < 4; i++) {
            temp.word[i] = word[i].exclusiveOR(w.word[i]);
        }
        return temp;
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < 4; i++) {
            temp += word[i].toHexaString();
        }
        return temp;
    }
}
