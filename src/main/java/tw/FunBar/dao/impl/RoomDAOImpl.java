package tw.FunBar.dao.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import tw.FunBar.dao.RoomDAO;
import tw.FunBar.model.BookingData;
import tw.FunBar.model.Room;
import tw.FunBar.model.RoomOrder;
import tw.FunBar.model.RoomOrderInDays;

@Repository
public class RoomDAOImpl implements RoomDAO {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public Set<Room> pullRoom(String date, Integer stay, Integer rooms) throws ParseException {

		ArrayList<Room> all = allRoom();

		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

		Date D = fm.parse(date);

		Calendar calendar = new GregorianCalendar();

		calendar.setTime(D);

		D = calendar.getTime();

		String check_in_time = fm.format(D);

		Set<Room> allroom = new HashSet<>();

		for (Room o : all) {

			String hql = "From RoomOrderInDays Where check_in_time =:check_in_time and room_id =:room_id";

			int x = 0;
			for (int i = 1; i <= stay; i++) {
				Session session = sessionFactory.getCurrentSession();
				ArrayList<RoomOrderInDays> r = (ArrayList<RoomOrderInDays>) session.createQuery(hql)
						.setParameter("check_in_time", check_in_time).setParameter("room_id", o.getRoom_id())
						.getResultList();
				int TypeCount = 0;
				for (RoomOrderInDays rs : r) {
					TypeCount += rs.getRooms();
				}

				if (o.getRoom_quantity() - TypeCount >= rooms) {
					x += 1;
				} else {
					x -= 1;
				}

				calendar.add(Calendar.DATE, 1);
				D = calendar.getTime();
				check_in_time = fm.format(D);

			}

			if (x == stay) {
				allroom.add(o);
			}

			D = fm.parse(date);

			calendar.setTime(D);

			D = calendar.getTime();

			check_in_time = fm.format(D);
		}

		return allroom;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Room> allRoom() {
		String hql = "From Room order by room_id ASC";
		Session session = sessionFactory.getCurrentSession();
		ArrayList<Room> all = (ArrayList<Room>) session.createQuery(hql).getResultList();

		return all;
	}

