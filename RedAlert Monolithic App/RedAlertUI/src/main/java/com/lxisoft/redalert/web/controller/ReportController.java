package com.lxisoft.redalert.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.red_alert.api.PostResourceApi;
import com.lxisoft.redalert.client.red_alert.api.ReportResourceApi;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;
import com.lxisoft.redalert.client.red_alert.model.ReportDTO;
import com.lxisoft.redalert.client.red_alert.model.ReportDTO.ReportTypeEnum;

@Controller
@RequestMapping("/reportController")
public class ReportController {
	  @Autowired
      ReportResourceApi reportResourceApi;
	  @Autowired
	  PostResourceApi postResourceApi;
	  ReportDTO reportDTO = new ReportDTO();
	  
	  @RequestMapping(value="/getReport",method=RequestMethod.POST)
	public String getReport(@RequestParam ReportTypeEnum report)
	{
		  System.out.println("************************ "+report);
	ResponseEntity<PostDTO> postDTO =	postResourceApi.getPostUsingGET((long) 1);
	System.out.println("#############################"+postDTO.toString());
	reportDTO.setPostId(postDTO.getBody().getId()); 
	reportDTO.setReportType(report);
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@"+reportDTO.toString());
	reportResourceApi.createReportUsingPOST(reportDTO);
	/*ResponseEntity<List<ReportDTO>> reportDto=reportResourceApi.getAllReportsUsingGET(null, null, null, null, null, null, null, null, null, null);
	System.out.println("^^^^^^^^^^^^^^^^^^"+reportDto.getBody());	
	*/
	ResponseEntity<ReportDTO> reportDto = reportResourceApi.findAllByPostUsingGET((long)1);
	System.out.println("report types ..************** "+reportDto.getBody().getReportType());
	return "news";
	}
} 
