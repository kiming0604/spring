package org.hype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/member/*")
public class MemberController {

    
    // 비밀번호 변경 처리
    // 실제 DB 연동과 비밀번호 유효성 검사는 서비스에서 처리하게 됩니다!
    // POST 요청으로 사용자의 새로운 비밀번호를 받아 처리하게 됩니다!
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam("userId") String userId, 
                                 @RequestParam("currentPassword") String currentPassword, 
                                 @RequestParam("newPassword") String newPassword, Model model) {
        // 주석: 현재 비밀번호가 맞는지 확인하는 로직이 필요
        // boolean isCurrentPasswordValid = memberService.checkPassword(userId, currentPassword);

        // 주석: 새 비밀번호로 업데이트하는 로직이 필요
        // if (isCurrentPasswordValid) {
        //     memberService.updatePassword(userId, newPassword);
        //     model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
        //     return "/member/updateSuccess"; // 성공 시 결과 페이지로 이동하게 됩니다!
        // } else {
        //     model.addAttribute("error", "현재 비밀번호가 올바르지 않습니다.");
             return "/member/changePassword"; // 실패 시 다시 비밀번호 변경 페이지로 이동하게 됩니다!
        // }
    }

   

    // 전화번호 변경 처리
    // POST 요청으로 사용자의 새로운 전화번호를 받아 처리
    @PostMapping("/updatePhone")
    public String updatePhone(@RequestParam("userId") String userId, 
                              @RequestParam("newPhone") String newPhone, Model model) {
        // 주석: 전화번호를 업데이트하는 로직이 필요
        // memberService.updatePhone(userId, newPhone);
        // model.addAttribute("message", "전화번호가 성공적으로 변경되었습니다.");
         return "/member/updateSuccess"; // 성공 시 결과 페이지로 이동
    }


    // 이메일 변경 처리
    // POST 요청으로 사용자의 새로운 이메일을 받아 처리
    @PostMapping("/updateEmail")
    public String updateEmail(@RequestParam("userId") String userId, 
                              @RequestParam("newEmail") String newEmail, Model model) {
        // 주석: 이메일을 업데이트하는 로직이 필요
        // memberService.updateEmail(userId, newEmail);
        // model.addAttribute("message", "이메일이 성공적으로 변경되었습니다.");
         return "/member/updateSuccess"; // 성공 시 결과 페이지로 이동하게 됩니다!
    }
    // 좋아요한 팝업스토어 목록 띄워주는 메서드
    @GetMapping("/likedPopUpStores")
    public String getLikedPopUpStores(@RequestParam("userId") String userId, Model model) {
        log.info("좋아요한 팝업스토어 목록 조회: " + userId);
        
        // List<PopUpStore> likedStores = memberService.getLikedPopUpStores(userId);
        // model.addAttribute("likedStores", likedStores);

        return "/member/likedPopUpStores"; // 좋아요한 팝업스토어 목록 JSP
    }

    // 목록에서 삭제하는 메서드
    @PostMapping("/removeLikedPopUpStore")
    public String removeLikedPopUpStore(@RequestParam("userId") String userId, @RequestParam("storeId") Long storeId, Model model) {
        log.info("좋아요한 팝업스토어 삭제: " + storeId + " by " + userId);
        
        // boolean isRemoved = memberService.removeLikedPopUpStore(userId, storeId);
        // if (isRemoved) {
        //     model.addAttribute("message", "팝업스토어가 삭제되었습니다.");
        // } else {
        //     model.addAttribute("error", "팝업스토어 삭제에 실패했습니다.");
        // }

        return "redirect:/member/likedPopUpStores"; // 좋아요한 팝업스토어 목록 페이지로 리다이렉트하게 됩니다!
    }

    // 좋아요한 굿즈 목록 띄워주는 메서드
    @GetMapping("/likedGoods")
    public String getLikedGoods(@RequestParam("userId") String userId, Model model) {
        log.info("좋아요한 굿즈 목록 조회: " + userId);
        
        // List<Goods> likedGoods = memberService.getLikedGoods(userId);
        // model.addAttribute("likedGoods", likedGoods);

        return "/member/likedGoods"; // 좋아요한 굿즈 목록 JSP
    }

    // 좋아요한 굿즈 목록에서 삭제하는 메서드
    @PostMapping("/removeLikedGoods")
    public String removeLikedGoods(@RequestParam("userId") String userId, @RequestParam("goodsId") Long goodsId, Model model) {
        log.info("좋아요한 굿즈 삭제: " + goodsId + " by " + userId);
        
        // boolean isRemoved = memberService.removeLikedGoods(userId, goodsId);
        // if (isRemoved) {
        //     model.addAttribute("message", "굿즈가 삭제되었습니다.");
        // } else {
        //     model.addAttribute("error", "굿즈 삭제에 실패했습니다.");
        // }

        return "redirect:/member/likedGoods"; // 좋아요한 굿즈 목록 페이지로 리다이렉트하게 됩니다!
    }

    // 관심사 변경 메서드
    @PostMapping("/updateInterests")
    public String updateInterests(@RequestParam("userId") String userId, @RequestParam("interests") String interests, Model model) {
        log.info("관심사 변경: " + userId + " -> " + interests);
        
        // memberService.updateInterests(userId, interests);
        // model.addAttribute("message", "관심사가 성공적으로 변경되었습니다.");

        return "/member/updateSuccess"; // 성공 시 결과 페이지로 이동하게 됩니다!
    }

    // 회원 탈퇴 메서드
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("userId") String userId, Model model) {
        log.info("회원 탈퇴 요청: " + userId);
        
        // boolean isWithdrawn = memberService.withdraw(userId);
        // if (isWithdrawn) {
        //     model.addAttribute("message", "회원 탈퇴가 완료되었습니다.");
        // } else {
        //     model.addAttribute("error", "회원 탈퇴에 실패했습니다.");
        // }

        return "redirect:/"; // 메인 페이지로 리다이렉트하게 됩니다!
    }
}
