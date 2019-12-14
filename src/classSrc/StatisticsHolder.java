package classSrc;

//data structure to hold important statistics for a certain measurement (i.e. assignment grades)
public class StatisticsHolder {
	
	private Double average, median, max, min;
	
	public StatisticsHolder() {
		average = 0.0;
		median = 0.0;
		max = 0.0;
		min = 0.0;
	}
	
	public StatisticsHolder(Double average_, Double median_, Double min_, Double max_) {
		average = average_;
		median = median_;
		max = max_;
		min = min_;
	}
	
	public void SetAverage(Double average_) {
		average = average_;
	}
	
	public void SetMin(Double min_) {
		min = min_;
	}
	
	public void SetMax(Double max_) {
		max = max_;
	}
	
	public void SetMedian(Double median_) {
		median = median_;
	}
	
	
	public Double GetAverage() {
		return average;
	}
	
	public Double GetMin() {
		return min;
	}
	
	public Double GetMax() {
		return max;
	}
	
	public Double GetMedian() {
		return median;
	}

}
