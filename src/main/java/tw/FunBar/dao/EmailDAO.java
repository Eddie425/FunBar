package tw.FunBar.dao;

import java.util.List;

import tw.FunBar.model.Activity;
import tw.FunBar.model.Applicant;
import tw.FunBar.model.BookingData;
import tw.FunBar.model.Member;
import tw.FunBar.model.Room;
import tw.FunBar.model.RoomOrder;

public interface EmailDAO {
		void sendEmail(RoomOrder room_order,Room room);

		void sendBookingEmail(BookingData data);

		void sendmembercheck(Member mail);

		
		void sendActivityEmail(Applicant email,Activity activity);
		
}
