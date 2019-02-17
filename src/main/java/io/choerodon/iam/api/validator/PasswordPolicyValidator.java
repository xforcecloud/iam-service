package io.choerodon.iam.api.validator;

import io.choerodon.core.exception.CommonException;
import io.choerodon.iam.api.dto.PasswordPolicyDTO;
import io.choerodon.iam.infra.dataobject.PasswordPolicyDO;
import io.choerodon.iam.infra.mapper.PasswordPolicyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wuguokai
 */
@Component
public class PasswordPolicyValidator {
    @Autowired
    private PasswordPolicyMapper passwordPolicyMapper;

    public void create(Long orgId, PasswordPolicyDTO passwordPolicyDTO) {
        PasswordPolicyDO passwordPolicyDO = new PasswordPolicyDO();
        passwordPolicyDO.setOrganizationId(orgId);
        if (!passwordPolicyMapper.select(passwordPolicyDO).isEmpty()) {
            throw new CommonException("error.passwordPolicy.organizationId.exist");
        }
        passwordPolicyDO.setOrganizationId(null);
        passwordPolicyDO.setCode(passwordPolicyDTO.getCode());
        if (!passwordPolicyMapper.select(passwordPolicyDO).isEmpty()) {
            throw new CommonException("error.passwordPolicy.code.exist");
        }
    }

    public void update(Long orgId, Long passwordPolicyId, PasswordPolicyDTO passwordPolicyDTO) {
        PasswordPolicyDO passwordPolicyDO = passwordPolicyMapper.selectByPrimaryKey(passwordPolicyId);
        if (passwordPolicyDO == null) {
            throw new CommonException("error.passwordPolicy.not.exist");
        }
        if (!orgId.equals(passwordPolicyDO.getOrganizationId())) {
            throw new CommonException("error.passwordPolicy.organizationId.not.same");
        }

        if (passwordPolicyDTO.getMinLength() < 8) {
            throw new CommonException("error.minLength");
        }

        // the sum of all the fields with least length requirement is greater than maxLength
        int allLeastRequiredLength = passwordPolicyDTO.getDigitsCount() +
                passwordPolicyDTO.getSpecialCharCount() +
                passwordPolicyDTO.getLowercaseCount() +
                passwordPolicyDTO.getUppercaseCount();
        if (allLeastRequiredLength > passwordPolicyDTO.getMaxLength()) {
            throw new CommonException("error.allLeastRequiredLength.greaterThan.maxLength");
        }

        if (passwordPolicyDTO.getMinLength() > passwordPolicyDTO.getMaxLength()) {
            throw new CommonException("error.maxLength.lessThan.minLength");
        }

        passwordPolicyDTO.setCode(null);
        passwordPolicyDTO.setOrganizationId(null);
    }

}
