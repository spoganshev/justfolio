package com.example.folio.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class ProjectPlace extends Place {
	
	/**
	 * Gets generated by tokenizer when some invalid history token got parsed.
	 */
	public static final ProjectPlace INVALID_PROJECT_PLACE = new ProjectPlace();
	
	/**
	 * Name of a project to show.
	 */
	private String projectName;
	
	/**
	 * Name of an item to show.
	 */
	private String itemName;
	
	public ProjectPlace() {
	}
	
	public ProjectPlace(String projectName, String itemName) {
		this.projectName = projectName;
		this.itemName = itemName;
	}
	
	public ProjectPlace(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Prefix("project")
    public static class Tokenizer implements PlaceTokenizer<ProjectPlace> {
		
		private static final RegExp REGEXP = RegExp.compile("(\\w+);item=(\\w+)");
		
        @Override
        public String getToken(ProjectPlace place) {
            return place.getProjectName() + ";item=" + place.getItemName();
        }

        @Override
        public ProjectPlace getPlace(String token) {
        	MatchResult matcher = REGEXP.exec(token);
        	if (matcher.getGroupCount() != 3) {
        		return INVALID_PROJECT_PLACE;
        	}
            return new ProjectPlace(matcher.getGroup(1), matcher.getGroup(2));
        }
    }
	
}