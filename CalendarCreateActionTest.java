package googleapi;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import core.HabServiceException;
import goog.cal.CalendarCreateAction;
import goog.cal.CalendarInfo;

public class CalendarCreateActionTest {

	@Test
	public void testCalendar() throws HabServiceException {

		String accessToken = "ya29.A0ARrdaM9k1rwQOGF-rNRd5jdrfIgcn24acRILPGG33L3CedSl3VEEhRBGhy10FZQbVc5xho4OwDUdPr8aYlZKVRv-VCeyWWfif1Jv-aMOoWUSkiPy9vCsZQWnV8dHRi3tjAF2smZFCfYjZDsTGDHoK8_lnoqg";
		String junoBusinessName = "Juno";
		
		String summary ="AskJuno schedules followup for New lead";
		String location = "800 Howard St., San Francisco, CA 94103";
		String description = "A chance to hear more about Google's developer products.";
		String timeZone = 		"Asia/Kolkata";
		CalendarInfo event = new CalendarInfo();
		event.setSummary(summary);
		event.setLocation(location);
		event.setDescription(description);
		event.setTimeZone(timeZone);
		
		event.setStartDate("2022-01-19T15:00:00-07:00");
		event.setEndDate("2022-01-19T15:15:00-07:00");
		String[] bizOwner = new String[2];
		
		bizOwner[0]="calep@askjuno.com";
		bizOwner[1]="jayapratha.kajapathi@askjuno.com";
		event.setBusinessOwnerId(bizOwner);
		
		Collection<CalendarInfo> action = new ArrayList<>();
		action.add(event);
		
		CalendarCreateAction calendar=new CalendarCreateAction(accessToken, junoBusinessName);
		calendar.execute(action);
		System.out.printf("Event created: %s\n", event.getBusinessAttendees());
	}

}
