package geometry;

public class GeometryTester{

	public GeometryTester() {
		
	}
	
	public boolean insideCircle(int circleCenterX, int circleCenterY, double radius, int x, int y) {
		double dist = Math.sqrt((x-circleCenterX)*(x-circleCenterX) + (y-circleCenterY)*(y-circleCenterY));
		if(dist < radius) {
			return true;
		}
		else return false;
	}
	
}
