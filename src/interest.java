import java.lang.Math;	

final public class interest{

	public String string;
	
	double calculateInterest(double principle, double rate, double compounding_period, double time, boolean continuous) {
		
		if(!continuous) {
			double final_amount = principle*Math.pow(1+rate/compounding_period, compounding_period*time);
			return final_amount;
		}
		else {
			return principle*Math.exp(rate*time);
		}
		
		}
}
