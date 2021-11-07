package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.base.mapper.BaseMapper;
import ir.maktabsharif.domain.Admin;
import ir.maktabsharif.service.dto.AdminDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper extends BaseMapper<Admin, AdminDTO, Long> {

}
