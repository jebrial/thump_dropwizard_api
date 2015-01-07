package com.jebrial.thumpweb;

import com.jebrial.thumpweb.core.Player;
import com.jebrial.thumpweb.db.PlayerDAO;
import com.jebrial.thumpweb.resources.PlayersResource;
import com.jebrial.thumpweb.resources.PlayerResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class ThumpWebApplication extends Application<ThumpWebConfiguration> {

    public static void main(String[] args) throws Exception {
        new ThumpWebApplication().run(args);
    }

    private final HibernateBundle<ThumpWebConfiguration> hibernateBundle =
	        new HibernateBundle<ThumpWebConfiguration>(Player.class) {
	            @Override
	            public DataSourceFactory getDataSourceFactory(ThumpWebConfiguration configuration) {
	                return configuration.getDataSourceFactory();
	            }
	        };

    @Override
    public void initialize(Bootstrap<ThumpWebConfiguration> bootstrap) {
    	bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<ThumpWebConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ThumpWebConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(ThumpWebConfiguration configuration, Environment environment) {
    	final PlayerDAO dao = new PlayerDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new PlayersResource(dao));
        environment.jersey().register(new PlayerResource(dao));
    }

}
