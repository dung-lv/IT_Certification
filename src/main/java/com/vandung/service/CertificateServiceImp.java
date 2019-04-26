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

import com.vandung.entity.Certificate;
import com.vandung.entity.Examination;
import com.vandung.repository.CertificateRepository;
import com.vandung.repository.ExaminationRepository;

@Service
public class CertificateServiceImp implements CertificateService{

	@Autowired
	private CertificateRepository certificateRepository;
	
	@Autowired
	private ExaminationRepository examinationRepository;

	@Override
	public List<Certificate> getAllByExamination() {
		Examination exam = examinationRepository.getExaminationNow();
		if(exam == null) {
			return new ArrayList<Certificate>();
		}else {			
			return certificateRepository.getAllByExamination(exam.getIdExamination());
		}
	}

	@Override
	public Boolean update(Certificate entity) {
		return certificateRepository.update(entity);
	}
	
	@Override
	public Boolean createExcel(List<Certificate> entity, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if(!exists) {
			new File(filePath).mkdirs();
		}
		try {
			FileOutputStream fos = new FileOutputStream(file + "/" + "danhsachcapchungchi" + ".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("danhsachcapchungchi");
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
			
			HSSFCell object = headerRow.createCell(3);
			object.setCellValue("Doi tuong");
			object.setCellStyle(headerCellStyle);
			
			HSSFCell diplomaNumber = headerRow.createCell(4);
			diplomaNumber.setCellValue("So van bang");
			diplomaNumber.setCellStyle(headerCellStyle);
			
			HSSFCell notebookNumber = headerRow.createCell(5);
			notebookNumber.setCellValue("So vao so");
			notebookNumber.setCellStyle(headerCellStyle);
			
			HSSFCell confirm = headerRow.createCell(6);
			confirm.setCellValue("Ky nhan");
			confirm.setCellStyle(headerCellStyle);
			
			HSSFCell note = headerRow.createCell(7);
			note.setCellValue("Ghi chu");
			note.setCellStyle(headerCellStyle);
			
			int i = 1;
			for(Certificate cer : entity) {
				HSSFRow bodyRow = worksheet.createRow(i);

				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

				HSSFCell nameValue = bodyRow.createCell(0);
				nameValue.setCellValue(cer.getCandidate().getNameCandidate());
				nameValue.setCellStyle(bodyCellStyle);
				
				HSSFCell dobValue = bodyRow.createCell(1);
				dobValue.setCellValue(cer.getCandidate().getDateOfBirth());
				dobValue.setCellStyle(bodyCellStyle);
				
				HSSFCell codeValue = bodyRow.createCell(2);
				codeValue.setCellValue(cer.getCandidate().getCode());
				codeValue.setCellStyle(bodyCellStyle);
				
				HSSFCell objectValue = bodyRow.createCell(3);
				objectValue.setCellValue(cer.getCandidate().getDemand().getObject());
				objectValue.setCellStyle(bodyCellStyle);
				
				HSSFCell diploNumberValue = bodyRow.createCell(4);
				diploNumberValue.setCellValue(cer.getDiplomaNumber());
				diploNumberValue.setCellStyle(bodyCellStyle);
				
				HSSFCell noteNumberValue = bodyRow.createCell(5);
				noteNumberValue.setCellValue(cer.getNotebookNumber());
				noteNumberValue.setCellStyle(bodyCellStyle);
				
				HSSFCell confirmValue = bodyRow.createCell(6);
				confirmValue.setCellValue("");
				confirmValue.setCellStyle(bodyCellStyle);
				
				HSSFCell noteValue = bodyRow.createCell(7);
				noteValue.setCellValue("");
				noteValue.setCellStyle(bodyCellStyle);
				
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

}
