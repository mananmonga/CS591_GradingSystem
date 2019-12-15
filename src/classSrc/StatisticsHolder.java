package classSrc;

import java.math.BigDecimal;

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
		BigDecimal bg = new BigDecimal(average);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double GetMin() {
		BigDecimal bg = new BigDecimal(min);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double GetMax() {
		BigDecimal bg = new BigDecimal(max);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double GetMedian() {
		BigDecimal bg = new BigDecimal(median);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
