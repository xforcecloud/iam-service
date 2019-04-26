package io.choerodon.iam.app.service;

import io.choerodon.iam.api.dto.ProjectRelationshipDTO;
import io.choerodon.iam.api.dto.RelationshipCheckDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Eugen
 */
public interface ProjectRelationshipService {
    /**
     * 查询一个项目群下的子项目(默认查所有子项目，可传参只查启用的子项目).
     *
     * @param parentId         父级Id
     * @param onlySelectEnable 是否只查启用项目
     * @return 项目群下的子项目列表
     */
    List<ProjectRelationshipDTO> getProjUnderGroup(Long parentId, Boolean onlySelectEnable);

    /**
     * 项目组下移除项目
     *
     * @param orgId   组织Id
     * @param groupId 项目群关系Id
     */
    void removesAProjUnderGroup(Long orgId, Long groupId);

    /**
     * 查询项目在该项目组下的不可用时间
     *
     * @param projectId 项目id
     * @param parentId  项目组id
     * @return
     */
    List<Map<String, Date>> getUnavailableTime(Long projectId, Long parentId);


    /**
     * 批量修改/新增/启停用项目组
     *
     * @param list
     * @return
     */
    List<ProjectRelationshipDTO> batchUpdateRelationShipUnderProgram(Long orgId, List<ProjectRelationshipDTO> list);

    /**
     * 校验项目关系能否被启用
     *
     * @param id
     * @return
     */
    RelationshipCheckDTO checkRelationshipCanBeEnabled(Long id);
}
