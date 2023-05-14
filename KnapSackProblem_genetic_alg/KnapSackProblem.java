import java.util.ArrayList;

public class KnapSackProblem {
	static int capacityKS=5000;
	
	static ArrayList<Cell>items;
	static int totalItemWeight;
	
	static int popmax;
	static float mutationRate;
	static Population population; 
	
	//generating the knapsack problem with specific items
	static void generateKnapSackProblem(ArrayList<Cell>it){
		items=new ArrayList<>(it);
		int crrWeight=0;
		for (int i=0;i<items.size();i++) {
			crrWeight=crrWeight+items.get(i).weight;
		}
		totalItemWeight=crrWeight;
	}
	
	//creating a first population --popmax:population size-- mutationRate:1%
	static void setup() {
		popmax = 200;
		mutationRate = (float) 0.01;
		population=new Population(mutationRate,popmax);
	}
	
	//creating new generations
	static void newGeneration() {
		//creating the mate pool
		population.rouletteWheelSelection();
		//creating the new population using the mate pool
		population.generate();
		//calculating the fitness values for the new population
		population.calculateFitness();
	}
	
	
	//test
	public static void main(String[] args) {
   	 ArrayList<Cell>items=new ArrayList<>();
   	Cell cell0=new Cell(90,150);
   	Cell cell1=new Cell(130,35);
   	Cell cell2=new Cell(1530,200);
   	Cell cell3=new Cell(500,160);
   	Cell cell4=new Cell(150,60);
   	Cell cell5=new Cell(680,45);
   	Cell cell6=new Cell(270,60);
   	Cell cell7=new Cell(390,40);
   	Cell cell8=new Cell(230,30);
   	Cell cell9=new Cell(520,10);
   	Cell cell10=new Cell(110,70);
   	Cell cell11=new Cell(320,30);
   	Cell cell12=new Cell(240,15);
   	Cell cell13=new Cell(480,10);
   	Cell cell14=new Cell(730,40);
   	Cell cell15=new Cell(420,70);
   	Cell cell16=new Cell(430,75);
   	Cell cell17=new Cell(220,80);
   	Cell cell18=new Cell(70,20);
   	Cell cell19=new Cell(180,12);
   	Cell cell20=new Cell(40,50);
   	Cell cell21=new Cell(300,10);
   	Cell cell22=new Cell(900,1);
   	Cell cell23=new Cell(2000,150);
   	
   	items.add(cell0);
	items.add(cell1);
	items.add(cell2);
	items.add(cell3);
	items.add(cell4);
	items.add(cell5);
	items.add(cell6);
	items.add(cell7);
	items.add(cell8);
	items.add(cell9);
	items.add(cell10);
	items.add(cell11);
	items.add(cell12);
	items.add(cell13);
	items.add(cell14);
	items.add(cell15);
	items.add(cell16);
	items.add(cell17);
	items.add(cell18);
	items.add(cell19);
	items.add(cell20);
	items.add(cell21);
	items.add(cell22);
	items.add(cell23);
	
	generateKnapSackProblem(items);
	setup();
	while(true) {
		if (population.getGenerations()==2000) {
			population.getMax();
			break;
		}
		newGeneration();
	}
	
   	 
    }
}
