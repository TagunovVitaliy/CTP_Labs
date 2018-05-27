import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator{
    // Maximum iterations:
    public static final int MAX_ITERATIONS = 2000;
    /**
     * @param range - The range borders of the fractal
     */
    public void getInitialRange (Rectangle2D.Double range){
        range.x=-2;
        range.y=-1.5;
        range.width=range.height=3;
    }

    //Use formulas for fractal Mandelbrot
    /**
     * @param x - The abscissa of the point
     * @param y - The ordinate of the point
     */
    public int numIterations(double x, double y){
        double re=0;
        double im=0;
        for (int i=0; i<MAX_ITERATIONS; i++){
            double nextRe=re*re-im*im+x;
            double nextIm=2*re*im+y;

            //“ак как мы достигли MAX_ITERATIONS возвращаем количество  итераций
            if ((im*im+re*re) > 4)
                return i;

            //»змен€ем мнимую и действительные части
            re=nextRe;
            im=nextIm;
        }
        return -1;
    }

}
