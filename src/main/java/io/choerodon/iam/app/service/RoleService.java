package io.choerodon.iam.app.service;

import com.github.pagehelper.PageInfo;
import io.choerodon.base.domain.PageRequest;
import io.choerodon.iam.api.query.ClientRoleQuery;
import io.choerodon.iam.api.dto.RoleAssignmentSearchDTO;
import io.choerodon.iam.api.query.RoleQuery;
import io.choerodon.iam.infra.dto.RoleDTO;

import java.util.List;

/**
 * @author superlee
 * @author wuguokai
 */
public interface RoleService {

    PageInfo<RoleDTO> pagingSearch(PageRequest pageRequest, RoleQuery roleQuery);

    PageInfo<RoleDTO> pagingQueryOrgRoles(Long orgId, PageRequest pageRequest, RoleQuery roleQuery);

    RoleDTO create(RoleDTO roleDTO);

    RoleDTO createBaseOnRoles(RoleDTO roleDTO);

    RoleDTO update(RoleDTO roleDTO);

    RoleDTO orgUpdate(RoleDTO roleDTO,Long orgId);

    void delete(Long id);

    RoleDTO queryById(Long id);

    RoleDTO enableRole(Long id);

    RoleDTO disableRole(Long id);

    RoleDTO orgEnableRole(Long roleId,Long orgId);

    RoleDTO orgDisableRole(Long roleId,Long orgId);

    RoleDTO queryWithPermissionsAndLabels(Long id);

    List<RoleDTO> listRolesWithUserCountOnSiteLevel(RoleAssignmentSearchDTO roleAssignmentSearchDTO);

    List<RoleDTO> listRolesWithClientCountOnSiteLevel(ClientRoleQuery clientRoleSearchDTO);

    List<RoleDTO> listRolesWithUserCountOnOrganizationLevel(RoleAssignmentSearchDTO roleAssignmentSearchDTO, Long sourceId);

    List<RoleDTO> listRolesWithClientCountOnOrganizationLevel(ClientRoleQuery clientRoleSearchDTO, Long sourceId);

    List<RoleDTO> listRolesWithUserCountOnProjectLevel(RoleAssignmentSearchDTO roleAssignmentSearchDTO, Long sourceId);

    List<RoleDTO> listRolesWithClientCountOnProjectLevel(ClientRoleQuery clientRoleSearchDTO, Long sourceId);

    void check(RoleDTO role);

    List<Long> queryIdsByLabelNameAndLabelType(String labelName, String labelType);

    List<RoleDTO> selectByLabel(String label, Long organizationId);

    List<RoleDTO> listRolesBySourceIdAndTypeAndUserId(String sourceType, Long sourceId, Long userId);

    RoleDTO queryByCode(String code);
}
