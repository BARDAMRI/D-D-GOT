
    public class Position {
    private int x;
    private int y;

    public Position(int x, int y)
    {   this.x=x;
        this.y=y;
    }

    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public void setLocation(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    public boolean Equals(Position loc)
    {
        return this.x==loc.x & this.y==loc.y;
    }
    public double distance(Position loc)
    {
        double a= Math.pow( this.y-loc.y,2);
        double b=Math.pow(this.x-loc.x,2);
        return Math.sqrt(a+b);
    }

    //TODO:
    public void moveUp(){
        y++;
    }
    public void moveDown(){ y--; }
    public void moveRight(){ x++; }
    public void moveLeft(){ x--; }
}
