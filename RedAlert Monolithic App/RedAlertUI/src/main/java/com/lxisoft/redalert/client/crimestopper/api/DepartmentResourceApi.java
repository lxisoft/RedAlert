/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.lxisoft.redalert.client.crimestopper.api;

import com.lxisoft.redalert.client.crimestopper.model.DepartmentDTO;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-02-09T12:49:50.445+05:30[Asia/Calcutta]")

@Api(value = "DepartmentResource", description = "the DepartmentResource API")
public interface DepartmentResourceApi {

    @ApiOperation(value = "createDepartment", nickname = "createDepartmentUsingPOST", notes = "", response = DepartmentDTO.class, tags={ "department-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = DepartmentDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/departments",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<DepartmentDTO> createDepartmentUsingPOST(@ApiParam(value = "departmentDTO" ,required=true )  @Valid @RequestBody DepartmentDTO departmentDTO);


    @ApiOperation(value = "deleteDepartment", nickname = "deleteDepartmentUsingDELETE", notes = "", tags={ "department-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/departments/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteDepartmentUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllDepartments", nickname = "getAllDepartmentsUsingGET", notes = "", response = DepartmentDTO.class, responseContainer = "List", tags={ "department-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = DepartmentDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/departments",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<DepartmentDTO>> getAllDepartmentsUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getDepartment", nickname = "getDepartmentUsingGET", notes = "", response = DepartmentDTO.class, tags={ "department-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = DepartmentDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/departments/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<DepartmentDTO> getDepartmentUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "updateDepartment", nickname = "updateDepartmentUsingPUT", notes = "", response = DepartmentDTO.class, tags={ "department-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = DepartmentDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/departments",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<DepartmentDTO> updateDepartmentUsingPUT(@ApiParam(value = "departmentDTO" ,required=true )  @Valid @RequestBody DepartmentDTO departmentDTO);

}
