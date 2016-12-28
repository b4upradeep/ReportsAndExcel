package app;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {
	
	
	public static boolean isCoreSubject(String subject){
		try {
		int number = Integer.parseInt(subject.substring(subject.length() - 3));
		}catch(Exception e){
			return false;
		}
		return true;
		
	}

	public static List<Integer> findPerformanceVariation(Course courseDetails) {
		List<Integer> externalMarks = new ArrayList<Integer>();
		List<Integer> internalMarks = new ArrayList<Integer>();
		List<Integer> totalMarks = new ArrayList<Integer>();
		
		int positiveVariationCount = 0;
		int percent10VariationCount = 0;
		int percent14VariationCount = 0;
		int percent20VariationCount = 0;
		for(int i=0;i<courseDetails.getStudents().size();i++){
			int externalTotal = 0;
			int internalTotal = 0;
			
			int subjectCount = courseDetails.getStudents().get(i).getSubjects().size();
			for(int j=0;j<subjectCount;j++){
					externalTotal += courseDetails.getStudents().get(i).getSubjects().get(j).getMarks().getExternalMarks();
					
					internalTotal += courseDetails.getStudents().get(i).getSubjects().get(j).getMarks().getInternalMarks();
			}
			
			externalMarks.add((externalTotal/subjectCount));
			internalMarks.add((internalTotal/subjectCount));
			int variation = externalMarks.get(i) - internalMarks.get(i);
			if(variation>0){
				++positiveVariationCount;
					
			}
			
			if(Math.abs(variation)>=5){
				
				++percent10VariationCount;
			}
			
			if(Math.abs(variation)>=7){
				++percent14VariationCount;
			}
			
			if(Math.abs(variation)>=10){
				++percent10VariationCount;
			}
			
			
		
		}
		
		List<Integer> countParameters = new ArrayList<Integer>();
		countParameters.add(positiveVariationCount);
		countParameters.add(percent10VariationCount);

		countParameters.add(percent14VariationCount);
		countParameters.add(percent20VariationCount);
		
		return countParameters;
		
	}
	
	
}
