public enum State
{
    Player(5),
    Computer(1),
    Empty(0);

    private int value;

    State(int value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        switch (this)
        {
            case Empty:
                return "-";
            case Player:
                return "X";
            case Computer:
                return "O";
            default: return null;
        }
    }

    public int getValue() {return value;}
}
