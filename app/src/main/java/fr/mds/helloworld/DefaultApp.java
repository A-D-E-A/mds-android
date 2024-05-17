package fr.mds.helloworld;

import android.app.Application;

import fr.mds.helloworld.data.BaseDIContainer;
import fr.mds.helloworld.data.DIContainer;

public class DefaultApp extends Application {
    DIContainer container;

    @Override
    public void onCreate() {
        super.onCreate();
        container = new BaseDIContainer();
    }
}
