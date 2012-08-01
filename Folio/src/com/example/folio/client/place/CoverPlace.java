package com.example.folio.client.place;

import com.google.code.ginmvp.autoplacehistorymapper.client.UseTokenizer;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

@UseTokenizer(CoverPlace.Tokenizer.class)
public class CoverPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<CoverPlace> {
        @Override
        public String getToken(CoverPlace place) {
            return "";
        }

        @Override
        public CoverPlace getPlace(String token) {
            return new CoverPlace();
        }
    }
	
}
