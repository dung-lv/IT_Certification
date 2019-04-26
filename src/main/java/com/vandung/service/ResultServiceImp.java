package com.vandung.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandung.entity.ExamList;
import com.vandung.entity.Examination;
import com.vandung.entity.Result;
import com.vandung.repository.ExaminationRepository;
import com.vandung.repository.ResultRepository;

@Service
public class ResultServiceImp implements ResultService{

	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private ExaminationRepository examinationRepository;

	@Override
	public List<Result> getAllByExamination() {
		Examination exam = examinationRepository.getExaminationNow();
		if(exam == null) {
			return new ArrayList<Result>();
		}else {			
			return resultRepository.getAllByExamination(exam.getIdExamination());
		}
	}

	@Override
	public Boolean createExcelResultList(List<Result> entity, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if(!exists) {
			new File(filePath).mkdirs();
		}
		try {

			FileOutputStream fos = new FileOutputStream(file + "/" + "ketquathi" + ".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("ketquathi");
			worksheet.setDefaultColumnWidth(30);
			
			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFRow headerRow = worksheet.createRow(0);
			
			HSSFCell name = headerRow.createCell(0);
			name.setCellValue("Ho ten");
			name.setCellStyle(headerCellStyle);
			
			HSSFCell dob = headerRow.createCell(1);
			dob.setCellValue("Ngay sinh");
			dob.setCellStyle(headerCellStyle);
			
			HSSFCell code = headerRow.createCell(2);
			code.setCellValue("CMT");
			code.setCellStyle(headerCellStyle);
			
			HSSFCell sightScores = headerRow.createCell(3);
			sightScores.setCellValue("Trac nghiem");
			sightScores.setCellStyle(headerCellStyle);
			
			HSSFCell drillScores = headerRow.createCell(4);
			drillScores.setCellValue("Thuc hanh");
			drillScores.setCellStyle(headerCellStyle);
			
			int i = 1;
			for(Result res : entity) {
				HSSFRow bodyRow = worksheet.createRow(i);

				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

				HSSFCell nameValue = bodyRow.createCell(0);
				nameValue.setCellValue(res.getCandidate().getNameCandidate());
				nameValue.setCellStyle(bodyCellStyle);
				
				HSSFCell dobValue = bodyRow.createCell(1);
				dobValue.setCellValue(res.getCandidate().getDateOfBirth());
				dobValue.setCellStyle(bodyCellStyle);
				
				HSSFCell codeValue = bodyRow.createCell(2);
				codeValue.setCellValue(res.getCandidate().getCode());
				codeValue.setCellStyle(bodyCellStyle);
				
				HSSFCell sightScoresValue = bodyRow.createCell(3);
				sightScoresValue.setCellValue(res.getSightScores());
				sightScoresValue.setCellStyle(bodyCellStyle);
				
				HSSFCell drillScoresValue = bodyRow.createCell(4);
				drillScoresValue.setCellValue(res.getDrillScores());
				drillScoresValue.setCellStyle(bodyCellStyle);
				
				i++;
			}
			workbook.write(fos);
			fos.flush();
			fos.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean createExcelCollectTestList(List<ExamList> entity, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if(!exists) {
			new File(filePath).mkdirs();
		}
		try {
			FileOutputStream fos = new FileOutputStream(file + "/" + "danhsachthubai" + ".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("danhsachthubai");
			worksheet.setDefaultColumnWidth(30);
			
			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFRow headerRow = worksheet.createRow(0);
			
			HSSFCell registrationNumber = headerRow.createCell(0);
			registrationNumber.setCellValue("So bao danh");
			registrationNumber.setCellStyle(headerCellStyle);
			
			HSSFCell name = headerRow.createCell(1);
			name.setCellValue("Ho ten");
			name.setCellStyle(headerCellStyle);
			
			HSSFCell dob = headerRow.createCell(2);
			dob.setCellValue("Ngay sinh");
			dob.setCellStyle(headerCellStyle);
			
			HSSFCell code = headerRow.createCell(3);
			code.setCellValue("CMT");
			code.setCellStyle(headerCellStyle);
			
			HSSFCell sightScores = headerRow.createCell(4);
			sightScores.setCellValue("Trac nghiem");
			sightScores.setCellStyle(headerCellStyle);
			
			HSSFCell confirmSightScores = headerRow.createCell(5);
			confirmSightScores.setCellValue("Ky");
			confirmSightScores.setCellStyle(headerCellStyle);
			
			HSSFCell wordScores = headerRow.createCell(6);
			wordScores.setCellValue("Word");
			wordScores.setCellStyle(headerCellStyle);
			
			HSSFCell excelScores = headerRow.createCell(7);
			excelScores.setCellValue("Excel");
			excelScores.setCellStyle(headerCellStyle);
			
			HSSFCell pptScores = headerRow.createCell(8);
			pptScores.setCellValue("PPT");
			pptScores.setCellStyle(headerCellStyle);
			
			HSSFCell confirmDrillScores = headerRow.createCell(9);
			confirmDrillScores.setCellValue("Ky");
			confirmDrillScores.setCellStyle(headerCellStyle);
			
			int i = 1;
			for(ExamList el : entity) {
				HSSFRow bodyRow = worksheet.createRow(i);

				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

				HSSFCell rnValue = bodyRow.createCell(0);
				rnValue.setCellValue(el.getCandidate().getRegistrationNumber());
				rnValue.setCellStyle(bodyCellStyle);
				
				HSSFCell nameValue = bodyRow.createCell(1);
				nameValue.setCellValue(el.getCandidate().getNameCandidate());
				nameValue.setCellStyle(bodyCellStyle);
				
				HSSFCell dobValue = bodyRow.createCell(2);
				dobValue.setCellValue(el.getCandidate().getDateOfBirth());
				dobValue.setCellStyle(bodyCellStyle);
				
				HSSFCell codeValue = bodyRow.createCell(3);
				codeValue.setCellValue(el.getCandidate().getCode());
				codeValue.setCellStyle(bodyCellStyle);
				
				HSSFCell sightScoresValue = bodyRow.createCell(4);
				sightScoresValue.setCellValue("");
				sightScoresValue.setCellStyle(bodyCellStyle);
				
				HSSFCell confirmSightScoresValue = bodyRow.createCell(5);
				confirmSightScoresValue.setCellValue("");
				confirmSightScoresValue.setCellStyle(bodyCellStyle);
				
				HSSFCell wordScoresValue = bodyRow.createCell(6);
				wordScoresValue.setCellValue("");
				wordScoresValue.setCellStyle(bodyCellStyle);
				
				HSSFCell excelScoresValue = bodyRow.createCell(7);
				excelScoresValue.setCellValue("");
				excelScoresValue.setCellStyle(bodyCellStyle);
								
				HSSFCell pptScoresValue = bodyRow.createCell(8);
				pptScoresValue.setCellValue("");
				pptScoresValue.setCellStyle(bodyCellStyle);
				
				HSSFCell confirmDrillScoresValue = bodyRow.createCell(9);
				confirmDrillScoresValue.setCellValue("");
				confirmDrillScoresValue.setCellStyle(bodyCellStyle);
				
				i++;
			}
			workbook.write(fos);
			fos.flush();
			fos.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Result db) {
		return resultRepository.update(db);
	}	

}
