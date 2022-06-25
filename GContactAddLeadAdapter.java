package aj.csi.bots;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import aj.csi.ConfigurationService;
import aj.csi.pim.form.LeadAcquisitionPage;
import aj.csi.pim.lead.Lead;
import aj.csi.pim.tenant.JunoBusiness;
import goog.cal.CalendarCreateAction;
import goog.cal.CalendarInfo;
import goog.contact.ContactCreateResponse;
import goog.contact.CreateContactAction;
import goog.contact.GoogleContactInfo;


public class GContactAddLeadAdapter implements Callable<BotResponse> {
	private final Lead lead;
	private final ConfigurationService configService;
	private final LeadAcquisitionPage form;;
	private final JunoBusiness junoBusiness;

	public GContactAddLeadAdapter(Lead lead, ConfigurationService configService, LeadAcquisitionPage form,
			JunoBusiness junoBusiness) {
		this.lead=lead;
		this.configService=configService;
		this.form=form;
		this.junoBusiness=junoBusiness;

	}

	@Override
	public BotResponse call() throws Exception {
	
			String userToken = junoBusiness.getGoogSystemUserToken();
			String junoBusinessName = junoBusiness.getJunoBusinessName();
			CreateContactAction contactAction = new CreateContactAction(userToken, junoBusinessName);
			
			 Collection<GoogleContactInfo> contactCollection = new ArrayList<>();
			 GoogleContactInfo contactInfo = new GoogleContactInfo();
			contactInfo.setFullName(lead.getFirstName() + " " + lead.getLastName());
			contactInfo.setEmaiId(lead.getEmailId());
			contactInfo.setLocation(lead.getUserFedLocation());
			contactInfo.setPhoneNumber(lead.getPhoneNumber());
			contactInfo.setAlternateNumber(lead.getAlternateNumber());
	        contactCollection.add(contactInfo);
	       
	            contactAction.execute(contactCollection);
			
	            BotResponse botResponse = new BotResponse();
	    		botResponse.setBotName(this.getClass().getName());
			
			return botResponse;
	}

}
