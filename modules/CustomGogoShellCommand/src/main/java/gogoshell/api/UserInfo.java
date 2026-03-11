package gogoshell.api;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;

@Component(
        immediate = true,
        property = {
                "osgi.command.scope=admin",
                "osgi.command.function=getUserByEmail"
        },
        service = Object.class
)
public class UserInfo {

    private static final Log _log =
            LogFactoryUtil.getLog(UserInfo.class);

    public void getUserByEmail(String emailAddress) {

        try {
            List<Company> companies = _companyLocalService.getCompanies();

            for (Company company : companies) {
                User user =
                        _userLocalService.fetchUserByEmailAddress(
                                company.getCompanyId(),
                                emailAddress);

                if (user != null) {

                    System.out.println("------ USER FOUND ------");
                    System.out.println("CompanyId: " + company.getCompanyId());
                    System.out.println("UserId: " + user.getUserId());
                    System.out.println("Full Name: " + user.getFullName());
                    System.out.println("Email: " + user.getEmailAddress());
                    System.out.println("Status: " + user.getStatus());
                    System.out.println("Last Login: " + user.getLastLoginDate());

                    return;
                }
            }

            System.out.println("User not found in any company!");

        } catch (Exception e) {
            _log.error("Error fetching user", e);
        }
    }

    @Reference
    private UserLocalService _userLocalService;

    @Reference
    private CompanyLocalService _companyLocalService;
}
