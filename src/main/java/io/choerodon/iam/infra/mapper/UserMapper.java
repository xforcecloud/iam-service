package io.choerodon.iam.infra.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import io.choerodon.iam.api.dto.RoleAssignmentSearchDTO;
import io.choerodon.iam.api.dto.SimplifiedUserDTO;
import io.choerodon.iam.infra.dataobject.UserDO;
import io.choerodon.mybatis.common.BaseMapper;

/**
 * @author wuguokai
 * @author superlee
 */
public interface UserMapper extends BaseMapper<UserDO> {

    List<UserDO> fulltextSearch(@Param("userDO") UserDO userDO,
                                @Param("param") String param);

    List<UserDO> selectUserWithRolesByOption(
            @Param("roleAssignmentSearchDTO") RoleAssignmentSearchDTO roleAssignmentSearchDTO,
            @Param("sourceId") Long sourceId,
            @Param("sourceType") String sourceType,
            @Param("start") Integer start,
            @Param("size") Integer size,
            @Param("param") String param);

    int selectCountUsers(@Param("roleAssignmentSearchDTO")
                                 RoleAssignmentSearchDTO roleAssignmentSearchDTO,
                         @Param("sourceId") Long sourceId,
                         @Param("sourceType") String sourceType,
                         @Param("param") String param);

    List<UserDO> selectUsersByLevelAndOptions(@Param("sourceType") String sourceType,
                                              @Param("sourceId") Long sourceId,
                                              @Param("userId") Long userId,
                                              @Param("email") String email,
                                              @Param("param") String param);

    Integer selectUserCountFromMemberRoleByOptions(@Param("roleId") Long roleId,
                                                   @Param("memberType") String memberType,
                                                   @Param("sourceId") Long sourceId,
                                                   @Param("sourceType") String sourceType,
                                                   @Param("roleAssignmentSearchDTO")
                                                           RoleAssignmentSearchDTO roleAssignmentSearchDTO,
                                                   @Param("param") String param);

    List selectUsersFromMemberRoleByOptions(@Param("roleId") Long roleId,
                                            @Param("memberType") String memberType,
                                            @Param("sourceId") Long sourceId,
                                            @Param("sourceType") String sourceType,
                                            @Param("roleAssignmentSearchDTO")
                                                    RoleAssignmentSearchDTO roleAssignmentSearchDTO,
                                            @Param("param") String param);


    List<UserDO> listUsersByIds(@Param("ids") Long[] ids, @Param("onlyEnabled") Boolean onlyEnabled);

    List<UserDO> listUsersByEmails(@Param("emails") String[] emails);

    List<UserDO> selectAdminUserPage(@Param("userDO") UserDO userDO, @Param("params") String params);

    Set<String> matchLoginName(@Param("nameSet") Set<String> nameSet);

    Set<String> matchEmail(@Param("emailSet") Set<String> emailSet);

    Long[] listUserIds();


    List<SimplifiedUserDTO> selectAllUsersSimplifiedInfo(@Param("params") String params);
}