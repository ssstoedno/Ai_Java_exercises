
public class Point {
	double sepalLength;
	double sepalWidth;
	double petalLength;
	double petalWidth;
	String group;
	double distance;
	
	Point(String sepL, String sepW, String petalL, String petalW, String gr){
		group=gr;
		sepalLength=Double.parseDouble(sepL);
		sepalWidth=Double.parseDouble(sepW);
		petalLength=Double.parseDouble(petalL);
		petalWidth=Double.parseDouble(petalW);
	}
	
	Point(double sepL, double sepW, double petalL, double petalW, String gr){
		group=gr;
		sepalLength=sepL;
		sepalWidth=sepW;
		petalLength=petalL;
		petalWidth=petalW;
	}

	
	void print() {
		System.out.print(sepalLength+",");
		System.out.print(sepalWidth+",");
		System.out.print(petalLength+",");
		System.out.print(petalWidth+",");
		System.out.print(group);
	}
}
