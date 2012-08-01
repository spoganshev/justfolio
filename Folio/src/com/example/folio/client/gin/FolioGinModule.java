package com.example.folio.client.gin;

import com.example.folio.client.MainViewImpl;
import com.example.folio.client.activity.FolioActivityMapper;
import com.example.folio.client.place.CoverPlace;
import com.example.folio.client.project.ProjectPage;
import com.google.code.ginmvp.client.GinMvpModule;
import com.google.gwt.inject.client.AbstractGinModule;

/**
 * Dependency injection configuration. Binds keys to implementation. It is possible to configure
 * singletons or prototypes. By default it's prototype - every time the object is looked up
 * a new instance will be constructed.
 */
public class FolioGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		// GinMvpModule provides EventBus, PlaceController, PlaceHistoryHandler
		install(new GinMvpModule(FolioActivityMapper.class, CoverPlace.class, MainViewImpl.class));
		
		bind(ProjectPage.class);
	}

}
