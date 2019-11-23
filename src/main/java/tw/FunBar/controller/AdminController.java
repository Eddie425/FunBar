package tw.FunBar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.FunBar.model.OrderBean;
import tw.FunBar.model.RoomOrder;
import tw.FunBar.service.OrderHandleService;
import tw.FunBar.service.RoomService;

@Controller
public class AdminController {

		
	@Autowired
	RoomService roomService;
	
	@Autowired
	OrderHandleService orderService;
		
	@RequestMapping("/admin")
	public String admin(Model model) {
		
		
		ArrayList<RoomOrder> orders = roomService.getLatestOrders();	
		int	roomTotalIncome = roomService.getRoomTotalIncome();
		
		List<OrderBean> list = orderService.getLatestOrders();
		int productTotalIncome = orderService.getProductTotalIncome();
		
		model.addAttribute("ob", list);
		model.addAttribute("productTotalIncome", productTotalIncome);
		
		model.addAttribute("orders", orders);
		model.addAttribute("roomTotalIncome", roomTotalIncome);
		
		return "admin";
	}
}
