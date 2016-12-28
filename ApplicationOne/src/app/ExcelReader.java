package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}

		return null;
	}

	public Course readDataFromExcelFile(String excelFilePath) {
		Course courseObject = new Course();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));

			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(1);
			courseObject.setCourseName(firstSheet.getSheetName());
			Iterator<Row> iterator = firstSheet.iterator();
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			List<UniversityStudent> studentList = new ArrayList<UniversityStudent>();
			List<String> courseSubjects = new ArrayList<String>();
			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();

				if (getCellValue(nextCell) != null && nextCell.getColumnIndex() > 1) {
					courseSubjects.add((String) getCellValue(nextCell));
				}
			}
			nextRow = iterator.next();
			while (iterator.hasNext()) {
				nextRow = iterator.next();

				UniversityStudent studentRecord = new UniversityStudent();
				cellIterator = nextRow.cellIterator();
				Cell cellPointer = cellIterator.next();
				cellPointer = cellIterator.next();
				studentRecord.setRegisterNumber(String.valueOf(getCellValue(cellPointer)));
				List<Subject> subjects = new ArrayList<Subject>();
				for (int i = 0; i < courseSubjects.size(); i++) {
					Subject subject = new Subject();
					Marks mark = new Marks();
					cellPointer = cellIterator.next();
					if (!"N".equals(String.valueOf(getCellValue(cellPointer)))) {
						subject.setSubjectId(courseSubjects.get(i));
						mark.setExternalMarks((int) Double.parseDouble(String.valueOf(getCellValue(cellPointer))));
					}
					cellPointer = cellIterator.next();
					if (!"N".equals(String.valueOf(getCellValue(cellPointer)))) {

						mark.setInternalMarks((int) Double.parseDouble(String.valueOf(getCellValue(cellPointer))));
					}

					cellPointer = cellIterator.next();
					if (!"N".equals(String.valueOf(getCellValue(cellPointer)))) {
						mark.setTotalMarks((int) Double.parseDouble(String.valueOf(getCellValue(cellPointer))));
					}
					cellPointer = cellIterator.next();
					if (!"N".equals(String.valueOf(getCellValue(cellPointer)))) {
						mark.setGrade(String.valueOf(getCellValue(cellPointer)).charAt(0));
						subject.setMarks(mark);
						subjects.add(subject);
					}
				}
				cellPointer = cellIterator.next();
				cellPointer = cellIterator.next();
				cellPointer = cellIterator.next();
				studentRecord.setGpa(Double.parseDouble(String.valueOf(getCellValue(cellPointer))));
				studentRecord.setSubjects(subjects);
				studentList.add(studentRecord);
			}
			courseObject.setStudents(studentList);
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			String exceptionReason = "Invalid Marksheet Format";
			JOptionPane.showMessageDialog(null, exceptionReason + "\n" + e);
			return null;
		}
		
		DataBean bean = new DataBean();
		bean.setCourse(courseObject);
		return courseObject;
	}
}
