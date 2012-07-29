package com.example.folio.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Dependency injection configuration. Binds keys to implementation. It is possible to configure
 * singletons or prototypes. By default it's prototype - every time the object is looked up
 * a new instance will be constructed.
 */
public class FolioGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
	}

}
