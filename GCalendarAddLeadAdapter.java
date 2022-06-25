package aj.csi.bots;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import org.slf4j.LoggerFactory;

import aj.csi.ConfigurationService;
import aj.csi.DateUtil;
import aj.csi.MessageUtil;
import aj.csi.pim.form.LeadAcquisitionPage;
import aj.csi.pim.lead.Lead;
import aj.csi.pim.tenant.JunoBusiness;
import aj.csi.tz.TimeZoneList.TimeZoneMapping;
import goog.cal.CalendarCreateAction;
import goog.cal.CalendarInfo;

public class GCalendarAddLeadAdapter implements Callable<BotResponse> {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GCalendarAddLeadAdapter.class);
	private final Lead lead;
	private final LeadAcquisitionPage form;
	private final ConfigurationService configService;
	private JunoBusiness junoBusiness;

	public GCalendarAddLeadAdapter(JunoBusiness junoBusiness, LeadAcquisitionPage form, Lead lead,
			ConfigurationService configService) {
		this.lead = lead;
		this.form = form;
		this.junoBusiness = junoBusiness;
		this.configService = configService;

	}

	@Override
	public BotResponse call() throws Exception {
		String userToken = junoBusiness.getGoogSystemUserToken();
		String junoBusinessName = junoBusiness.getJunoBusinessName();
		CalendarCreateAction createCalendar = new CalendarCreateAction(userToken, junoBusinessName);		CalendarInfo calInfo = new CalendarInfo();
		String summary = MessageUtil.createCalendarSummary(lead, form, junoBusiness);
		calInfo.setSummary(summary);
		String description = MessageUtil.createCalendarDescription(lead, form, junoBusiness);
		calInfo.setDescription(description);
		String location = MessageUtil.createCalendarLocation(lead, form, junoBusiness);
		calInfo.setLocation(location);

		calInfo.setTimeZone(this.configService.getDefaultTimeZone());

		calInfo.setLeadEmailId(lead.getEmailId());
		String[] attendeeList = this.generateAttendeeList(form, junoBusiness);
		calInfo.setBusinessOwnerId(attendeeList);

		calInfo.setEmailNotificationPreNotification(this.configService.getDefaultCalendarEMailInterval());
		calInfo.setPopUpPreNotification(this.configService.getDefaultCalendarPopupIntercal());
		calInfo.setFrequency(this.configService.getDefaultCalendarRecurFrequency());
		calInfo.setTimeInterval(this.configService.getDefaultCalendarRecurInterval());

		LocalDate tomorrow = DateUtil.getTomorrow();
		String startDate = DateUtil.getDateOnlyStringForGoogleCal(tomorrow);
		calInfo.setStartDate(startDate);
		calInfo.setEndDate(startDate);

		Collection<CalendarInfo> contactCollection = new ArrayList<>();
		contactCollection.add(calInfo);

		createCalendar.execute(contactCollection);

		BotResponse botResponse = new BotResponse();
		botResponse.setBotName(this.getClass().getName());
		botResponse.addDetail("summary", summary);
		botResponse.addDetail("description", description);
		botResponse.setTaskStatus("completed");
		
		return botResponse;

	}

	private String[] generateAttendeeList(LeadAcquisitionPage form, JunoBusiness junoBusiness) {
		String emailId = form.getThankyouCard().getContactInfo().getEmailId();
		String[] contactId;
		if (emailId != null && emailId.contains(",")) {
			contactId = emailId.split(",");
		} else if (emailId != null) {
			contactId = new String[] { emailId };
		} else {
			contactId = new String[] { junoBusiness.getAccountOwnerEmail() };
		}
		return contactId;
	}

}