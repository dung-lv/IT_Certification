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

/*import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;*/
import com.vandung.entity.Examination;
import com.vandung.entity.Member;
import com.vandung.repository.ExaminationRepository;

@Service
public class ExaminationServiceImp implements ExaminationService{

	@Autowired
	private ExaminationRepository examinationRepository;
	
	@Override
	public List<Examination> getAll() {
		return examinationRepository.getAll();
	}

	@Override
	public void add(Examination model) {
		examinationRepository.add(model);
	}

	@Override
	public Examination getExaminationNow() {
		return examinationRepository.getExaminationNow();
	}

//	@Override
//	public Boolean createPdf(Examination model, ServletContext context, HttpServletRequest request,
//			HttpServletResponse response) {
//		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
//		try {
//			String filePath = context.getRealPath("/resources/reports");
//			File file = new File(filePath);
//			boolean exists = new File(filePath).exists();
//			if(!exists) {
//				new File(filePath).mkdirs();
//			}
//			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "hoidongthi" + ".pdf"));
//			document.open();
//			Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
//			Paragraph paragraph = new Paragraph("HOI DONG THI", mainFont);
//			paragraph.setAlignment(Element.ALIGN_CENTER);
//			paragraph.setIndentationLeft(50);
//			paragraph.setIndentationRight(50);
//			paragraph.setSpacingAfter(10);
//			document.add(paragraph);
//			PdfPTable table = new PdfPTable(5);
//			table.setWidthPercentage(100);
//			table.setSpacingBefore(10f);
//			table.setSpacingAfter(10);
//			
//			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
//			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
//			
//			float[] columnWidths = {2f, 2f, 2f, 2f, 2f};
//			table.setWidths(columnWidths);
//			
//			PdfPCell name = new PdfPCell(new Paragraph("Ho ten", tableHeader));
//			name.setBorderColor(BaseColor.BLACK);
//			name.setPaddingLeft(10);
//			name.setHorizontalAlignment(Element.ALIGN_CENTER);
//			name.setVerticalAlignment(Element.ALIGN_CENTER);
//			name.setBackgroundColor(BaseColor.GRAY);
//			name.setExtraParagraphSpace(5);
//			table.addCell(name);
//			
//			PdfPCell workunit = new PdfPCell(new Paragraph("Don vi", tableHeader));
//			workunit.setBorderColor(BaseColor.BLACK);
//			workunit.setPaddingLeft(10);
//			workunit.setHorizontalAlignment(Element.ALIGN_CENTER);
//			workunit.setVerticalAlignment(Element.ALIGN_CENTER);
//			workunit.setBackgroundColor(BaseColor.GRAY);
//			workunit.setExtraParagraphSpace(5);
//			table.addCell(workunit);
//			
//			PdfPCell rank = new PdfPCell(new Paragraph("Cap bac", tableHeader));
//			rank.setBorderColor(BaseColor.BLACK);
//			rank.setPaddingLeft(10);
//			rank.setHorizontalAlignment(Element.ALIGN_CENTER);
//			rank.setVerticalAlignment(Element.ALIGN_CENTER);
//			rank.setBackgroundColor(BaseColor.GRAY);
//			rank.setExtraParagraphSpace(5);
//			table.addCell(rank);
//			
//			PdfPCell note = new PdfPCell(new Paragraph("Ghi chu", tableHeader));
//			note.setBorderColor(BaseColor.BLACK);
//			note.setPaddingLeft(10);
//			note.setHorizontalAlignment(Element.ALIGN_CENTER);
//			note.setVerticalAlignment(Element.ALIGN_CENTER);
//			note.setBackgroundColor(BaseColor.GRAY);
//			note.setExtraParagraphSpace(5);
//			table.addCell(note);
//			
//			PdfPCell mission = new PdfPCell(new Paragraph("Nhiem vu", tableHeader));
//			mission.setBorderColor(BaseColor.BLACK);
//			mission.setPaddingLeft(10);
//			mission.setHorizontalAlignment(Element.ALIGN_CENTER);
//			mission.setVerticalAlignment(Element.ALIGN_CENTER);
//			mission.setBackgroundColor(BaseColor.GRAY);
//			mission.setExtraParagraphSpace(5);
//			table.addCell(mission);
//			
//			for(Member member : model.getMembers()) {
//				PdfPCell nameValue = new PdfPCell(new Paragraph(member.getNameMember(), tableBody));
//				nameValue.setBorderColor(BaseColor.BLACK);
//				nameValue.setPaddingLeft(10);
//				nameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//				nameValue.setVerticalAlignment(Element.ALIGN_CENTER);
//				nameValue.setBackgroundColor(BaseColor.WHITE);
//				nameValue.setExtraParagraphSpace(5f);
//				table.addCell(nameValue);
//				
//				PdfPCell workunitValue = new PdfPCell(new Paragraph(member.getWorkUnit(), tableBody));
//				workunitValue.setBorderColor(BaseColor.BLACK);
//				workunitValue.setPaddingLeft(10);
//				workunitValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//				workunitValue.setVerticalAlignment(Element.ALIGN_CENTER);
//				workunitValue.setBackgroundColor(BaseColor.WHITE);
//				workunitValue.setExtraParagraphSpace(5f);
//				table.addCell(workunitValue);
//				
//				PdfPCell rankValue = new PdfPCell(new Paragraph(member.getRank().getNameRank(), tableBody));
//				rankValue.setBorderColor(BaseColor.BLACK);
//				rankValue.setPaddingLeft(10);
//				rankValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//				rankValue.setVerticalAlignment(Element.ALIGN_CENTER);
//				rankValue.setBackgroundColor(BaseColor.WHITE);
//				rankValue.setExtraParagraphSpace(5f);
//				table.addCell(rankValue);
//				
//				PdfPCell noteValue = new PdfPCell(new Paragraph(member.getNote(), tableBody));
//				noteValue.setBorderColor(BaseColor.BLACK);
//				noteValue.setPaddingLeft(10);
//				noteValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//				noteValue.setVerticalAlignment(Element.ALIGN_CENTER);
//				noteValue.setBackgroundColor(BaseColor.WHITE);
//				noteValue.setExtraParagraphSpace(5f);
//				table.addCell(noteValue);
//				
//				PdfPCell missonValue = new PdfPCell(new Paragraph(member.getRank().getMission().getNameMission(), tableBody));
//				missonValue.setBorderColor(BaseColor.BLACK);
//				missonValue.setPaddingLeft(10);
//				missonValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//				missonValue.setVerticalAlignment(Element.ALIGN_CENTER);
//				missonValue.setBackgroundColor(BaseColor.WHITE);
//				missonValue.setExtraParagraphSpace(5f);
//				table.addCell(missonValue);
//			}
//			document.add(table);
//			document.close();
//			writer.close();
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

