package tw.FunBar.controller;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.FunBar.chat.ParticipantRepository;
import tw.FunBar.model.Member;
import tw.FunBar.model.Message;

@Controller
public class ChatController {

	private ParticipantRepository participantRepository;
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@Autowired
	public void setParticipantRepository(ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String loginIntoChatRoom(Model model, HttpServletRequest request, HttpSession session) {
		session = request.getSession(false);
		Member member = (Member) session.getAttribute("member");
		participantRepository.add(String.valueOf(member.getId()), member);
		System.out.println(member);
		System.out.println("在線人數：" + participantRepository.getActiveSessions().values().size());
		System.out.println("member Detail:" + participantRepository.getParticipant(String.valueOf(member.getId())).getId());
		model.addAttribute("member", member);
		return "chat";
	}

//	上線人數
	@SubscribeMapping("/chat/participants")
	public Long getActiveUserNumber() {
		System.out.println("有執行到喔");
		return Long.valueOf(participantRepository.getActiveSessions().values().size());
	}

//	一對一聊天
	@MessageMapping("/message")
	public Message alone2(@Payload Message message, @Header("simpSessionId") String sessionId) {
		System.out.println("測試一對一 Message 傳輸");
		System.out.println("senderId = " + message.getSenderMemberId());
		System.out.println("receiverId = " + message.getReceiverMemberId());
		System.out.println("sessionId = " + sessionId);
		System.out.println("name = " + message.getUserName());
		System.out.println("chatValue = " + message.getContent());
		message.setMessageType("text");
		Date sendDate = new Date();
		message.setSendDate(sendDate);
		int[] conn = { message.getSenderMemberId(), message.getReceiverMemberId() };
		Arrays.sort(conn);
		messagingTemplate.convertAndSend("/member/message/" + conn[0] + "/" + conn[1], message);
		return message;
	}

}