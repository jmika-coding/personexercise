package connection;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

@Singleton
public class TasksModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DatabaseConnection.class).asEagerSingleton();
    }
}