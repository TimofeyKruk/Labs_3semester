import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;

public class MyFirstApplet extends Applet {
    private ArrayList<Pair> data = new ArrayList<>();
    int quantity = 0;
    int WIN_WIDTH = 1060;
    int WIN_HEIGHT = 660;
    int MIDDLE = (WIN_HEIGHT) / 2;
    int coefficientX = (WIN_WIDTH - 60) / 100;
    int coefficientY = (WIN_HEIGHT - 60) / 140;

    public void init() {
        String inputData;
        setSize(WIN_WIDTH, WIN_HEIGHT);


        while ((inputData = getParameter(String.format("param_%d", quantity++))) != null) {
            String[] words = inputData.split(" ");

            data.add(new Pair(words[0],words[1]));
        }

        //Sorting
        data.sort(new CompareByHumidity());
    }

    public void paint(Graphics graphics) {
        paintAxes(graphics);
        graphics.setColor(Color.RED);


        for (int i = 0; i < quantity - 1; ++i) {

            int x1 = (int) (Double.parseDouble(data.get(i).getHumidity()) * coefficientX);
            int y1 = (int) (Double.parseDouble(data.get(i).getTemperature()) * coefficientY);
            int x2 = (int) (Double.parseDouble(data.get(i+1).getHumidity()) * coefficientX);
            int y2 = (int) (Double.parseDouble(data.get(i+1).getTemperature()) * coefficientY);
            graphics.drawLine(x1+30, MIDDLE- y1, x2+30, MIDDLE - y2);

        }


    }

    public void paintAxes(Graphics graphics) {
       // setBounds(20, 20, WIN_WIDTH - 40, WIN_HEIGHT - 40);

        //X
        graphics.drawLine(30, WIN_HEIGHT / 2, WIN_WIDTH-30, WIN_HEIGHT / 2);
        for(int i=0;i<=100;i+=10)
        {
            graphics.drawString(String.format("%d%c",i,'%'),i*coefficientX,WIN_HEIGHT/2+15);
        }
        //Y
        graphics.drawLine(30, 30, 30, WIN_HEIGHT-60);

        for(int i=-70;i<=70;i+=10)
        {
            graphics.drawString(String.format("%d%c",i,'C'),0,WIN_HEIGHT/2-i*coefficientY);
        }

        graphics.setColor(Color.BLUE);
        graphics.drawString("H",WIN_WIDTH-20,WIN_HEIGHT/2+15);
        graphics.drawString("Temperature, C",0,15);
    }

}