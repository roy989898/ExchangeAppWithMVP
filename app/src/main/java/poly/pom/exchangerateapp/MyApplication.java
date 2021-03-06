package poly.pom.exchangerateapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApplication extends Application {
    private PresenterComponent component;

    public PresenterComponent getComponent() {
        return component ;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        component = createComponent();



    }

   public PresenterComponent createComponent(){
        return component = DaggerPresenterComponent.builder().presenterModule(new PresenterModule()).build();
    }
}
