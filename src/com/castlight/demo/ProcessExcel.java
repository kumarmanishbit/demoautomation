package com.castlight.demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.castlight.beans.Providers;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderDao;
import com.castlight.dao.ProviderLocationDao;
import com.castlight.dao.ProviderParticipationDao;

public class ProcessExcel {

	private  List<String> excelRow = null;

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
		ProcessExcel.sourceExcelRow = sourceExcelRow;
	}

	private static List<SourceExcel> sourceExcelRow = new ArrayList<>();

	public ProcessExcel() {
		SourceExcel sourceExcel = null;
		try {
			FileInputStream file = new FileInputStream(new File("/Users/manishk/Downloads/demo_automation.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			DataFormatter fmt = new DataFormatter();

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = rowIterator.next();
			ProviderDao providerDao = new ProviderDao();

			long id = providerDao.getMaxId();

			while (rowIterator.hasNext()) {
				
				row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				excelRow = new ArrayList<>();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {
					default:
						excelRow.add(fmt.formatCellValue(cell));

					}
				}

				sourceExcel = new SourceExcel();

				sourceExcel.setProvider_Name(excelRow.get(0));
				sourceExcel.setFirst_Name("NA".equalsIgnoreCase(excelRow.get(1)) ? "NULL" : excelRow.get(1));
				sourceExcel.setLast_Name("NA".equalsIgnoreCase(excelRow.get(2)) ? "NULL" : excelRow.get(2));
				sourceExcel.setInitials("NA".equalsIgnoreCase(excelRow.get(3)) ? "NULL" : excelRow.get(3));

				sourceExcel.setDoctor_Hospital("NA".equalsIgnoreCase(excelRow.get(4)) ? "NULL" : excelRow.get(4));

				Integer providerId = 0;
				if ("D".equalsIgnoreCase(sourceExcel.getDoctor_Hospital().trim())) {
					providerId = providerDao.getProviderId(sourceExcel.getFirst_Name().trim(),
							sourceExcel.getLast_Name().trim(), sourceExcel.getInitials().trim());

					if (providerId != 0) {
						sourceExcel.setId(providerId);
					} else {
						id++;
						sourceExcel.setId(id);
					}
				} else {
					providerId = providerDao.getProviderId(sourceExcel.getProvider_Name());
					if (providerId != 0) {
						sourceExcel.setId(providerId);
					} else {
						id++;
						sourceExcel.setId(id);
					}
				}

				sourceExcel.setProvider_Type("NA".equalsIgnoreCase(excelRow.get(5)) ? "NULL" : excelRow.get(5));
				sourceExcel.setReview_Provider_Type("NA".equalsIgnoreCase(excelRow.get(6)) ? "NULL" : excelRow.get(6));
				sourceExcel.setSuffix("NA".equalsIgnoreCase(excelRow.get(7)) ? "NULL" : excelRow.get(7));
				sourceExcel.setYears_Of_Experience("NA".equalsIgnoreCase(excelRow.get(8)) ? "NULL" : excelRow.get(8));
				sourceExcel.setHospital_Name("NA".equalsIgnoreCase(excelRow.get(9)) ? "NULL" : excelRow.get(9));
				sourceExcel.setGender("NA".equalsIgnoreCase(excelRow.get(10)) ? "NULL" : excelRow.get(10));

				sourceExcel.setBoard_Certification("NA".equalsIgnoreCase(excelRow.get(11)) ? "NULL" : excelRow.get(11));

				sourceExcel.setEducation_Associations_Publications("NA".equalsIgnoreCase(excelRow.get(12)) ? "NULL" : excelRow.get(12));

				sourceExcel.setMedical_School_Graduation_Year("NA".equalsIgnoreCase(excelRow.get(13)) ? "NULL" : excelRow.get(13));

				sourceExcel.setAddress("NA".equalsIgnoreCase(excelRow.get(14)) ? "NULL" : excelRow.get(14));

				sourceExcel.setState("NA".equalsIgnoreCase(excelRow.get(15)) ? "NULL" : excelRow.get(15));

				sourceExcel.setCity("NA".equalsIgnoreCase(excelRow.get(16)) ? "NULL" : excelRow.get(16));

				sourceExcel.setZip((int) Float.valueOf(excelRow.get(17).trim()).floatValue());

				sourceExcel.setDistance("NA".equalsIgnoreCase(excelRow.get(18)) ? "NULL" : excelRow.get(18));

				ProviderLocationDao providerLocationDao = new ProviderLocationDao();

				int providerLocationID = providerLocationDao.findProviderLocation(excelRow.get(14), excelRow.get(16),
						(int) Float.valueOf(excelRow.get(17).trim()).floatValue());

				sourceExcel.setProviderLocationId(providerLocationID);

				ProviderParticipationDao providerParticipationDao = new ProviderParticipationDao();

				sourceExcel.setPhoneNumber("NA".equalsIgnoreCase(excelRow.get(19)) ? "NULL" : excelRow.get(19));

				sourceExcel.setSpecialities(excelRow.get(20));

				sourceExcel.setParent_Provider_Name(excelRow.get(21));

				sourceExcel.setAffilation_Type(excelRow.get(22));

				sourceExcel.setPatient_Rating("NA".equalsIgnoreCase(excelRow.get(23)) ? "0.0" : excelRow.get(23));

				sourceExcel.setNumberofRatings("NA".equalsIgnoreCase(excelRow.get(24)) ? "0" : excelRow.get(24));

				sourceExcel.setEstimated_Price("NA".equalsIgnoreCase(excelRow.get(25)) ? "$0-$0" : excelRow.get(25));

				sourceExcel.setYou_Pay("NA".equalsIgnoreCase(excelRow.get(26)) ? "$0-$0" : excelRow.get(25));

				sourceExcel.setOverall_Rating("NA".equalsIgnoreCase(excelRow.get(27)) ? "0.0" : excelRow.get(27));

				sourceExcel.setPost_discharge_transmissionOfCarePlanToNextLevelOfCareProvider(excelRow.get(28));

				sourceExcel.setHoursofSeclusion(excelRow.get(29));

				sourceExcel.setHoursOfPhysicalRestraint(excelRow.get(30));

				sourceExcel.setPostDischargeContinuingCarePlanCreated(excelRow.get(31));

				sourceExcel
						.setOverallIWouldRecommendThisProvider("NA".equalsIgnoreCase(excelRow.get(32)) ? "0" : excelRow.get(32));

				sourceExcel.setDoctorsCommunicateWell("NA".equalsIgnoreCase(excelRow.get(33)) ? "0" : excelRow.get(33));

				sourceExcel.setNursesCommunicateWell("NA".equalsIgnoreCase(excelRow.get(34)) ? "0" : excelRow.get(34));

				sourceExcel.setClearDischargeInstructions("NA".equalsIgnoreCase(excelRow.get(35)) ? "0" : excelRow.get(35));

				sourceExcel.setPainWellControlled("NA".equalsIgnoreCase(excelRow.get(36)) ? "0" : excelRow.get(36));

				sourceExcel.setMedicationsExplainedBeforeGiving("NA".equalsIgnoreCase(excelRow.get(37)) ? "0" : excelRow.get(37));

				sourceExcel.setRoomBathroomKeptClean("NA".equalsIgnoreCase(excelRow.get(38)) ? "0" : excelRow.get(38));

				sourceExcel.setRoomKeptQuietAtNight("NA".equalsIgnoreCase(excelRow.get(39)) ? "0" : excelRow.get(39));

				sourceExcel.setReceiveHelpQuickly("NA".equalsIgnoreCase(excelRow.get(40)) ? "0" : excelRow.get(40));

				sourceExcel.setService(excelRow.get(41));

				sourceExcel.setIncluded(excelRow.get(42));

				sourceExcel.setNotIncluded(excelRow.get(43));

				sourceExcel.setCompanyPays(excelRow.get(45));

			//	System.out.println(excelRow.get(1));
				sourceExcel.setDesignation(excelRow.get(46));
				sourceExcelRow.add(sourceExcel);
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
