package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        return "안녕하세요 sbb에 오신것을 환영합니다.";
    }

    @GetMapping("/")
    public String root() {
        // redirect: 302
        // 302 는 "이 분을 찾아가 보세요." 라고 응답
        // 브라우저 주소가 아래로 바뀐다.
        return "redirect:/question/list";
    }
}

