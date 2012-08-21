package com.example.folio.client.activity;

import com.example.folio.client.place.CoverPlace;
import com.example.folio.client.place.ProjectPlace;
import com.google.code.ginmvp.client.SimpleActivityMapper;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Maps places to activities.
 */
public class FolioActivityMapper extends SimpleActivityMapper {

	private BaseActivity lastActivity;
	
	@Inject
	public FolioActivityMapper(Provider<CoverActivity> coverProvider,
			Provider<ProjectActivity> projectProvider) {
		addProvider(CoverPlace.class, coverProvider);
		addProvider(ProjectPlace.class, projectProvider);
	}
	
	@Override
	public Activity getActivity(Place place) {
		if (lastActivity instanceof ProjectActivity && place instanceof ProjectPlace) {
			return lastActivity; // Return last activity for ProjectPlace if it was ProjectActivity
		}
		lastActivity = (BaseActivity) super.getActivity(place);
		lastActivity.init(place);
		return lastActivity;
	}
	
}
