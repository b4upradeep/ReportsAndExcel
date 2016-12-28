package app;

import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

public class ReportGenerator extends ApplicationFrame {
	 public ReportGenerator( String title,List<String> stringSet,List<Integer> integerSet ) 
	   {
	      super( title ); 
	      setContentPane(createDemoPanel(stringSet,integerSet));
	   }
	   private static PieDataset createDataset(List<String> stringSet,List<Integer>  integerSet)  
	   {
	      DefaultPieDataset dataset = new DefaultPieDataset( );
	      for(int i=0;i<stringSet.size();i++){
	    	  dataset.setValue( stringSet.get(i) , new Double(integerSet.get(i)) );  
	      }
	      return dataset;         
	   }
	   private static JFreeChart createChart( PieDataset dataset )
	   {
	      JFreeChart chart = ChartFactory.createPieChart(      
	         "",  // chart title 
	         dataset,        // data    
	         true,           // include legend   
	         true, 
	         false);

	      return chart;
	   }
	   public static JPanel createDemoPanel(List<String> stringSet,List<Integer> integerSet)
	   {
	      JFreeChart chart = createChart(createDataset( stringSet,integerSet) );  
	      return new ChartPanel( chart ); 
	   }
	   
	   
}
