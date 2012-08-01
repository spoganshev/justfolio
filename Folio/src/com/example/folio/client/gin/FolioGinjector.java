package com.example.folio.client.gin;

import com.google.code.ginmvp.client.GinMvpDisplay;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

/**
 * GIN injector to use for getting services/event bus etc. Use @Inject annotation to inject
 * dependencies.
 */
@GinModules(FolioGinModule.class)
public interface FolioGinjector extends Ginjector {
	
	EventBus getEventBus();
	
	GinMvpDisplay getMainView();
	
	PlaceHistoryHandler getPlaceHistoryHandler();
	
	PlaceController getPlaceController();
	
}
