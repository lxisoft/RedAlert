/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.lxisoft.redalert.client.red_alert.api;

import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
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


@Api(value = "UserRegistrationResource", description = "the UserRegistrationResource API")
public interface UserRegistrationResourceApi {

    @ApiOperation(value = "createUserRegistration", nickname = "createUserRegistrationUsingPOST", notes = "", response = UserRegistrationDTO.class, tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/user-registrations",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<UserRegistrationDTO> createUserRegistrationUsingPOST(@ApiParam(value = "userRegistrationDTO" ,required=true )  @Valid @RequestBody UserRegistrationDTO userRegistrationDTO);


    @ApiOperation(value = "deleteUserRegistration", nickname = "deleteUserRegistrationUsingDELETE", notes = "", tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/apis/user-registrations/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUserRegistrationUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllUserRegistrations", nickname = "getAllUserRegistrationsUsingGET", notes = "", response = UserRegistrationDTO.class, responseContainer = "List", tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/user-registrations",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserRegistrationDTO>> getAllUserRegistrationsUsingGET(@ApiParam(value = "eagerload", defaultValue = "false") @Valid @RequestParam(value = "eagerload", required = false, defaultValue="false") Boolean eagerload,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getUserRegistration", nickname = "getUserRegistrationUsingGET", notes = "", response = UserRegistrationDTO.class, tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/user-registrations/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<UserRegistrationDTO> getUserRegistrationUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "inputStartingCharacter", nickname = "inputStartingCharacterUsingGET", notes = "", response = UserRegistrationDTO.class, responseContainer = "List", tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/user-registrations/findstartcharacter/{charname}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserRegistrationDTO>> inputStartingCharacterUsingGET(@ApiParam(value = "charname",required=true) @PathVariable("charname") String charname,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "searchWithFirstNameLastNameEmail", nickname = "searchWithFirstNameLastNameEmailUsingGET", notes = "", response = UserRegistrationDTO.class, responseContainer = "List", tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/user-registrations/findAll/{keyword}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserRegistrationDTO>> searchWithFirstNameLastNameEmailUsingGET(@ApiParam(value = "keyword",required=true) @PathVariable("keyword") String keyword,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "searchWithLastName", nickname = "searchWithLastNameUsingGET", notes = "", response = UserRegistrationDTO.class, responseContainer = "List", tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/user-registrations/finduser/{lastName}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserRegistrationDTO>> searchWithLastNameUsingGET(@ApiParam(value = "lastName",required=true) @PathVariable("lastName") String lastName,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "searchWithUserName", nickname = "searchWithUserNameUsingGET", notes = "", response = UserRegistrationDTO.class, tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/user-registrations/find/{userName}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<UserRegistrationDTO> searchWithUserNameUsingGET(@ApiParam(value = "userName",required=true) @PathVariable("userName") String userName);


    @ApiOperation(value = "updateUserRegistration", nickname = "updateUserRegistrationUsingPUT", notes = "", response = UserRegistrationDTO.class, tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/apis/user-registrations",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<UserRegistrationDTO> updateUserRegistrationUsingPUT(@ApiParam(value = "userRegistrationDTO" ,required=true )  @Valid @RequestBody UserRegistrationDTO userRegistrationDTO);

}
