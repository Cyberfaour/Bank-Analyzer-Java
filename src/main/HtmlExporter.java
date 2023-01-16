package main;

public class HtmlExporter implements Exporter{

    @Override
    public String export(SummaryStatistics summaryStatistics) {
        String result="<!doctype html>";
        result+="<html lang=en>";
        result+="<head><title>Bank transaction Report</title></head>";
        result+="<body>";
        result+="<h1 >Bank Transaction Report</h1>";
        result+="<ul>";
        result+="<li><strong>Total Sum of Transactions</strong>: "+summaryStatistics.getSum()+"</li>";
        result+="<li><strong>Average of Transactions</strong>: "+summaryStatistics.getAverage()+"</li>";
        result+="<li><strong> Max of Transactions</strong>: "+summaryStatistics.getMax()+"</li>";
        result+="<li><strong> Min of Transactions</strong>: "+summaryStatistics.getMin()+"</li>";
        result+="</ul>";
        result+="</body>";
        result+="</html>";
        return result;
    }
    
}
