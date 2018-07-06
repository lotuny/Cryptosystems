package AES;

public class Byte {
    private int decimal_form;
    private int[] binary_form = new int[8];
    private char[] hexadecimal_form = new char[2];

    public Byte(int decimal) {
        decimal_form = decimal;
        binary_form = int2binary(decimal);
        hexadecimal_form = int2hexa(decimal);
    }

    public Byte(int[] binary) {
        decimal_form = binary2int(binary);
        binary_form = binary;
        hexadecimal_form = int2hexa(decimal_form);
    }

    public Byte(char[] hexadecimal) {
        decimal_form = hexa2int(hexadecimal);
        binary_form = int2binary(decimal_form);
        hexadecimal_form = hexadecimal;
    }

    private int[] int2binary(int integer) {
        int[] binary = new int[8];
        for (int i = 0; i < 8; i++) {
            if (integer == 0) {
                binary[i] = 0;
                continue;
            }
            binary[i] = Math.floorMod(integer,2);
            integer /= 2;
        }

        binary = transpose(binary);
        return binary;
    }

    private int hexa2int(char[] hexadecimal) {
        int integer = hexadecimal[0] + hexadecimal[1] * 16;
        return integer;
    }

    private int[] transpose(int[] original) {
        int[] result = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[original.length-i-1];
        }
        return result;
    }

    private int binary2int(int[] binary) {
        int integer = 0;
        for (int i = 0; i < 8; i++) {
            if (binary[i] == 1) {
                integer += Math.pow(2, 7-i);
            }
        }
        return integer;
    }

    private char[] int2hexa(int decimal) {
        if (decimal > 255) {
            System.out.println("value = " + decimal + " > 255    Value error!");
            System.exit(1);
        }

        char[] temp = new char[2];
        for (int i = 0; i < 2; i++) {
            if (decimal == 0) {
                temp[i] = hexaDigit(0);
                continue;
            }
            temp[i] = hexaDigit(Math.floorMod(decimal,16));
            decimal /= 16;
        }

        char[] hexa = new char[2];
        hexa[0] = temp[1];
        hexa[1] = temp[0];
        return hexa;
    }

    private char hexaDigit(int num) {
        switch (num) {
            case 0:
                return '0';
            case 1:
                return '1';
            case 2:
                return '2';
            case 3:
                return '3';
            case 4:
                return '4';
            case 5:
                return '5';
            case 6:
                return '6';
            case 7:
                return '7';
            case 8:
                return '8';
            case 9:
                return '9';
            case 10:
                return 'A';
            case 11:
                return 'B';
            case 12:
                return 'C';
            case 13:
                return 'D';
            case 14:
                return 'E';
            case 15:
                return 'F';
        }
        return '!';
    }

    public int getDecimal_form() {
        return decimal_form;
    }

    public char[] getHexadecimal_form() {
        return hexadecimal_form;
    }

    public int[] getBinary_form() {
        return binary_form;
    }

    public String toBinaryString() {
        String temp = "";
        for (int i = 0; i < 8; i++) {
            temp += binary_form[i];
        }
        return temp;
    }

    public String toHexaString() {
        String temp = "";
        for (int i = 0; i < 2; i++) {
            temp += hexadecimal_form[i];
        }
        return temp;
    }

    public Byte exclusiveOR(Byte b) {
        int temp = decimal_form ^ b.getDecimal_form();
        return new Byte(temp);
    }
}
