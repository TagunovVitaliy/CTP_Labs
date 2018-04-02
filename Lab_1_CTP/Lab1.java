import java.util.Scanner;


public class Lab1 {

	 static double computeArea(Point3d[] point)
	 {

	       double Area = 0;
	       double	a = point[0].distanceTo(point[1]);
		   double	b = point[1].distanceTo(point[2]);
		   double	c = point[2].distanceTo(point[0]);
		   double   s = (a+b+c)/2; 
		   Area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
		   

	        return Area;
	 }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  Scanner k = new Scanner(System.in);
	        double input = 0;
	        Point3d point[] = new Point3d[3];

	        for(int i = 0;i < 3; i++)
	        {
	            point[i] = new Point3d();
	            
	            System.out.println("X coordinate: ");
	            input = k.nextDouble();
	            point[i].setX(input);
	            
	            System.out.println("Y coordinate: ");
	            input = k.nextDouble();
	            point[i].setY(input);

	            System.out.println("Z coordinate: ");
	            input = k.nextDouble();
	            point[i].setZ(input);

	}
	        System.out.println("Answer: " + computeArea(point));
	}
