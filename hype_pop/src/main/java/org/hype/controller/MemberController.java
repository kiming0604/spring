package org.hype.controller;

import java.util.List;

import org.hype.domain.gImgVO;
import org.hype.domain.likedGoodsImgVO;
import org.hype.domain.likedPopImgVO;
import org.hype.domain.mCatVO;
import org.hype.domain.pImgVO;
import org.hype.domain.signInVO;
import org.hype.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
   
   @Autowired
   private MemberService mservice;

   // 로그인 페이지로 이동
   @GetMapping("/login")
   public String login() {
      return "member/login";
   }

   // 로그인 처리
   @PostMapping("/login")
   public String login(signInVO svo, Model model) {
      signInVO member = mservice.loginMember(svo);
      
      if (member != null) {       
         return "popUp/popUpMain";
      } else {
         model.addAttribute("error", "로그인에 오류가 있습니다.");
         return "member/login";
      }
   }

   // 회원가입
   @GetMapping("/join")
   public String joinPage() {
      log.info("join now");
      return "member/joinPage";
   }

   // 회원가입 처리
   @PostMapping("/join")
   public String join(signInVO svo, mCatVO mcvo) {
      log.warn("mcvo :" + mcvo.getGame());
      log.warn("mcvo :" + mcvo.getCulture());
      log.warn("mcvo :" + mcvo.getShopping());
      
      mservice.joinMember(svo, mcvo);
      return "popUp/popUpMain";
   }

   // 마이페이지
   @GetMapping("/myPage")
   public String myPage(Model model, @RequestParam("userNo") int userNo) { 
      signInVO userInfo = mservice.selectMyPageInfo(userNo);
      mCatVO userInterests = mservice.selectMyInterest(userNo);
      List<likedPopImgVO> pLikeList = mservice.pLikeList(userNo);
      List<likedGoodsImgVO> gLikeList = mservice.gLikeList(userNo);
      System.out.println("gLikeList :"+ gLikeList);
      
      model.addAttribute("userInfo", userInfo);
      model.addAttribute("userInterests", userInterests);
      model.addAttribute("pLikeList", pLikeList);
      model.addAttribute("gLikeList", gLikeList);
      
      for (likedGoodsImgVO goods : gLikeList) {
         System.out.println("gName: " + goods.getGname());
         System.out.println("gNo: " + goods.getGno());
         System.out.println("uploadPath: " + goods.getUploadPath());
      }
      
      return "member/myPage"; 
   }

   // 비밀번호 변경
   @GetMapping("/passwordChange")
   public String passwordChange(@RequestParam(value = "userNo") int userNo, 
                                @RequestParam("oldPw") String oldPw, 
                                @RequestParam("newPw") String newPw) {
      log.info("비밀번호 변경: userNo=" + userNo);
      
      if (mservice.selectOldPw(userNo, oldPw) > 0) {
         mservice.updateNewPw(oldPw, newPw, userNo);
         return "/member/myPage";
      }
      return "member/myPage"; 
   }

   // 이메일 변경
   @GetMapping("/emailChange")
   public String emailChange(@RequestParam(value = "userNo") int userNo, 
                             @RequestParam("newEmail") String newEmail,
                             Model model) {
      log.info("이메일 변경: userNo=" + userNo);
      log.info("이메일 변경: new Email=" + newEmail);

      int updateCount = mservice.updateNewEmail(newEmail, userNo);
      
      if (updateCount > 0) {
         model.addAttribute("success", "이메일을 변경했습니다.");
      } else {
         model.addAttribute("error", "이메일 변경에 실패했습니다.");
      }

      return "redirect:/member/myPage?userNo=" + userNo;
   }

   // 전화번호 변경
   @GetMapping("/phoneNumberChange")
   public String phoneNumberChange(@RequestParam(value = "userNo") int userNo, 
                                   @RequestParam("oldPhoneNumber") String oldPhoneNumber, 
                                   @RequestParam("newPhoneNumber") String newPhoneNumber,
                                   Model model) {
      log.info("전화번호 변경: userNo=" + userNo);
      
      if (mservice.selectOldPhoneNum(userNo, oldPhoneNumber) > 0) {
         mservice.updateNewPhoneNum(oldPhoneNumber, newPhoneNumber, userNo);
         model.addAttribute("success", "전화번호를 변경했습니다.");
         return "redirect:/member/myPage?userNo=" + userNo;
      }
      model.addAttribute("error", "전화번호 변경에 실패했습니다."); 
      return "redirect:/member/myPage?userNo=" + userNo;
   }

   @GetMapping("/userReply")
   public String userReply() { 
      System.out.println("userReply..");
      return "/member/userReply"; 
   }

   @GetMapping("/myCart")
   public String myCart() { 
      System.out.println("myCart..");
      return "/purchase/myCart"; 
   }

   @GetMapping("/paymentList")
   public String paymentList() { 
      System.out.println("paymentList..");
      return "/purchase/paymentList"; 
   }

   // 비밀번호 업데이트 처리
   @PostMapping("/updatePassword")
   public String updatePassword(@RequestParam("userId") String userId, 
                                @RequestParam("currentPassword") String currentPassword, 
                                @RequestParam("newPassword") String newPassword, Model model) {
      return "/member/changePassword";
   }

   // 전화번호 업데이트 처리
   @PostMapping("/updatePhone")
   public String updatePhone(@RequestParam("userId") String userId, 
                             @RequestParam("newPhone") String newPhone, Model model) {
      return "/member/updateSuccess";
   }

   // 이메일 업데이트 처리
   @PostMapping("/updateEmail")
   public String updateEmail(@RequestParam("userId") String userId, 
                             @RequestParam("newEmail") String newEmail, Model model) {
      return "/member/updateSuccess";
   }

   // 좋아요한 팝업스토어 목록 조회
   @GetMapping("/likedPopUpStores")
   public String getLikedPopUpStores(@RequestParam("userId") String userId, Model model) {
      log.info("좋아요한 팝업스토어 목록 조회: " + userId);
      return "/member/likedPopUpStores";
   }

   // 좋아요한 팝업스토어 삭제
   @PostMapping("/removeLikedPopUpStore")
   public String removeLikedPopUpStore(@RequestParam("userId") String userId, @RequestParam("storeId") Long storeId, Model model) {
      log.info("좋아요한 팝업스토어 삭제: " + storeId + " by " + userId);
      return "redirect:/member/likedPopUpStores";
   }

   // 좋아요한 굿즈 목록 조회
   @GetMapping("/likedGoods")
   public String getLikedGoods(@RequestParam("userId") String userId, Model model) {
      log.info("좋아요한 굿즈 목록 조회: " + userId);
      return "/member/likedGoods";
   }

   // 좋아요한 굿즈 삭제
   @PostMapping("/removeLikedGoods")
   public String removeLikedGoods(@RequestParam("userId") String userId, @RequestParam("goodsId") Long goodsId, Model model) {
      log.info("좋아요한 굿즈 삭제: " + goodsId + " by " + userId);
      return "redirect:/member/likedGoods";
   }

   // 관심사 변경
   @PostMapping("/updateInterests")
   public String updateInterests(@RequestParam("userId") String userId, @RequestParam("interests") String interests, Model model) {
      log.info("관심사 변경: " + userId + " -> " + interests);
      return "/member/updateSuccess";
   }

   // 회원 탈퇴
   @PostMapping("/withdraw")
   public String withdraw(@RequestParam("userId") String userId, Model model) {
      log.info("회원 탈퇴 요청: " + userId);
      return "redirect:/";
   }
}
