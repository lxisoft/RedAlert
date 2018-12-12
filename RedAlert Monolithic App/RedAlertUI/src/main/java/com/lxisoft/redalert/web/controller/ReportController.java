package com.lxisoft.redalert.web.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	HashMap<String, Integer> reportMap = new HashMap<>();
	ReportDTO reportDTO = new ReportDTO();

	@GetMapping(value = "/reports")
	public ReportDTO getReport() {
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setUserName("sahal");
		return reportDTO;
	}

	@RequestMapping(value = "/postReport", method = RequestMethod.POST)
	public String getReport(@RequestParam ReportTypeEnum report, @RequestParam long postId, Model model)

	{
		/* System.out.println("************************ "+report+" "+postId); */
		ResponseEntity<PostDTO> postDTO = postResourceApi.getPostUsingGET(postId);
		/*
		 * System.out.println("#############################postttttttttttt"+
		 * postDTO.toString());
		 */
		reportDTO.setPostId(postDTO.getBody().getId());
		reportDTO.setReportType(report);
		/*
		 * System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@"+reportDTO.toString());
		 */
		reportResourceApi.createReportUsingPOST(reportDTO);
		/*
		 * ResponseEntity<List<ReportDTO>>
		 * reportDto=reportResourceApi.getAllReportsUsingGET(null, null, null,
		 * null, null, null, null, null, null, null);
		 * System.out.println("^^^^^^^^^^^^^^^^^^"+reportDto.getBody());
		 */

		/*
		 * System.out.println("!!!!!!!!!!"+reportMap.values());
		 * System.out.println("fake count: "+
		 * reportMap.get("FAKE")+"prank count: "+reportMap.get("PRANK")
		 * +"Solve count: "+reportMap.get("SOLVED")+"support count: "+reportMap.
		 * get("SUPPORT")+"timed out count: "+reportMap.get("TIMEDOUT"));
		 */
		return "redirect:/redAlertUi/newsPage/newsOfFriend";

	}

	@GetMapping("/getGenuine")
	public String getGenuine(Model model, @RequestParam("id") Long postId) {

		ResponseEntity<List<ReportDTO>> reportDto = reportResourceApi.findAllByPostUsingGET(postId, null, 0, null,
				null, null, 1000, null, null, null, null);
		/*reportResourceApi.findAllByPostUsingGET(id, offset, page, pageNumber, pageSize, paged, size, sort, sortSorted, sortUnsorted, unpaged)*/
		System.out.println(
				"report types**************************************************************************************************************************************************************** ..************** "
						+ reportDto.getBody().size());

		int f = 1;
		int p = 1;
		int s = 1;
		int su = 1;
		int t = 1;

		for (ReportDTO reports : reportDto.getBody()) {
			/*
			 * System.out.println("mapp sizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
			 * +reportMap.size());
			 * System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+reports.
			 * getReportType());
			 */

			if (reports.getReportType() != null) {

				if (reports.getReportType().equals(ReportDTO.ReportTypeEnum.FAKE)) {
					reportMap.put("FAKE", f++);

				} else if (reports.getReportType().equals(ReportDTO.ReportTypeEnum.PRANK)) {
					reportMap.put("PRANK", p++);

				} else if (reports.getReportType().equals(ReportDTO.ReportTypeEnum.SOLVED)) {
					reportMap.put("SOLVED", s++);

				}

				else if (reports.getReportType().equals(ReportDTO.ReportTypeEnum.SUPPORT)) {
					reportMap.put("SUPPORT", su++);

				} else if (reports.getReportType().equals(ReportDTO.ReportTypeEnum.TIMEOUT)) {
					reportMap.put("TIMEDOUT", t++);

				}
			}

		}

		model.addAttribute("reports", reportMap);
		System.out.println("reports ^^^^^^^^^^^^^^^^^^^^^^^^^^" + reportMap.size());
		return "frag1 :: reports";

	}
}