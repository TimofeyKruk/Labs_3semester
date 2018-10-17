package geometry;

//-----------TRIANGLE-----------
public abstract class Triangle {
    protected double angle;
    protected double firstSide, secondSide;

    //Constructors
    public Triangle() {
        this.angle = 0;
        this.firstSide = 0;
        this.secondSide = 0;
    }

    public Triangle(double angle, double firstSide, double secondSide) {
        this.angle = angle;
        this.firstSide = firstSide;
        this.secondSide = secondSide;
    }

    //Methods
    public double getFirstSide() {
        return firstSide;
    }

    public void setFirstSide(double firstSide) {
        this.firstSide = firstSide;
    }

    public double getSecondSide() {
        return secondSide;
    }

    public void setSecondSide(double secondSide) {
        this.secondSide = secondSide;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    //Abstract methods
    public abstract double area();

    public abstract double perimeter();
}

