// Joseph Camarena
// 0588321
// jc0588321@swccd.edu

public class ShellSort {
	
	private Double[] data;
	long comparisons;
	
	public ShellSort (Double[] x) {
		
		comparisons = 0;
		data = new Double[x.length];
		for (int i = 0; i < x.length; i++) {
			
			this.data[i] = x[i];
		}
	}
	
	public void sortUsing (int[] increment) {
		
		int n = this.data.length;
		for (int k = 0; k < increment.length; k++) {
			
			int h = increment[k];
			for (int i = h; i < n; i++) {
			
				for (int j = i; j >= h && less(this.data[j], this.data[j-h]); j -= h) {
					exch(this.data, j, j-h);
				}
			}
		}
	}
	
	private boolean less(Double v, Double w) {
		comparisons++;
		return (v.compareTo(w) < 0);
	}
	
	private void exch (Double[] data, int i, int j) {
		
		Double temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	public long getComparisons() {
		return this.comparisons;
	}
	
	public void resetComparisons() {
		this.comparisons = 0;
	}
}