	@Override
	public void addRoom(Room room) {
		MultipartFile imageCover = room.getImageCover();
		String originalFilename = imageCover.getOriginalFilename();
		System.out.println("originalFilename:" + originalFilename);
		room.setRoom_image_filename(originalFilename);
		if (imageCover != null && !imageCover.isEmpty()) {
			try {
				byte[] b = imageCover.getBytes();
				Blob blob = new SerialBlob(b);
				room.setRoom_image(blob);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Session session = sessionFactory.getCurrentSession();
		session.save(room);

	}

	@Override
	public Room getImage(Integer room_id) {
		Session session = sessionFactory.getCurrentSession();
		Room room = session.get(Room.class, room_id);

		return room;
	}

	@Override
	public Room getRoomById(Integer room_id) {
		Session session = sessionFactory.getCurrentSession();
		Room room = session.get(Room.class, room_id);
		return room;
	}

	@Override
	public void addRoomOrder(RoomOrder room_order) throws ParseException {
		Session session = sessionFactory.getCurrentSession();

		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

		Date D = fm.parse(room_order.getCheck_in_time());

		Calendar calendar = new GregorianCalendar();

		calendar.setTime(D);

		D = calendar.getTime();

		int stay = Integer.parseInt(room_order.getCheck_out_time());

		calendar.add(Calendar.DATE, stay);
		D = calendar.getTime();
		String check_out_time = fm.format(D);

		room_order.setCheck_out_time(check_out_time);

		session.save(room_order);

	}

	public void addOrderInDays(RoomOrder room_order) throws ParseException {

		Session session = sessionFactory.getCurrentSession();

		Date day;
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

		String date = room_order.getCheck_in_time();

		day = fm.parse(date);

		String st = room_order.getCheck_out_time();

		Integer stay = Integer.parseInt(st);

		String theDay;

		Calendar calendar = new GregorianCalendar();

		calendar.setTime(day);

		for (int i = 1; i <= stay; i++) {

			day = calendar.getTime();

			theDay = fm.format(day);

			RoomOrderInDays roomOrderInDays = new RoomOrderInDays();

			roomOrderInDays.setRoom_id(room_order.getRoom_id());
			roomOrderInDays.setCheck_in_time(theDay);
			roomOrderInDays.setRooms(room_order.getRooms());

			session.save(roomOrderInDays);
			calendar.add(Calendar.DATE, 1);

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RoomOrder> allOrder() {

		String hql = "From RoomOrder Order by check_in_time";
		Session session = sessionFactory.getCurrentSession();

		ArrayList<RoomOrder> orders = (ArrayList<RoomOrder>) session.createQuery(hql).getResultList();

		return orders;
	}

	@Override
	public RoomOrder pullOrderSingle(Integer id) {

		String hql = "From RoomOrder where order_id = :id";
		Session session = sessionFactory.getCurrentSession();
		RoomOrder one = (RoomOrder) session.createQuery(hql).setParameter("id", id).getSingleResult();
		return one;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Room> pullTodayRoomStatus(String date) throws ParseException {

		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

		Date day = fm.parse(date);

		Calendar calendar = new GregorianCalendar();

		calendar.setTime(day);

		day = calendar.getTime();

		String theDay = fm.format(day);

		String hql = "From RoomOrderInDays where check_in_time = :date and room_id = :room_id";
		Session session = sessionFactory.getCurrentSession();

		ArrayList<Room> allroom = allRoom();

		ArrayList<Room> status = new ArrayList<>();

		for (Room type : allroom) {

			ArrayList<RoomOrderInDays> od = (ArrayList<RoomOrderInDays>) session.createQuery(hql)
					.setParameter("date", theDay).setParameter("room_id", type.getRoom_id()).getResultList();
			int rooms = type.getRoom_quantity();
			Room room = new Room();
			for (RoomOrderInDays ost : od) {
				rooms -= ost.getRooms();
			}
			room.setRoom_type(type.getRoom_type());
			room.setRoom_quantity(rooms);
			status.add(room);

		}

		return status;
	}

	@Override
	public void cancelRoom(Integer id) {

		Session session = sessionFactory.getCurrentSession();

		Room one = session.get(Room.class, id);

		session.delete(one);

	}

	@Override
	public void updateRoom(Integer room_id, String room_type, Integer room_quantity, Integer room_price,
			Integer room_people, String room_bed, Integer room_pings, String room_description, MultipartFile imageCover)
			throws IOException {

		Blob image = null;
		Room room = null;

		if (imageCover.getSize() == 0) {
			room = getRoomById(room_id);
			image = room.getRoom_image();
		} else {
			MultipartFile Cover = imageCover;
			byte[] b = Cover.getBytes();
			try {
				image = new SerialBlob(b);
			} catch (SerialException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		room.setRoom_type(room_type);
		room.setRoom_quantity(room_quantity);
		room.setRoom_people(room_people);
		room.setRoom_price(room_price);
		room.setRoom_bed(room_bed);
		room.setRoom_pings(room_pings);
		room.setRoom_description(room_description);
		room.setRoom_image(image);

		Session session = sessionFactory.getCurrentSession();
		session.update(room);

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RoomOrder> dateSearch(String date) throws ParseException {

		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

		Date day = fm.parse(date);

		Calendar calendar = new GregorianCalendar();

		calendar.setTime(day);

		day = calendar.getTime();

		String theDay = fm.format(day);

		String hql = "From RoomOrder where check_in_time = :date order by check_out_time ASC";

		Session session = sessionFactory.getCurrentSession();

		ArrayList<RoomOrder> ro = (ArrayList<RoomOrder>) session.createQuery(hql).setParameter("date", theDay)
				.getResultList();

		return ro;
	}

	@Override
	public RoomOrder roomNumQuery(Integer number) {

		String hql = "From RoomOrder where order_id : order_id";

		Session session = sessionFactory.getCurrentSession();

		RoomOrder order = session.get(RoomOrder.class, number);
		return order;
	}

	@Override
	public void cancelOrder(Integer id) {

		Session session = sessionFactory.getCurrentSession();

		RoomOrder order = session.get(RoomOrder.class, id);

		session.delete(order);

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RoomOrder> phoneSearch(String phone) {
		String hql = "From RoomOrder where order_phone = :phone order by check_out_time ASC";

		Session session = sessionFactory.getCurrentSession();

		ArrayList<RoomOrder> ro = (ArrayList<RoomOrder>) session.createQuery(hql).setParameter("phone", phone)
				.getResultList();

		return ro;
	}

}