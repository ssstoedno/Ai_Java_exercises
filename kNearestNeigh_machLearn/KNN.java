import java.net.*;
import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class KNN {
	Vector<Point>trainingset=new Vector<>();
	Vector<Point>testset=new Vector<>();
	
	
	
	KNN(){
		trainingset=new Vector<>();
		testset=new Vector<>();
	}
	
	private Vector<Point> normalize(Vector<Point>set) {
		Vector<Double>sepalLengths=new Vector<>();
		Vector<Double>sepalWidths=new Vector<>();
		Vector<Double>petalLengths=new Vector<>();
		Vector<Double>petalWidths=new Vector<>();
		
		Vector<Point> normalizedSet=new Vector<>();
		
		for (int i=0;i<set.size();i++) {
			sepalLengths.add(set.get(i).sepalLength);
			sepalWidths.add(set.get(i).sepalWidth);
			petalLengths.add(set.get(i).petalLength);
			petalWidths.add(set.get(i).petalWidth);
		}
		
		int newMax=1;
		int newMin=0;
		
		double maxSepalL=Collections.max(sepalLengths);
		double minSepalL=Collections.min(sepalLengths);
		double maxSepalW=Collections.max(sepalWidths);
		double minSepalW=Collections.min(sepalWidths);
		double maxPetalL=Collections.max(petalLengths);
		double minPetalL=Collections.min(petalLengths);
		double maxPetalW=Collections.max(petalWidths);
		double minPetalW=Collections.min(petalWidths);
		
		for(int i=0;i<set.size();i++) {
			double normalizedSL=((set.get(i).sepalLength-minSepalL)/(maxSepalL-minSepalL))*(newMax-newMin);
			double normalizedSW=((set.get(i).sepalWidth-minSepalW)/(maxSepalW-minSepalW))*(newMax-newMin);
			double normalizedPL=((set.get(i).petalLength-minPetalL)/(maxPetalL-minPetalL))*(newMax-newMin);
			double normalizedPW=((set.get(i).petalWidth-minPetalW)/(maxPetalW-minPetalW))*(newMax-newMin);
			Point p=new Point(normalizedSL,normalizedSW,normalizedPL,normalizedPW,set.get(i).group);
			normalizedSet.add(p);
		}
		return normalizedSet;
	}
	
	int  max(int a,int b,int c) {
		if(a==b) {
			if(a>c) {
				return 0;
			}
			else {
				return c;
			}
		}
		if(a==c) {
			if(a>b) {
				return 0;
			}
			else {
				return b;
			}
		}
		if (b==c) {
			if(b>a) {
				return 0;
			}
			else {
				return a;
			}
		}
		int crr=Math.max(a,b);
		int answer=Math.max(crr,c);
		return answer;
	}
	
	public void kNearestNeigh(int k){
		//choosing 20 random for testset
		for(int i=0;i<20;i++) {
			Random ran=new Random();
			int random=ran.nextInt(trainingset.size());
			testset.add(trainingset.get(random));
			trainingset.remove(random);
		}
		//correctly guessed class counter
		int correctGuessed=0;
		
		//normalize
		trainingset=normalize(trainingset);
		testset=normalize(testset);
		
		//for every one element of testset pick class using trainingset
		for(int i=0;i<testset.size();i++) {
			Vector<Double>distances=new Vector<>();
			double testsetSL=testset.get(i).sepalLength;
			double testsetSW=testset.get(i).sepalWidth;
			double testsetPL=testset.get(i).petalLength;
			double testsetPW=testset.get(i).petalWidth;
			String testgroup=testset.get(i).group;
			
			for (int j=0;j<trainingset.size();j++) {	
				double trsetSL=trainingset.get(j).sepalLength;
				double trsetSW=trainingset.get(j).sepalWidth;
				double trsetPL=trainingset.get(j).petalLength;
				double trsetPW=trainingset.get(j).petalWidth;
				trainingset.get(j).distance=Math.sqrt((trsetSL-testsetSL)*(trsetSL-testsetSL)
						+(trsetSW-testsetSW)*(trsetSW-testsetSW)
						+(trsetPL-testsetPL)*(trsetPL-testsetPL)
						+(trsetPW-testsetPW)*(trsetPW-testsetPW));
				distances.add(trainingset.get(j).distance);
			}
			
			HashSet<Double> minDistances=new HashSet<>();
			Vector<Point> minDistancePoints=new Vector<>();
			
			for (int l=0;l<k;l++) {
				double min=Collections.min(distances);
				minDistances.add(min);
				distances.remove(min);
			}
			
			for (int m=0;m<trainingset.size();m++) {
				if(minDistances.contains(trainingset.get(m).distance)) {
					minDistancePoints.add(trainingset.get(m));
					if (minDistancePoints.size()==k) {
						break;
					}
				}
			}
			
			int count=0;
			int countIrissetosa=0;
			int countIrisversicolor=0;
			int countIrisvirginica=0;
			
			while (count!=k) {
				Point p=minDistancePoints.get(count);
				
				if (p.group.equals("Iris-setosa")) {
					countIrissetosa++;
				}
				else if (p.group.equals("Iris-versicolor")) {
					countIrisversicolor++;
				}
				else {
					countIrisvirginica++;
				}
				count++;
			}
			
			int max=max(countIrissetosa,countIrisversicolor,countIrisvirginica);
			if (max==0) {	
				if (countIrissetosa==countIrisversicolor) {
					if(testgroup.equals("Iris-setosa")||testgroup.equals("Iris-versicolor")) {
						correctGuessed++;
					}
					testset.get(i).print();
					System.out.print(" belongs to "+"Iris-setosa"+" class or "+"Iris-versicolor");
					System.out.println();
				}
				else if(countIrisversicolor==countIrisvirginica) {
					if(testgroup.equals("Iris-versicolor")||testgroup.equals("Iris-virginica")) {
						correctGuessed++;
					}
					testset.get(i).print();
					System.out.print(" belongs to "+"Iris-versicolor"+" class or "+"Iris-virginica");
					System.out.println();
				}
				else {
					if(testgroup.equals("Iris-virginica")||testgroup.equals("Iris-setosa")) {
						correctGuessed++;
					}
					testset.get(i).print();
					System.out.print(" belongs to "+"Iris-virginica"+" class or "+"Iris-setosa");
					System.out.println();
				}
			}
			else {
				if (countIrissetosa==max) {
					if(testgroup.equals("Iris-setosa")) {
						correctGuessed++;
					}
					testset.get(i).print();
					System.out.print(" belongs to "+"Iris-setosa"+" class");
					System.out.println();
				}
				else if(countIrisversicolor==max) {
					if(testgroup.equals("Iris-versicolor")) {
						correctGuessed++;
					}
					testset.get(i).print();
					System.out.print(" belongs to "+"Iris-versicolor"+" class");
					System.out.println();
				}
				else {
					if(testgroup.equals("Iris-virginica")) {
						correctGuessed++;
					}
					testset.get(i).print();
					System.out.print(" belongs to "+"Iris-virginica"+" class");
					System.out.println();
				}
			}
		}
		double res=(double)(correctGuessed*100/testset.size());
		System.out.println("Accuracy: "+res+"%");
	}
	
	
	
	
	
	
	
	
	
	 public static void main(String[] args) throws Exception {
		   KNN test=new KNN();
		   
		   URL iris=new URL("http://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data");
		   URLConnection conn=iris.openConnection();
		   BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		   String input;
		   int count=0;
		   while(count!=150) {
			   input=in.readLine();
			   String[] splited = input.split(",");
			   Point p=new Point(splited[0],splited[1],splited[2],splited[3],splited[4]);
			   test.trainingset.add(p);
			   count++;
		   }
		   
		 System.out.print("Enter k(0-130): ");
      	 @SuppressWarnings("resource")
		 Scanner kIn=new Scanner(System.in);
      	 int k=kIn.nextInt();
      	 test.kNearestNeigh(k);
	 }
	 
}




