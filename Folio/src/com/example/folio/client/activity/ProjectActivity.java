package com.example.folio.client.activity;

import com.example.folio.client.place.ProjectPlace;
import com.example.folio.client.project.ProjectPage;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class ProjectActivity extends BaseActivity {

	@Inject
	private ProjectPage projectPage;
	
	private boolean currenltyOnProjectPage = false;
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		if (!currenltyOnProjectPage) {
			panel.setWidget(projectPage);
		}
		currenltyOnProjectPage = true;
	}
	
	@Override
	public void onStop() {
		super.onStop();
		currenltyOnProjectPage = false;
	}
	
	@Override
	protected void init(Place place) {
		super.init(place);
		ProjectPlace projectPlace = (ProjectPlace) place;
		projectPage.setProjectAndItem(projectPlace.getProjectName(), projectPlace.getItemName());
	}
	
}
