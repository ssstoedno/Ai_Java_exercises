import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {
	int numberChromosomes;
	float mutationRate;
	ArrayList<Chromosome>matingPool;
	Chromosome[] population;
	int generation;
	Chromosome maxValueInAllGenerations;
	
	//constructor with mutationRate and population size
	Population(float mR, int n){
		numberChromosomes=n;
		mutationRate=mR;
		generation=0;	
		population=new Chromosome[numberChromosomes];
		matingPool=new ArrayList<>();
		maxValueInAllGenerations=new Chromosome();
		
		for (int i=0;i<n;i++) {
			population[i]=new Chromosome();
		}
		calculateFitness();
	}
	
	//function to calculate fitness values of all chromosomes in a population
	void calculateFitness() {
		for(int i=0;i<population.length;i++) {
			population[i].fitness();
		}
	}
	
	//roulette wheel selection for the mating pool
	void rouletteWheelSelection() {
		matingPool.clear();
		int fsum=0;
		
		for(int i=0;i<population.length;i++) {
			fsum=fsum+population[i].chr_fitness;
			if (population[i].chr_fitness>maxValueInAllGenerations.chr_fitness) {
				maxValueInAllGenerations=new Chromosome(population[i]);
			}
		}
		
		Random rnd=new Random();
		while(matingPool.size()!=population.length) {
			int limit=rnd.nextInt(fsum);
			int PS=0;
			
			for(int i=0;i<population.length;i++) {
				PS=PS+population[i].chr_fitness;
				if(PS>=limit) {
					matingPool.add(population[i]);
					break;
				}
			}
		}
		Collections.shuffle(matingPool);
	}
	
	//creating new generation
	void generate() {
		Random rnd=new Random();
		for (int i=0;i<population.length;i++) {
			int a=rnd.nextInt(matingPool.size());
			int b=rnd.nextInt(matingPool.size());
			Chromosome parentA=matingPool.get(a);
			Chromosome parentB=matingPool.get(b);
			Chromosome child=parentA.crossover(parentB);
			child.mutate(mutationRate);
			population[i]=child;
		}
			generation++;
	}
	
	//return generation
	int getGenerations() {
	    return generation;
	  }
	
	//return answer--chromosome with max value and weight of less than 
	//5000g throughout all generations
	void getMax() {
		for (int i=0;i<population.length;i++) {
			if (population[i].chr_fitness>maxValueInAllGenerations.chr_fitness) {
				maxValueInAllGenerations=new Chromosome(population[i]);
			}
		}
		System.out.print("Value:");
		System.out.println(maxValueInAllGenerations.chr_fitness);
		System.out.print("Weight:");
		System.out.println(maxValueInAllGenerations.chr_weight);
		System.out.print("Binary string (1:chosen item, 0:not chosen item):");
		for (int i=0;i<maxValueInAllGenerations.genes.length;i++) {
			if (maxValueInAllGenerations.genes[i]==true) {
				System.out.print(1);
			}
			else {
				System.out.print(0);
			}
		}
	}
	
}
