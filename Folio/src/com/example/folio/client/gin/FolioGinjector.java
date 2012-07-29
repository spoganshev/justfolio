package com.example.folio.client.gin;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;

/**
 * GIN injector to use for getting services/event bus etc. Use @Inject annotation to inject
 * dependencies.
 */
@GinModules(FolioGinModule.class)
public interface FolioGinjector extends Ginjector {
	
//	ActivityMapper getActivityMapper();
//	
//	PlaceController getPlaceController();
	
	EventBus getEventBus();
	
}
