package warehouse;

import model.user.UserProfile;

import java.util.HashMap;
import java.util.Map;

public class UserProfileWarehouse {

    private volatile Map<String, UserProfile> userProfiles = new HashMap<String, UserProfile>();

    public void add(UserProfile userProfile) {
        userProfiles.put(userProfile.getUserName(),userProfile);
    }

    public UserProfile getUserProfileByUserName(String userName) {
        return userProfiles.get(userName);
    }

    public Boolean validatePassword(UserProfile userProfile, String password) {
        String userPassword = userProfile.getPassword();
        return password.equals(userPassword);
    }

    public void removeUserProfile(UserProfile userProfile) {
        removeUserProfileByUserName(userProfile.getUserName());
    }

    public void removeUserProfileByUserName(String userName) {
        userProfiles.remove(userName);
    }
}
