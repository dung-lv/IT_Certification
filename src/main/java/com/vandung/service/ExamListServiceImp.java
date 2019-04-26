package com.vandung.service;

import java.io.File;
import java.io.FileOutputStream;
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
import com.vandung.repository.ExamListRepository;

@Service
public class ExamListServiceImp implements ExamListService {

	@Autowired
	private ExamListRepository examlistRepository;

	@Override
	public List<ExamList> getListByConfirm() {
		return examlistRepository.getListByConfirm();
	}
	
	@Override
	public void add(ExamList db) {
		examlistRepository.add(db);
	}

	@Override
	public List<ExamList> getListByNotConfirm() {
		return examlistRepository.getListByNotConfirm();
	}

	@Override
	public void updateCandidateConfirm(Integer id) {
		examlistRepository.updateCandidateConfirm(id);
	}

	@Override
	public Boolean createExcel(List<ExamList> entity, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if(!exists) {
			new File(filePath).mkdirs();
		}
		try {
			FileOutputStream fos = null;
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = null;
			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFRow headerRow = null;
			
			if(entity.get(0).getConfirm()) {
				fos = new FileOutputStream(file + "/" + "danhsachthi" + ".xls");
				worksheet = workbook.createSheet("danhsachthi");
				worksheet.setDefaultColumnWidth(30);
				headerRow = worksheet.createRow(0);
				
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
				
				HSSFCell note = headerRow.createCell(4);
				note.setCellValue("Ghi chu");
				note.setCellStyle(headerCellStyle);
				
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
					
					HSSFCell noteValue = bodyRow.createCell(4);
					noteValue.setCellValue(el.getCandidate().getNote());
					noteValue.setCellStyle(bodyCellStyle);
					
					i++;
				}
			}else {
				fos = new FileOutputStream(file + "/" + "danhsachthutien" + ".xls");
				worksheet = workbook.createSheet("danhsachthutien");
				worksheet.setDefaultColumnWidth(30);
				headerRow = worksheet.createRow(0);
				
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
				
				HSSFCell reviewCost = headerRow.createCell(4);
				reviewCost.setCellValue("On thi");
				reviewCost.setCellStyle(headerCellStyle);
				
				HSSFCell examCost = headerRow.createCell(5);
				examCost.setCellValue("Thi");
				examCost.setCellStyle(headerCellStyle);
				
				HSSFCell total = headerRow.createCell(6);
				total.setCellValue("Tong");
				total.setCellStyle(headerCellStyle);
				
				HSSFCell confirm = headerRow.createCell(7);
				confirm.setCellValue("Ky nhan");
				confirm.setCellStyle(headerCellStyle);
				
				int i = 1;
				for(ExamList el : entity) {
					HSSFRow bodyRow = worksheet.createRow(i);

					HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
					bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
					
					HSSFCell nameValue = bodyRow.createCell(0);
					nameValue.setCellValue(el.getCandidate().getNameCandidate());
					nameValue.setCellStyle(bodyCellStyle);
					
					HSSFCell dobValue = bodyRow.createCell(1);
					dobValue.setCellValue(el.getCandidate().getDateOfBirth());
					dobValue.setCellStyle(bodyCellStyle);
					
					HSSFCell codeValue = bodyRow.createCell(2);
					codeValue.setCellValue(el.getCandidate().getCode());
					codeValue.setCellStyle(bodyCellStyle);
					
					HSSFCell objectValue = bodyRow.createCell(3);
					objectValue.setCellValue(el.getCandidate().getDemand().getObject());
					objectValue.setCellStyle(bodyCellStyle);
					
					HSSFCell reviewCostValue = bodyRow.createCell(4);
					reviewCostValue.setCellValue(el.getCandidate().getDemand().getReviewCost());
					reviewCostValue.setCellStyle(bodyCellStyle);
					
					HSSFCell examCostValue = bodyRow.createCell(5);
					examCostValue.setCellValue(el.getCandidate().getDemand().getExamCost());
					examCostValue.setCellStyle(bodyCellStyle);
					
					HSSFCell totalValue = bodyRow.createCell(4);
					totalValue.setCellValue(el.getTotalCost());
					totalValue.setCellStyle(bodyCellStyle);
					
					HSSFCell confirmValue = bodyRow.createCell(4);
					confirmValue.setCellValue("");
					confirmValue.setCellStyle(bodyCellStyle);
					
					i++;
				}
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
