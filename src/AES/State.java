package AES;

public class State {
    public Byte[][] state;

    public State() {
        state = new Byte[4][4];
    }

    public State(Byte[][] state) {
        this.state = state;
    }

    public State subBytes() {
        State temp = new State();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp.state[i][j] = AES._instance.subByte(state[i][j]);
            }
        }
        return temp;
    }

    public State shiftRows() {
        State temp = new State();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp.state[i][j] = state[i][Math.floorMod(j+i,4)];
            }
        }
        return temp;
    }

    public State mixColumns() {
        State temp = new State();
        for (int i = 0; i < 4; i++) {
            int[] t = {state[i][0].getDecimal_form(),
                       state[i][1].getDecimal_form(),
                       state[i][2].getDecimal_form(),
                       state[i][3].getDecimal_form()};
            temp.state[i][0] = new Byte(Math.floorMod(t[0]*2, 255) ^ Math.floorMod(t[1]*3, 255) ^ t[2] ^t[3]);
            temp.state[i][1] = new Byte(Math.floorMod(t[1]*2, 255) ^ Math.floorMod(t[2]*3, 255) ^ t[3] ^t[0]);
            temp.state[i][2] = new Byte(Math.floorMod(t[2]*2, 255) ^ Math.floorMod(t[3]*3, 255) ^ t[0] ^t[1]);
            temp.state[i][3] = new Byte(Math.floorMod(t[3]*2, 255) ^ Math.floorMod(t[0]*3, 255) ^ t[1] ^t[2]);
        }
        return temp;
    }

    public State addRoundKey(Word[] roundKey) {
        State temp = new State();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp.state[i][j] = state[i][j].exclusiveOR(roundKey[i].word[j]);
            }
        }
        return temp;
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp += state[i][j].toHexaString();
            }
        }
        return temp;
    }
}
