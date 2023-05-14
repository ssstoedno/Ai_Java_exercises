import java.util.ArrayList;
import java.util.Random;

public class Chromosome {
	boolean[] genes;
	int chr_weight;
	int chr_fitness;
	
	//constructor
	Chromosome(){
		Random rand=new Random();
		genes=new boolean[KnapSackProblem.items.size()];
		for (int i=0;i<genes.length;i++) {
			genes[i]=rand.nextBoolean();
		}
		chr_weight=0;
		chr_fitness=0;
	}
	
	//copy constructor
	Chromosome(Chromosome other){
		chr_weight=other.chr_weight;
		chr_fitness=other.chr_fitness;
		genes=new boolean[other.genes.length];
		for(int i=0;i<other.genes.length;i++) {
			genes[i]=other.genes[i];
		}
	}
	
	//fitness function
	void fitness() {
		int totalValue=0;
		int totalWeight=0;
		ArrayList<Integer>indexesOfIncludedItems=new ArrayList<>();
		Random rnd=new Random();
		
		for(int i=0;i<genes.length;i++) {
			if(genes[i]==true) {
				totalValue=totalValue+KnapSackProblem.items.get(i).value;
				totalWeight=totalWeight+KnapSackProblem.items.get(i).weight;
				indexesOfIncludedItems.add(i);
			}
		}
		if (totalWeight>KnapSackProblem.capacityKS) {
			while(totalWeight>KnapSackProblem.capacityKS) {
				int indexOfArrayList=rnd.nextInt(indexesOfIncludedItems.size());
				int indexOfInclItem=indexesOfIncludedItems.get(indexOfArrayList);
				genes[indexOfInclItem]=false;
				indexesOfIncludedItems.remove(indexOfArrayList);
				totalValue=0;
				totalWeight=0;
				for(int i=0;i<genes.length;i++) {
					if(genes[i]==true) {
						totalValue=totalValue+KnapSackProblem.items.get(i).value;
						totalWeight=totalWeight+KnapSackProblem.items.get(i).weight;
						}
				}
			}
		}
		chr_fitness=totalValue;
		chr_weight=totalWeight;
	}
	
	//single point crossover with 1 child and 100% crossover ratio 
	Chromosome crossover(Chromosome partner) {
		Random random=new Random();
		Chromosome child=new Chromosome();
		
		int mid=random.nextInt(genes.length-1);
		
		for(int i=0;i<genes.length;i++) {
			if (i<=mid) {
				child.genes[i]=genes[i];
			}
			else {
				child.genes[i]=partner.genes[i];
			}
		}
		return child;
	}
	
	//mutation with mutationRate 1%
	void mutate(float mutationRate) {
		Random random=new Random();
		for (int i=0;i<genes.length;i++) {
			if(random.nextFloat()<mutationRate) {
				if (genes[i]==true) {
					genes[i]=false;
				}
				else {
					genes[i]=true;
				}
			}
		}
	}
}