import java.awt.*;
import java.lang.reflect.GenericArrayType;

public class Car {

    private Color color;
    private ParkingSpace parkingSpace;

    public Car(Color color,ParkingSpace parkingSpace){
        this.color = color;
        this.parkingSpace = parkingSpace;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(parkingSpace.getX() + 5,parkingSpace.getY()+10,90,105);
    }
}
