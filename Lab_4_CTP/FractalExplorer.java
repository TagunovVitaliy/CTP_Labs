import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer{
    private JImageDisplay Jdisplay;
    private int s;
    private FractalGenerator Gen;
    private Rectangle2D.Double range = new Rectangle2D.Double(0,0,0,0);
            
    public static void main(String[] args)
    {
        FractalExplorer expl = new FractalExplorer(800);
        expl.createAndShowGUI();
        expl.drawFractal();
    }

    /**
     * @param size - The height and width of the window
     */
    public FractalExplorer(int size){
        s = size;
        Jdisplay = new JImageDisplay(s, s);
        Gen = new Mandelbrot();
        Gen.getInitialRange(range);
    }

    public void createAndShowGUI(){
        // создаем окно
        JFrame frame = new JFrame("Fractal Explorer");

        Jdisplay.addMouseListener(new mouse_listener());//добавляем фрактала
        frame.add(Jdisplay,BorderLayout.CENTER);	

        JButton button = new JButton("Reset");
        button.addActionListener(new act_listener());//создаем кнопку
        frame.add(button, BorderLayout.SOUTH);

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//кнопка выхода

       
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void drawFractal(){	//фукция отрисовки фрактала
        for(int i=0;i<s;i++)
        for (int j=0;j<s;j++){
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, s, i);
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, s, j);
            // вычисление количества итераций
            int iterations = Gen.numIterations(xCoord, yCoord);
            //установка цвета, если он есть
            if(iterations!=-1){
                float hue = 0.7f + (float) iterations / 200f;
                int color = Color.HSBtoRGB(hue, 1f, 1f);
                Jdisplay.drawPixel(i, j, color);
            }
            //если нет
            else Jdisplay.drawPixel(i, j, 0);
        }
          
         Jdisplay.repaint();//отрисовка картинки
    }

    private class act_listener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            Gen.getInitialRange(range);
            Jdisplay.clearImage();
            drawFractal();
        }
    }

    private class mouse_listener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            
            super.mouseClicked(e);//нахождение координат курсора
            int mouseX=e.getX();
            int mouseY=e.getY();

            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, s, mouseX);
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, s, mouseY);

            Gen.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            Jdisplay.clearImage();
            drawFractal();
        }
    }

}
