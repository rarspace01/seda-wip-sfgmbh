package de.sfgmbh.applayer.core.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfCtrlLiveTicker;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;

/**
 * Controller for the live ticker
 * 
 * @author hannes
 *
 */
public class CtrlLiveTicker implements IntfCtrlLiveTicker {
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.controller.IntfCtrlLiveTicker#getTickerAllocations()
	 */
	@Override
	public List<RoomAllocation> getTickerAllocations() {
		
		// Determine the date
		Date now = new Date( );
		SimpleDateFormat hour = new SimpleDateFormat ("k");
		Integer formatHour = Integer.parseInt(hour.format(now));
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(now);
	    // This will return 1 for Sunday and 7 for Saturday
	    Integer formatDay = calendar.get(Calendar.DAY_OF_WEEK);
	    // Fix this so that 1 is Monday
	    if ((formatDay - 1) > 0) {
	    	formatDay = formatDay - 1;
	    } else {
	    	formatDay = 7;
	    }
	    String formatDayString = String.valueOf(formatDay);
	    
	    // Prepare the filter (for the day)
	    HashMap<String, String> filter = new HashMap<String,String>();
	    filter.put("day", formatDayString);
	    // (Semester still hard coded)
	    filter.put("semester", "SS 13");
	    
	    // Get the allocations for the day
	    List<RoomAllocation> dayAllocations = AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(filter);
	    
	    // Determine which allocations to return (they have to be public, yet to start and at most ten)
	    List<RoomAllocation> returnList = new ArrayList<RoomAllocation>();
	    Integer counter = 0;
	    for (RoomAllocation ra : dayAllocations) {
	    	if (ra.isPublic() && 
	    			ra.getTime_() > this.transcodeHour(formatHour)) {
	    		returnList.add(ra);
	    		counter++;
	    		if (counter >= 15) {
	    			break;
	    		}
	    	}
	    }
	    
		return returnList;
	}
	
	private Integer transcodeHour(Integer hour) {
		if (hour <= 8) {
			return 0;
		} else if (hour <= 10) {
			return 1;
		} else if (hour <= 12) {
			return 2;
		} else if (hour <= 14) {
			return 3;
		} else if (hour <= 16) {
			return 4;
		} else if (hour <= 18) {
			return 5;
		} else if (hour <= 20) {
			return 6;
		} else if (hour <= 22) {
			return 7;
		}
		return 0;
	}
}
