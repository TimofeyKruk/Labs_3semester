public class Literal {
    private String literal;
    private int groupNumber;
    private int stringNumber;


    public Literal(String literal, int stringNumber) {
        this.literal = literal;
        this.groupNumber = Literal.whatGroup(literal);
        this.stringNumber = stringNumber;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getStringNumber() {
        return stringNumber;
    }

    public void setStringNumber(int stringNumber) {
        this.stringNumber = stringNumber;
    }

    public static int whatGroup(String token)
    {
        if(token.matches("^[-+]?\\d*$")) return 1;//is integer
        if(token.matches("(?:0[xX])?[0-9a-fA-F]+")) return 2;//is HEX
        if(token.matches("[+-]?([0-9]*[.])?[0-9]+")) return 3;//is float(double)
        if(token.matches("^([-+]?\\d*\\.?\\d+)(?:[eE]([-+]?\\d+))?$")) return 4;//is in E form
        return 5;//is a text
    }

    @Override
    public String toString() {
        return "Literal{" +
                "literal='" + literal + '\'' +
                ", groupNumber=" + groupNumber +
                ", stringNumber=" + stringNumber +
                '}';
    }
    public String toStringShort()
    {
        return  "literal= '" + literal + '\'' +
                ",  stringNumber=" + stringNumber;
    }
}
