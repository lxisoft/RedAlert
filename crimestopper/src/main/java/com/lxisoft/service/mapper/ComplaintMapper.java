package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.ComplaintDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Complaint and its DTO ComplaintDTO.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, DepartmentMapper.class})
public interface ComplaintMapper extends EntityMapper<ComplaintDTO, Complaint> {

    @Mapping(source = "location.id", target = "locationId")
    ComplaintDTO toDto(Complaint complaint);

    @Mapping(target = "userResponses", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(source = "locationId", target = "location")
    Complaint toEntity(ComplaintDTO complaintDTO);

    default Complaint fromId(Long id) {
        if (id == null) {
            return null;
        }
        Complaint complaint = new Complaint();
        complaint.setId(id);
        return complaint;
    }
}
