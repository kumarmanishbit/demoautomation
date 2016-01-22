package com.castlight.demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.castlight.beans.Providers;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderDao;

public class ProcessExcel {

	private static List<String> excelRow = null;

	public List<String> getExcelRow() {
		return excelRow;
	}

	public void setExcelRow(List<String> excelRow) {
		this.excelRow = excelRow;
	}

	public List<SourceExcel> getSourceExcelRow() {
		return sourceExcelRow;
	}

	public void setSourceExcelRow(List<SourceExcel> sourceExcelRow) {
		this.sourceExcelRow = sourceExcelRow;
	}

	private static List<SourceExcel> sourceExcelRow = new ArrayList<>();

	public ProcessExcel() {
		List<Providers> listOfProviders = new ArrayList<>();
		SourceExcel sourceExcel = null;
		try {
			FileInputStream file = new FileInputStream(new File("demo_automation.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = rowIterator.next();
			ProviderDao providerDao = new ProviderDao();

			long id = providerDao.getMaxId();

			while (rowIterator.hasNext()) {
				id++;
				row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				excelRow = new ArrayList<>();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						excelRow.add(cell.getNumericCellValue() + "");
						break;
					case Cell.CELL_TYPE_STRING:
						excelRow.add(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BLANK:
						excelRow.add("");
						break;
					}
				}

				sourceExcel = new SourceExcel();

				sourceExcel.setProvider_Name(excelRow.get(0));
				sourceExcel.setFirst_Name("NA".equals(excelRow.get(1)) ? "NULL" : excelRow.get(1));
				sourceExcel.setLast_Name("NA".equals(excelRow.get(2)) ? "NULL" : excelRow.get(2));
				sourceExcel.setInitials("NA".equals(excelRow.get(3)) ? "NULL" : excelRow.get(3));

				sourceExcel.setDoctor_Hospital(excelRow.get(4));

				if ("D".equalsIgnoreCase(sourceExcel.getDoctor_Hospital().trim())) {
					Integer providerId = providerDao.getProviderId(sourceExcel.getFirst_Name().trim(),
							sourceExcel.getLast_Name().trim(), sourceExcel.getInitials().trim());
				//	System.out.println(providerId);

					if (providerId != null) {
						sourceExcel.setId(providerId);
					}else{
					sourceExcel.setId(id);}
				} else {
					Integer providerId = providerDao.getProviderId(sourceExcel.getProvider_Name());
					if (providerId != null) {
						sourceExcel.setId(providerId);
					}else {
					sourceExcel.setId(id);
					}
				}

				sourceExcel.setProvider_Type(excelRow.get(5));
				sourceExcel.setReview_Provider_Type(excelRow.get(6));
				sourceExcel.setSuffix(excelRow.get(7));
				sourceExcel.setYears_Of_Experience(excelRow.get(8));
				sourceExcel.setHospital_Name(excelRow.get(9));
				sourceExcel.setGender(excelRow.get(10));

				sourceExcel.setBoard_Certification(excelRow.get(11));

				sourceExcel.setEducation_Associations_Publications(excelRow.get(12));

				sourceExcel.setMedical_School_Graduation_Year(excelRow.get(13));

				sourceExcel.setAddress(excelRow.get(14));

				sourceExcel.setState(excelRow.get(15));

				sourceExcel.setCity(excelRow.get(16));

				sourceExcel.setZip(excelRow.get(17));

				sourceExcel.setDistance(excelRow.get(18));

				sourceExcel.setPhoneNumber("NA".equals(excelRow.get(19)) ? "NULL" : excelRow.get(19));

				sourceExcel.setSpecialities(excelRow.get(20));

				sourceExcel.setParent_Provider_Name(excelRow.get(21));

				sourceExcel.setAffilation_Type(excelRow.get(22));

				sourceExcel.setPatient_Rating(excelRow.get(23));

				sourceExcel.setNumberofRatings(excelRow.get(24));

				sourceExcel.setEstimated_Price(excelRow.get(25));

				sourceExcel.setYou_Pay(excelRow.get(26));

				sourceExcel.setOverall_Rating(excelRow.get(27));

				sourceExcel.setPost_discharge_transmissionOfCarePlanToNextLevelOfCareProvider(excelRow.get(28));

				sourceExcel.setHoursofSeclusion(excelRow.get(29));

				sourceExcel.setHoursOfPhysicalRestraint(excelRow.get(30));

				sourceExcel.setPostDischargeContinuingCarePlanCreated(excelRow.get(31));

				sourceExcel
						.setOverallIWouldRecommendThisProvider("NA".equals(excelRow.get(32)) ? "0" : excelRow.get(32));

				sourceExcel.setDoctorsCommunicateWell("NA".equals(excelRow.get(33)) ? "0" : excelRow.get(33));

				sourceExcel.setNursesCommunicateWell("NA".equals(excelRow.get(34)) ? "0" : excelRow.get(34));

				sourceExcel.setClearDischargeInstructions("NA".equals(excelRow.get(35)) ? "0" : excelRow.get(35));
				// System.out.println("NA".equals(excelRow.get(34)));
				// System.out.println(excelRow.get(34));

				sourceExcel.setPainWellControlled("NA".equals(excelRow.get(36)) ? "0" : excelRow.get(36));

				sourceExcel.setMedicationsExplainedBeforeGiving("NA".equals(excelRow.get(37)) ? "0" : excelRow.get(37));

				sourceExcel.setRoomBathroomKeptClean("NA".equals(excelRow.get(38)) ? "0" : excelRow.get(38));

				sourceExcel.setRoomKeptQuietAtNight("NA".equals(excelRow.get(39)) ? "0" : excelRow.get(39));

				sourceExcel.setReceiveHelpQuickly("NA".equals(excelRow.get(40)) ? "0" : excelRow.get(40));

				sourceExcel.setService(excelRow.get(41));

				sourceExcel.setIncluded(excelRow.get(42));

				sourceExcel.setNotIncluded(excelRow.get(43));

				sourceExcel.setEstimated_Price(excelRow.get(44));

				sourceExcel.setCompanyPays(excelRow.get(45));

				sourceExcel.setDesignation(excelRow.get(46));
				sourceExcelRow.add(sourceExcel);
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//System.out.println(sourceExcelRow.size());
	}
}
