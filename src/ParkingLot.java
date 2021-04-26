import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ParkingLot extends JFrame {
    private JPanel panel;
    private ArrayList<ParkingSpace> parkingSpaces;
    private ArrayList<Car> cars;
    private File input;

    public ParkingLot() {
        panel = new JPanel();
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        parkingSpaces = new ArrayList<>(9);
        input = new File("E:\\JavaSem2\\parkingLot\\src\\parkingLot.txt");
        readFile();
        update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int p = 1;
        for (int i = 100; i < 600; i += 100) {
            g.drawLine(i, 175, i, 425);
            if (i < 500) {
                g.drawString(String.valueOf(p++), i + 5, 185);
                g.drawString(String.valueOf(p++), i + 5, 425);
            }
        }
        g.drawLine(100, 300, 500, 300);
        if(cars != null){
            for(Car car : cars){
                car.draw(g);
            }
        }
    }

    private void createSpaces(){
        int p = 1;
        parkingSpaces.add(new ParkingSpace(0,0,0));
        for (int i = 100; i < 600; i += 100) {
            parkingSpaces.add(p,new ParkingSpace(i,175,p));
            p++;
            parkingSpaces.add(p,new ParkingSpace(i,300,p));
            p++;
        }

    }

    public void checkSpaces(){
        for(int i = 1; i < 9;i++){
            System.out.println(parkingSpaces.get(i).toString());
        }
    }

    public void update(){
        Thread thread = new Thread(()->{
            while (true){
                long timeStamp = input.lastModified();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(timeStamp != input.lastModified()){
                    readFile();
                    timeStamp = input.lastModified();
                    repaint();
                }
            }
        });
        thread.start();
    }

    public void readFile()  {

        BufferedReader reader;
        cars = new ArrayList<>(9);
        createSpaces();
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null){
                String[] description = line.split(", ");
                Color color;
                try {
                    Field field = Class.forName("java.awt.Color").getField(description[0]);
                    color = (Color)field.get(null);
                } catch (Exception e) {
                    color = Color.red;
                }
                int pspace = Integer.parseInt(description[1]);
                if(!parkingSpaces.get(pspace).getOccupied()){
                    Car c = new Car(color,parkingSpaces.get(pspace));
                    parkingSpaces.get(pspace).setOccupied(true);
                    cars.add(c);
                }else {
                    System.out.println("The " + Integer.parseInt(description[1])  + "th/st parking space is occupied");
                }
                line = reader.readLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
