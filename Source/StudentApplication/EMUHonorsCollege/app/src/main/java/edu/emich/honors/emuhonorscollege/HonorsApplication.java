package edu.emich.honors.emuhonorscollege;

import android.app.Application;

import edu.emich.honors.emuhonorscollege.datatypes.User;

public class HonorsApplication extends Application {

    private static User currentUser = User.getSampleUser();

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
