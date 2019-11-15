package tw.FunBar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.FunBar.dao.EmailDAO;
import tw.FunBar.model.BookingData;
import tw.FunBar.model.Room;
import tw.FunBar.model.RoomOrder;
import tw.FunBar.service.EmailService;


@Service
@Transactional
public class EmailServiceImpl implements EmailService {
	
	
	@Autowired
	EmailDAO emailDAO;

	@Override
	public void sendEmail(RoomOrder room_order,Room room) {
		
		emailDAO.sendEmail(room_order,room);
		
	}

	@Override
	public void sendBookingEmail(BookingData data) {
		
		emailDAO.sendBookingEmail(data);
		
	}
	


}
