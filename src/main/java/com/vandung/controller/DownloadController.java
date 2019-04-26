package com.vandung.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vandung.entity.Certificate;
import com.vandung.entity.ExamList;
import com.vandung.entity.Examination;
import com.vandung.entity.Result;
import com.vandung.service.CertificateService;
import com.vandung.service.ExamListService;
import com.vandung.service.ExaminationService;
import com.vandung.service.ResultService;

@RestController
public class DownloadController {

	@Autowired
	private ExaminationService examinationService;
	
	@Autowired
	private ExamListService examlistService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private ServletContext context;
	
	/*
	 * @RequestMapping(value = "/createPdf-council", method = RequestMethod.GET)
	 * public void createPdfCouncil(HttpServletRequest request, HttpServletResponse
	 * response) { Examination entity = examinationService.getExaminationNow();
	 * Boolean isFlag = examinationService.createPdf(entity, context, request,
	 * response); if(isFlag) { String fullPath =
	 * request.getServletContext().getRealPath("/resources/reports/" + "hoidongthi"
	 * + ".pdf"); fileDownlaod(fullPath, response, "hoidongthi.pdf"); } }
	 */

	@RequestMapping(value = "/createExcel-council", method = RequestMethod.GET)
	public void createExcelCouncil(HttpServletRequest request, HttpServletResponse response) {
		Examination entity = examinationService.getExaminationNow();
		Boolean isFlag = examinationService.createExcel(entity, context, request, response);
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "hoidongthi" + ".xls");
			fileDownload(fullPath, response, "hoidongthi.xls");
		}
	}
	
	@RequestMapping(value = "/createExcel-collectMoney", method = RequestMethod.GET)
	public void createExcelCollectMoneyList(HttpServletRequest request, HttpServletResponse response) {
		List<ExamList> entity = examlistService.getListByNotConfirm();
		Boolean isFlag = examlistService.createExcel(entity, context, request, response);
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "danhsachthutien" + ".xls");
			fileDownload(fullPath, response, "danhsachthutien.xls");
		}
	}
	
	@RequestMapping(value = "/createExcel-exam", method = RequestMethod.GET)
	public void createExcelExamList(HttpServletRequest request, HttpServletResponse response) {
		List<ExamList> entity = examlistService.getListByConfirm();
		Boolean isFlag = examlistService.createExcel(entity, context, request, response);
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "danhsachthi" + ".xls");
			fileDownload(fullPath, response, "danhsachthi.xls");
		}
	}
		
	@RequestMapping(value = "/createExcel-collectTest", method = RequestMethod.GET)
	public void createExcelCollectTestList(HttpServletRequest request, HttpServletResponse response) {
		List<ExamList> entity = examlistService.getListByConfirm();
		Boolean isFlag = resultService.createExcelCollectTestList(entity, context, request, response);
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "danhsachthubai" + ".xls");
			fileDownload(fullPath, response, "danhsachthubai.xls");
		}
	}
		
	@RequestMapping(value = "/createExcel-result", method = RequestMethod.GET)
	public void createExcelResult(HttpServletRequest request, HttpServletResponse response) {
		List<Result> entity = resultService.getAllByExamination();
		Boolean isFlag = resultService.createExcelResultList(entity, context, request, response);
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "ketquathi" + ".xls");
			fileDownload(fullPath, response, "ketquathi.xls");
		}
	}
	
	@RequestMapping(value = "/createExcel-certificate", method = RequestMethod.GET)
	public void createExcelCertificate(HttpServletRequest request, HttpServletResponse response) {
		List<Certificate> entity = certificateService.getAllByExamination();
		Boolean isFlag = certificateService.createExcel(entity, context, request, response);
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "danhsachcapchungchi" + ".xls");
			fileDownload(fullPath, response, "danhsachcapchungchi.xls");
		}
	}
	
	private void fileDownload(String fullPath, HttpServletResponse response, String filename) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if(file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + filename);
				OutputStream os = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while((bytesRead = fis.read(buffer)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				fis.close();
				os.close();
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