	@Override
	public Boolean createExcel(Examination entity, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if(!exists) {
			new File(filePath).mkdirs();
		}
		try {
			FileOutputStream fos = new FileOutputStream(file + "/" + "hoidongthi" + ".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("hoidongthi");
			worksheet.setDefaultColumnWidth(30);
			
			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFRow headerRow = worksheet.createRow(0);
			
			HSSFCell name = headerRow.createCell(0);
			name.setCellValue("Ho ten");
			name.setCellStyle(headerCellStyle);
			
			HSSFCell workunit = headerRow.createCell(1);
			workunit.setCellValue("Don vi");
			workunit.setCellStyle(headerCellStyle);
			
			HSSFCell rank = headerRow.createCell(2);
			rank.setCellValue("Cap bac");
			rank.setCellStyle(headerCellStyle);
			
			HSSFCell note = headerRow.createCell(3);
			note.setCellValue("Ghi chu");
			note.setCellStyle(headerCellStyle);
			
			HSSFCell mission = headerRow.createCell(4);
			mission.setCellValue("Nhiem vu");
			mission.setCellStyle(headerCellStyle);
			
			int i = 1;
			for(Member member : entity.getMembers()) {
				HSSFRow bodyRow = worksheet.createRow(i);

				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

				HSSFCell nameValue = bodyRow.createCell(0);
				nameValue.setCellValue(member.getNameMember());
				nameValue.setCellStyle(bodyCellStyle);
				
				HSSFCell workunitValue = bodyRow.createCell(1);
				workunitValue.setCellValue(member.getWorkUnit());
				workunitValue.setCellStyle(bodyCellStyle);
				
				HSSFCell rankValue = bodyRow.createCell(2);
				rankValue.setCellValue(member.getRank().getNameRank());
				rankValue.setCellStyle(bodyCellStyle);
				
				HSSFCell noteValue = bodyRow.createCell(3);
				noteValue.setCellValue(member.getNote());
				noteValue.setCellStyle(bodyCellStyle);
				
				HSSFCell missonValue = bodyRow.createCell(4);
				missonValue.setCellValue(member.getRank().getMission().getNameMission());
				missonValue.setCellStyle(bodyCellStyle);
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
	public void update(Examination model) {
		examinationRepository.update(model);
	}
}