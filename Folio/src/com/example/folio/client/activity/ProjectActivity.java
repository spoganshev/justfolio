package com.example.folio.client.activity;

import com.example.folio.client.project.ProjectPage;
import com.google.code.ginmvp.client.GinMvpDisplay;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class ProjectActivity extends AbstractActivity {

	@Inject
	private GinMvpDisplay mainView;
	
	@Inject
	private ProjectPage projectPage;
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		mainView.setWidget(projectPage);
	}
	
}
