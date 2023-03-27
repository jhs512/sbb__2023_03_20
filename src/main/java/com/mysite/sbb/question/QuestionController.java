package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
// @Validated 컨트롤러에서는 이 부분 생략가능
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page);

        model.addAttribute("paging", paging);

        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = questionService.getQuestion(id);

        model.addAttribute("question", question);

        return "question_detail";
    }

    @GetMapping("/create")
    // questionForm 변수는 model.addAttribute 없이 바로 뷰에서 접근할 수 있다.
    // QuestionForm questionForm 써주는 이유, question_form.html 에서 questionForm 변수가 없으면 실행이 안되기 때문에
    // 빈 객체라도 만든다.
    public String questionCreate(Model model) {
        model.addAttribute("questionForm", new QuestionForm());
        return "question_form";
    }

    @PostMapping("/create")
    // @Valid QuestionForm questionForm
    // questionForm 값을 바인딩 할 때 유효성 체크를 해라!
    // questionForm 변수와 bindingResult 변수는 model.addAttribute 없이 바로 뷰에서 접근할 수 있다.
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if ( bindingResult.hasErrors() ) {
            // question_form.html 실행
            // 다시 작성하라는 의미로 응답에 폼을 실어서 보냄
            return "question_form";
        }

        questionService.create(questionForm.getSubject(), questionForm.getContent());

        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
}