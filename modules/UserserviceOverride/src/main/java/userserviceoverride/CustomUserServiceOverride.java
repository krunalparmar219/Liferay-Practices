package userserviceoverride;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.service.UserServiceWrapper;
import org.osgi.service.component.annotations.Component;
@Component(
        service = UserService.class,
        property = {
                "service.ranking:Integer=1000"
        }
)
public class CustomUserServiceOverride extends UserServiceWrapper {

    public CustomUserServiceOverride() {
        super(null);
    }

    @Override
    public User getUserById(long userId) throws PortalException {

        System.out.println("Custom UserService Override Executed");
        return super.getUserById(userId);
    }
}
