package com.lxisoft.redalert.service.mapper;

import com.lxisoft.redalert.domain.*;
import com.lxisoft.redalert.service.dto.ReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Report and its DTO ReportDTO.
 */
@Mapper(componentModel = "spring", uses = {PostMapper.class, ActionMapper.class})
public interface ReportMapper extends EntityMapper<ReportDTO, Report> {

    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "action.id", target = "actionId")
    ReportDTO toDto(Report report);

    @Mapping(source = "postId", target = "post")
    @Mapping(source = "actionId", target = "action")
    Report toEntity(ReportDTO reportDTO);

    default Report fromId(Long id) {
        if (id == null) {
            return null;
        }
        Report report = new Report();
        report.setId(id);
        return report;
    }
}
