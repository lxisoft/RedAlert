/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.lxisoft.redalert.client.red_alert.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-01-30T14:13:16.657136500+05:30[Asia/Calcutta]")


@Api(value = "UserRegistrationResource", description = "the UserRegistrationResource API")
public interface UserRegistrationResourceApi {


    @ApiOperation(value = "addFriend", nickname = "addFriendUsingPOST", notes = "", tags={ "user-registration-resource", })

    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registrations/addFriend/{userId}/{friendId}",
        method = RequestMethod.POST)
    ResponseEntity<Void> addFriendUsingPOST(@ApiParam(value = "friendId",required=true) @PathVariable("friendId") Long friendId,@ApiParam(value = "userId",required=true) @PathVariable("userId") Long userId);

    @ApiOperation(value = "createUserRegistration", nickname = "createUserRegistrationUsingPOST", notes = "", response = UserRegistrationDTO.class, tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registrations",
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
    @RequestMapping(value = "/api/user-registrations/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUserRegistrationUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "findBy	UserId", nickname = "findByUserIdUsingGET", notes = "", response = UserRegistrationDTO.class, tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registration/{id}",
   //  @RequestMapping(value = "/apis/user-registration/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<UserRegistrationDTO> findByUserIdUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") String id);


    @ApiOperation(value = "getAllFriends", nickname = "getAllFriendsUsingGET", notes = "", response = UserRegistrationDTO.class, responseContainer = "List", tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })

    @RequestMapping(value = "/api/user-registrations/getFriends/{userId}",

        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserRegistrationDTO>> getAllFriendsUsingGET(@ApiParam(value = "userId",required=true) @PathVariable("userId") Long userId);


    @ApiOperation(value = "getAllUserRegistrations", nickname = "getAllUserRegistrationsUsingGET", notes = "", response = UserRegistrationDTO.class, responseContainer = "List", tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registrations",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserRegistrationDTO>> getAllUserRegistrationsUsingGET(@ApiParam(value = "eagerload", defaultValue = "false") @Valid @RequestParam(value = "eagerload", required = false, defaultValue="false") Boolean eagerload,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getUserRegistration", nickname = "getUserRegistrationUsingGET", notes = "", response = UserRegistrationDTO.class, tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registrations/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<UserRegistrationDTO> getUserRegistrationUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "inputCharacterContaining", nickname = "inputCharacterContainingUsingGET", notes = "", response = UserRegistrationDTO.class, responseContainer = "List", tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registration/startcharacter",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserRegistrationDTO>> inputCharacterContainingUsingGET(@NotNull @ApiParam(value = "searchTerm", required = true) @Valid @RequestParam(value = "searchTerm", required = true) String searchTerm,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "inputStartingCharacter", nickname = "inputStartingCharacterUsingGET", notes = "", response = UserRegistrationDTO.class, responseContainer = "List", tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registrations/findstartcharacter/{charname}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserRegistrationDTO>> inputStartingCharacterUsingGET(@ApiParam(value = "charname",required=true) @PathVariable("charname") String charname,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "searchWithFirstNameLastNameEmail", nickname = "searchWithFirstNameLastNameEmailUsingGET", notes = "", response = UserRegistrationDTO.class, responseContainer = "List", tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registrations/findAll/{keyword}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserRegistrationDTO>> searchWithFirstNameLastNameEmailUsingGET(@ApiParam(value = "keyword",required=true) @PathVariable("keyword") String keyword,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "searchWithLastName", nickname = "searchWithLastNameUsingGET", notes = "", response = UserRegistrationDTO.class, responseContainer = "List", tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registrations/finduser/{lastName}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserRegistrationDTO>> searchWithLastNameUsingGET(@ApiParam(value = "lastName",required=true) @PathVariable("lastName") String lastName,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "searchWithUserName", nickname = "searchWithUserNameUsingGET", notes = "", response = UserRegistrationDTO.class, tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registrations/find/{userName}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<UserRegistrationDTO> searchWithUserNameUsingGET(@ApiParam(value = "userName",required=true) @PathVariable("userName") String userName);


    @ApiOperation(value = "sendSMS", nickname = "sendSMSUsingGET", notes = "", response = UserRegistrationDTO.class, tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/sendSMS/{userId}/{phoneno}",
        produces = "*/*", 
method = RequestMethod.GET)
    ResponseEntity<UserRegistrationDTO> sendSMSUsingGET(@ApiParam(value = "phoneno",required=true) @PathVariable("phoneno") Long phoneno,@ApiParam(value = "userId",required=true) @PathVariable("userId") String userId);


    @ApiOperation(value = "unFriend", nickname = "unFriendUsingPOST", notes = "", tags={ "user-registration-resource", })

    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registrations/unFriend/{userId}/{friendId}",
        method = RequestMethod.POST)
    ResponseEntity<Void> unFriendUsingPOST(@ApiParam(value = "friendId",required=true) @PathVariable("friendId") Long friendId,@ApiParam(value = "userId",required=true) @PathVariable("userId") Long userId);


    @ApiOperation(value = "updateUserRegistration", nickname = "updateUserRegistrationUsingPUT", notes = "", response = UserRegistrationDTO.class, tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-registrations",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<UserRegistrationDTO> updateUserRegistrationUsingPUT(@ApiParam(value = "userRegistrationDTO" ,required=true )  @Valid @RequestBody UserRegistrationDTO userRegistrationDTO);


    @ApiOperation(value = "validate", nickname = "validateUsingGET", notes = "", response = UserRegistrationDTO.class, tags={ "user-registration-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserRegistrationDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/validate/{phoneno}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<UserRegistrationDTO> validateUsingGET(@ApiParam(value = "phoneno",required=true) @PathVariable("phoneno") String phoneno);


}
