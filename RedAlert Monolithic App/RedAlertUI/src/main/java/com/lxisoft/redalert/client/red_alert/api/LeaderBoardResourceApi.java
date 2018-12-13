/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.lxisoft.redalert.client.red_alert.api;

import com.lxisoft.redalert.client.red_alert.model.LeaderBoardDTO;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-28T12:49:43.721+05:30[Asia/Calcutta]")

@Api(value = "LeaderBoardResource", description = "the LeaderBoardResource API")
public interface LeaderBoardResourceApi {

    @ApiOperation(value = "createLeaderBoard", nickname = "createLeaderBoardUsingPOST", notes = "", response = LeaderBoardDTO.class, tags={ "leader-board-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = LeaderBoardDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/leader-boards",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<LeaderBoardDTO> createLeaderBoardUsingPOST(@ApiParam(value = "leaderBoardDTO" ,required=true )  @Valid @RequestBody LeaderBoardDTO leaderBoardDTO);


    @ApiOperation(value = "deleteLeaderBoard", nickname = "deleteLeaderBoardUsingDELETE", notes = "", tags={ "leader-board-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/apis/leader-boards/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteLeaderBoardUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllLeaderBoards", nickname = "getAllLeaderBoardsUsingGET", notes = "", response = LeaderBoardDTO.class, responseContainer = "List", tags={ "leader-board-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = LeaderBoardDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/leader-boards",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<LeaderBoardDTO>> getAllLeaderBoardsUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getLeaderBoard", nickname = "getLeaderBoardUsingGET", notes = "", response = LeaderBoardDTO.class, tags={ "leader-board-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = LeaderBoardDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/leader-boards/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<LeaderBoardDTO> getLeaderBoardUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "updateLeaderBoard", nickname = "updateLeaderBoardUsingPUT", notes = "", response = LeaderBoardDTO.class, tags={ "leader-board-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = LeaderBoardDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/leader-boards",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<LeaderBoardDTO> updateLeaderBoardUsingPUT(@ApiParam(value = "leaderBoardDTO" ,required=true )  @Valid @RequestBody LeaderBoardDTO leaderBoardDTO);

}
