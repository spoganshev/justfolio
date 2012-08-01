package com.example.folio.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ProjectPlace extends Place {

	@Prefix("project")
    public static class Tokenizer implements PlaceTokenizer<ProjectPlace> {
        @Override
        public String getToken(ProjectPlace place) {
            return "";
        }

        @Override
        public ProjectPlace getPlace(String token) {
            return new ProjectPlace();
        }
    }
	
}
