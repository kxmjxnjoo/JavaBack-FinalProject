//package com.ezen.springfeed.tmp.service;
//import org.springframework.stereotype.Service;
//import lombok.AllArgsConstructor;
//
//@Service
//@AllArgsConstructor
//public class sendEmailService {
////
////	@Autowired
////	MemberService ms;
////
////    private JavaMailSender mailSender;
////
////    public MailDto createMailAndChangePassword(String email, String name){
////        String str = getTempPassword();
////        MailDto dto = new MailDto();
////        dto.setAddress(email);
////        dto.setTitle(name+"님의 SPRINGFEED 임시비밀번호 안내 이메일 입니다.");
////        dto.setMessage("안녕하세요. SPRINGFEED 임시비밀번호 안내 이메일 입니다."
////        + "\n [" + name + "]" +"님의 임시 비밀번호는 "
////        + str + " 입니다.");
////        updatePassword(str,email);
////        return dto;
////    }
////
//    public String getTempPassword() {
//    	 char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
//                 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
//
//         String str = "";
//
//         int idx = 0;
//         for (int i = 0; i < 10; i++) {
//             idx = (int) (charSet.length * Math.random());
//             str += charSet[idx];
//         }
//         return str;
//	}
////
////	public void updatePassword(String str,String email){
////		ms.updatePassword(str, email);
////    }
////
////	public void mailSend(MailDto mailDto){
////        System.out.println("이멜 전송 완료!");
////        SimpleMailMessage message = new SimpleMailMessage();
////        message.setTo(mailDto.getAddress());
////        message.setSubject(mailDto.getTitle());
////        message.setText(mailDto.getMessage());
////
////        mailSender.send(message);
////    }
////
//}
