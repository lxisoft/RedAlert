/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.lxisoft.redalert.client.red_alert.api;

import com.lxisoft.redalert.client.red_alert.model.PostDTO;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-01-28T10:13:25.923221+05:30[Asia/Calcutta]")

@Api(value = "PostResource", description = "the PostResource API")
public interface PostResourceApi {

    @ApiOperation(value = "changeAlertLevel", nickname = "changeAlertLevelUsingGET", notes = "", response = PostDTO.class, tags={ "post-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PostDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/changeAlert/{id}/{alertLevel}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<PostDTO> changeAlertLevelUsingGET(@ApiParam(value = "alertLevel",required=true) @PathVariable("alertLevel") String alertLevel,@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "createPost", nickname = "createPostUsingPOST", notes = "", response = PostDTO.class, tags={ "post-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PostDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/posts",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<PostDTO> createPostUsingPOST(@ApiParam(value = "postDTO" ,required=true )  @Valid @RequestBody PostDTO postDTO);


    @ApiOperation(value = "deletePost", nickname = "deletePostUsingDELETE", notes = "", tags={ "post-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/posts/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deletePostUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllPostsByUserRegistrationId", nickname = "getAllPostsByUserRegistrationIdUsingGET", notes = "", response = PostDTO.class, responseContainer = "List", tags={ "post-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PostDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/postsByUserRegistrationId/{userRegistrationId}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<PostDTO>> getAllPostsByUserRegistrationIdUsingGET(@ApiParam(value = "userRegistrationId",required=true) @PathVariable("userRegistrationId") Long userRegistrationId,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllPosts", nickname = "getAllPostsUsingGET", notes = "", response = PostDTO.class, responseContainer = "List", tags={ "post-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PostDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/posts",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<PostDTO>> getAllPostsUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getClosePost", nickname = "getClosePostUsingGET", notes = "", response = PostDTO.class, tags={ "post-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PostDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/postclose/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<PostDTO> getClosePostUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getPost", nickname = "getPostUsingGET", notes = "", response = PostDTO.class, tags={ "post-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PostDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/posts/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<PostDTO> getPostUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "sendMailWithAttachment", nickname = "sendMailWithAttachmentUsingPOST", notes = "", response = String.class, tags={ "post-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = String.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/mail/",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<String> sendMailWithAttachmentUsingPOST(@ApiParam(value = "post" ,required=true )  @Valid @RequestBody PostDTO postDTO);


    @ApiOperation(value = "updatePost", nickname = "updatePostUsingPUT", notes = "", response = PostDTO.class, tags={ "post-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PostDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/posts",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<PostDTO> updatePostUsingPUT(@ApiParam(value = "postDTO" ,required=true )  @Valid @RequestBody PostDTO postDTO);

}
