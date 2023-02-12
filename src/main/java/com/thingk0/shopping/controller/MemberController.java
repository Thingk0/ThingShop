package com.thingk0.shopping.controller;

import com.thingk0.shopping.dto.MemberForm;
import com.thingk0.shopping.exception.UsernameAlreadyExistsException;
import com.thingk0.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping(value = "/join")
    public String join(Model model) {
        log.info("========== 회원가입 폼 GET ==========");

        // memberForm 템플릿에 빈 객체를 전달.
        model.addAttribute("memberForm", new MemberForm());
        return "member/memberForm";
    }

    @PostMapping(value = "/join")
    public String join(@Valid MemberForm memberForm,
                       BindingResult bindingResult,
                       Model model) {
        log.info("========== 회원가입 ==========");

        if (bindingResult.hasErrors()) {
            log.info("========== 폼 에러 ==========");
            return "member/memberForm";
        }

        try {
            memberService.save(memberForm, passwordEncoder);
        } catch (UsernameAlreadyExistsException e) {
            log.info("========== 중복 가입 ==========");
            //=== 중복된 아이디이므로 아이디만 비우고 다시 폼 반환. ===//
            memberForm.setUsername("");
            model.addAttribute("memberForm", memberForm);
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login() {
        log.info("========== 로그인 폼 ==========");
        return "member/memberLogin";
    }

    @GetMapping(value = "/login/error")
    public String loginFail(Model model) {
        log.info("========== 로그인 실패 ==========");
        model.addAttribute("loginErrorMessage", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/memberLogin";
    }
}
