package com.example.folio.client.activity;

import com.example.folio.client.Folio;
import com.example.folio.client.cover.CoverPage;
import com.google.code.ginmvp.client.GinMvpDisplay;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class CoverActivity extends BaseActivity {

	@Inject
	private GinMvpDisplay mainView;
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		mainView.setWidget(new CoverPage(Folio.instance));
	}

}
