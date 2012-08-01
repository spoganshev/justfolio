package com.example.folio.client.activity;

import com.example.folio.client.place.CoverPlace;
import com.example.folio.client.place.ProjectPlace;
import com.google.code.ginmvp.client.SimpleActivityMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class FolioActivityMapper extends SimpleActivityMapper {

	@Inject
	public FolioActivityMapper(Provider<CoverActivity> coverProvider,
			Provider<ProjectActivity> projectProvider) {
		addProvider(CoverPlace.class, coverProvider);
		addProvider(ProjectPlace.class, projectProvider);
	}
	
}
