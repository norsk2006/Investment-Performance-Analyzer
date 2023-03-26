import java.lang.Math;	

final public class interest{

	
	double calculateInterest(double principle, double rate, double compounding_period, double time) {
		
			return principle*java.lang.Math.pow(1+rate/compounding_period,compounding_period*time);
		
		}
}
