import java.util.Random;

public class Dene {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		double rangeMin = 1000.0;
		double rangeMax = 8000.0;
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		
		System.out.println(randomValue); // displaying a random double value between 0.0 & 1.0
		Random rd = new Random(); // creating Random object
		System.out.println(rd.nextDouble()*100); // displaying a random double value between 0.0 & 1.0
		
		Random random = new Random();
		int randomInteger =  random.nextInt(5) + 1;
		
		System.out.println(randomInteger);
	}	
	


}
